package com.daily.learn.javamath;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by SunGuiyong
 * on 2018/1/22.
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimalTest test = new BigDecimalTest();
        test.test();

        test.bigIntegerTest();
    }

    public void test() {
        BigDecimal num1 = new BigDecimal("142134324132412343243214234132413431432432143214123432142134234231423412323.123");
        BigDecimal num2 = new BigDecimal(123.123);

        System.out.println(num1);
        System.out.println(num2);
    }

    public void bigIntegerTest(){
        BigInteger num1 = new BigInteger("123123");
        BigInteger num2 = new BigInteger("123");
        BigInteger temp = new BigInteger("12345678910");
        num1 = num1.mod(temp);
        num2 = num2.mod(temp);
        System.out.println(num1.mod(num2));
    }
}
