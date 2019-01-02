package com.feign.client.controller;

import com.feign.client.service.IFeignServerTest;
import com.feign.client.service.IFeignServerTest2;
import com.feign.client.service.impl.FeignServerTest2Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @auth amos
 * @date 2019/1/2
 **/
@RestController
@RequestMapping("/client")
public class FeignClientController {

    @Autowired
    private IFeignServerTest feignServerTest;

    @Autowired
    private IFeignServerTest2 feignServerTest2;

    @GetMapping("/first/test")
    public void test() {
        System.out.println(feignServerTest.fff());
        System.out.println(feignServerTest2.fff());
    }
}
