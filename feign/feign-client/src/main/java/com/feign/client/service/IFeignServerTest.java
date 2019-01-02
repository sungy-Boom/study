package com.feign.client.service;

import com.feign.client.service.impl.FeignServerTestImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * @auth amos
 * @date 2019/1/2
 **/
@FeignClient(name = "${feign.server.name}", url = "${feign.server.url}",
        fallback = FeignServerTestImpl.class)
public interface IFeignServerTest {
    @GetMapping("/server/first")
    String fff();
}
