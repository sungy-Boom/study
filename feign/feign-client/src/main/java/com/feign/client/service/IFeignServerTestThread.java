package com.feign.client.service;

import com.feign.client.service.impl.FeignServerTest2Impl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * @auth amos
 * @date 2019/1/2
 **/
@FeignClient(name = "${feign.server-thread.name}", url = "${feign.server-thread.url}",
        fallbackFactory = FeignServerTest2Impl.class)
//@Component
public interface IFeignServerTestThread {
    @GetMapping("/server/first")
    String fff();
}
