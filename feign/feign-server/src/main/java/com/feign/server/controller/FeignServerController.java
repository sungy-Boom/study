package com.feign.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @auth amos
 * @date 2019/1/2
 **/
@RestController
@RequestMapping("/server")
public class FeignServerController {

    @GetMapping("/first")
    public String feignFirstTest() {
        System.out.println("test");
        return "this is first feign test";
    }
}
