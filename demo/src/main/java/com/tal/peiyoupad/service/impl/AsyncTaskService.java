package com.tal.peiyoupad.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by SunGuiyong
 * on 2018/2/7.
 */
@Component
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
