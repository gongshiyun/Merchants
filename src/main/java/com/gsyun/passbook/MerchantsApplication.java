package com.gsyun.passbook;

import com.gsyun.passbook.security.AuthCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@SpringBootApplication
public class MerchantsApplication extends WebMvcConfigurationSupport {

    @Resource
    private AuthCheckInterceptor authCheckInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(MerchantsApplication.class, args);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器,并配置拦截请求url
        registry.addInterceptor(authCheckInterceptor).addPathPatterns("/merchants/**");
        super.addInterceptors(registry);
    }
}
