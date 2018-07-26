package com.gsyun.passbook.controller;

import com.alibaba.fastjson.JSON;
import com.gsyun.passbook.service.IMerchantsService;
import com.gsyun.passbook.vo.CreateMerchantsRequest;
import com.gsyun.passbook.vo.PassTemplate;
import com.gsyun.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gongshiyun
 * @Description 商户服务Controller
 * @date 2018/7/26
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsController {

    /**
     * 商户服务接口
     */
    private final IMerchantsService merchantsService;

    @Autowired
    public MerchantsController(IMerchantsService merchantsService) {
        this.merchantsService = merchantsService;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request) {
        log.info("CreateMerchants: " + JSON.toJSONString(request));
        return merchantsService.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/id={id}")
    public Response buildMerchantsInfoById(@PathVariable Integer id) {
        log.info("buildMerchantsInfoById: " + id);
        return merchantsService.buildMerchantsInfoById(id);
    }

    @ResponseBody
    @GetMapping("/name={name}")
    public Response buildMerchantsInfoByName(@PathVariable String name) {
        log.info("buildMerchantsInfoByName: " + name);
        return merchantsService.buildMerchantsInfoByName(name);
    }

    @ResponseBody
    @PostMapping("/dropPassTemplate")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate) {
        log.info("dropPassTemplate: " + JSON.toJSONString(passTemplate));
        return merchantsService.dropPassTemplate(passTemplate);
    }
}
