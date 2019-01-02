package com.feign.client.service.impl;

import com.feign.client.service.IFeignServerTest2;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

/*
 * @auth amos
 * @date 2019/1/2
 **/
@Service
public class FeignServerTest2Impl implements FallbackFactory<IFeignServerTest2> {

    @Override
    public IFeignServerTest2 create(Throwable throwable) {
        return new IFeignServerTest2() {

            @Override
            public String fff() {
                System.out.println(throwable);
                return "request error";
            }
        };
    }
}
