package com.feign.client.service.impl;

import com.feign.client.service.IFeignServerTestThread;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

/*
 * @auth amos
 * @date 2019/1/2
 **/
@Service
public class FeignServerTestThreadImpl implements FallbackFactory<IFeignServerTestThread> {

    @Override
    public IFeignServerTestThread create(Throwable throwable) {
        return new IFeignServerTestThread() {

            @Override
            public String fff() {
                System.out.println(throwable);
                return "request error";
            }
        };
    }
}
