package com.spring.boot.spring.service.impl;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by SunGuiyong
 * on 2018/1/15.
 */
@Component
public class TestServiceImpl {

    @PostConstruct
    public void test() {
        for (int i = 0; i < 10; i++) {
            System.out.println("this is postconstruct test " + i);
        }
    }

}
