package com.spring.boot.spring.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * Created by SunGuiyong
 * on 2018/2/7.
 */
@Component
@EnableAsync
public class AsyncTaskService {

    @Async
    public void asyncTest() {
        System.out.println("asynctask test1 1");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        System.out.println("asynctask test1 2");
    }
}
