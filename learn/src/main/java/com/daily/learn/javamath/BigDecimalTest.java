package com.daily.learn.javamath;

import java.math.BigDecimal;

/**
 * Created by SunGuiyong
 * on 2018/1/22.
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimalTest test = new BigDecimalTest();
        test.test();
    }

    public void test() {
        BigDecimal num1 = new BigDecimal("142134324132412343243214234132413431432432143214123432142134234231423412323.123");
        BigDecimal num2 = new BigDecimal(123.123);

        System.out.println(num1);
        System.out.println(num2);
    }
}
