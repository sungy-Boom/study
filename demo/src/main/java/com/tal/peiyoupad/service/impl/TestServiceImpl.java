package com.tal.peiyoupad.service.impl;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SunGuiyong
 * on 2018/1/15.
 */
@Component
public class TestServiceImpl {

    @PostConstruct
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("","");
        for (int i = 0; i < 10; i++) {
            System.out.println("this is postconstruct test " + i);
        }
    }

}
