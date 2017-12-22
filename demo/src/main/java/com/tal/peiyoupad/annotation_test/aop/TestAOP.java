package com.tal.peiyoupad.annotation_test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by SunGuiyong
 * on 2017/12/22.
 */
@Aspect
@Component
@ComponentScan
public class TestAOP {
    @Pointcut("@annotation(com.tal.peiyoupad.annotation_test.annotation.TestAnnotation)")
    public void annotationProcessor_1() {
    }

    /**
     * 前置通知
     */
    @Before("annotationProcessor_1()")
    public void doBeforeController(JoinPoint joinPoint) {
        System.out.println("this is an aop test");
    }
}
