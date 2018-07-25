package com.gsyun.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.gsyun.passbook.constant.Constants;
import com.gsyun.passbook.constant.ErrorCode;
import com.gsyun.passbook.dao.MerchantsDao;
import com.gsyun.passbook.entity.Merchants;
import com.gsyun.passbook.service.IMerchantsService;
import com.gsyun.passbook.vo.CreateMerchantsRequest;
import com.gsyun.passbook.vo.CreateMerchantsResponse;
import com.gsyun.passbook.vo.PassTemplate;
import com.gsyun.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author gongshiyun
 * @Description 商户服务接口实现
 * @date 2018/7/25
 */
@Slf4j
@Service
public class MerchantsServiceImpl implements IMerchantsService {

    /**
     * 数据库接口
     */
    private final MerchantsDao merchantsDao;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MerchantsServiceImpl(MerchantsDao merchantsDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();

        ErrorCode errorCode = request.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS) {
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }

        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();

        Optional<Merchants> optionalMerchants = merchantsDao.findById(id);
        if (!optionalMerchants.isPresent()) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXITS.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXITS.getDesc());
        } else {
            response.setData(optionalMerchants.get());
        }

        return response;
    }

    @Override
    public Response buildMerchantsInfoByName(String name) {
        Response response = new Response();

        Merchants merchants = merchantsDao.findByName(name);
        if (merchants == null) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXITS.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXITS.getDesc());
        } else {
            response.setData(merchants);
        }

        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );
            log.info("DropPassTemplate: {" + passTemplate + "}");
        }

        return response;
    }
}
