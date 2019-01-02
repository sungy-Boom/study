package com.feign.client;

import com.feign.client.service.IFeignServerTest;
import com.feign.client.service.IFeignServerTest2;
import com.feign.client.service.IFeignServerTestThread;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * @auth amos
 * @date 2019/1/2
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignTest {

    @Autowired
    private IFeignServerTest feignServerTest;
    @Autowired
    private IFeignServerTest2 feignServerTest2;
    @Autowired
    private IFeignServerTestThread feignServerTestThread;

    @Test
    public void test() {
        System.out.println(feignServerTest.fff());
        System.out.println(feignServerTest2.fff());
        System.out.println(feignServerTestThread.fff());
    }

    @Test
    public void threadTest() {
        Thread t = new Thread();
        System.out.println(feignServerTest2.fff());
        for (int i = 0; i < 100; i++) {
            Runnable r = () -> {
                String res = feignServerTest2.fff();
                System.out.println(res);
            };
            t = new Thread(r);
            t.start();
        }
    }

    @Test
    public void threadTest2() {
        System.out.println(feignServerTestThread.fff());
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(feignServerTestThread.fff());
            }).start();
        }
    }

}
