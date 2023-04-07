package com.zmkj.common;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "address")
@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @RequestMapping("/add")
    public String add(Address address){

        return "success";

    }
}
