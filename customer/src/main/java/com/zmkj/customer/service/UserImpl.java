package com.zmkj.customer.service;


import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

@DubboService
public class UserImpl implements User{
    @Value("${demo.service.name}")
    private String serviceName;

    @Override
    public String sayName(String name) {

        return "dubbo:"+serviceName+":"+name;
    }
}

