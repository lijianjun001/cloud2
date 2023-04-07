package com.zmkj.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @RequestMapping("/info")
    public String info(){

        return "info";

    }
}
