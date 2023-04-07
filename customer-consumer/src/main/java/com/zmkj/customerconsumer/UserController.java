package com.zmkj.customerconsumer;

import com.zmkj.customer.service.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    private final RestTemplate restTemplate;

    @DubboReference(version = "${demo.service.version}")
    private User demoService;

    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/user/info")
    public String info() {
        return restTemplate.getForObject("http://service-provider/customer/info", String.class);
    }


    @RequestMapping("/user/say")
    public String say() {
        return demoService.sayName("ljj");
    }


}
