package com.tal.peiyoupad.controller;

import com.tal.peiyoupad.redis.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunGuiyong
 * on 2018/8/20.
 */
@RestController
@RequestMapping("/redis")
public class RedisTestController {
    @Autowired
    private RedisTemplate<String, Object> template;


    @GetMapping("/test")
    public void redisTest() {
        User u = new User();
        u.setUsername("abc");
        u.setAge(12);
        List<String> test = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            test.add("test" + i);
        }
        u.setInfoList(test);
        long startTime = System.currentTimeMillis();
        template.opsForValue().set("u", u);

        User u2 = (User) template.opsForValue().get("u");
        long endTime = System.currentTimeMillis();
        System.out.println("运行共耗时 ： " + (endTime - startTime));
        System.out.println(u2.getUsername() + ".................");
        System.out.println(u2.getInfoList());

        template.opsForValue().set("name", "ywj");
        System.out.println(template.opsForValue().get("name").toString());
    }
}
