package com.gsyun.passbook.service;

import com.alibaba.fastjson.JSON;
import com.gsyun.passbook.vo.CreateMerchantsRequest;
import com.gsyun.passbook.vo.PassTemplate;
import com.gsyun.passbook.vo.Response;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author gongshiyun
 * @Description 商户服务测试类
 * @date 2018/7/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServiceTest {

    @Autowired
    private IMerchantsService merchantsService;

    @Test
    //@Transactional
    public void testCreateMerchants() {
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setAddress("广州市");
        request.setBusinessLicenseUrl("www.gongshiyun.top");
        request.setLogoUrl("logo-url");
        request.setName("gsyun");
        request.setPhone("123456677");

        System.out.println(JSON.toJSONString(merchantsService.createMerchants(request)));
    }

    /**
     * {"data":{"address":"广州市","businessLicenseUrl":"www.gongshiyun.top",
     * "id":19,"isAudit":true,"logoUrl":"logo-url","name":"gsyun",
     * "phone":"123456677"},"errorCode":0,"errorMsg":""}
     */
    @Test
    public void testBuildMerchantsInfoById() {
        Response response = merchantsService.buildMerchantsInfoById(19);
        System.out.println(JSON.toJSONString(response));
    }

    /**
     * {"data":{"address":"广州市","businessLicenseUrl":"www.gongshiyun.top",
     * "id":19,"isAudit":true,"logoUrl":"logo-url","name":"gsyun",
     * "phone":"123456677"},"errorCode":0,"errorMsg":""}
     */
    @Test
    public void testBuildMerchantsInfoByName() {
        Response response = merchantsService.buildMerchantsInfoByName("gsyun");
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testDropPassTemplate() {
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(19);
        passTemplate.setTitle("gsyun");
        passTemplate.setBackground(1);
        passTemplate.setDesc("详情");
        passTemplate.setHasToken(true);
        passTemplate.setLimit(1000L);
        passTemplate.setStart(new Date());
        passTemplate.setEnd(DateUtils.addDays(new Date(),10));
        passTemplate.setSummary("简介");

        System.out.println(JSON.toJSONString(merchantsService.dropPassTemplate(passTemplate)));
    }
}
