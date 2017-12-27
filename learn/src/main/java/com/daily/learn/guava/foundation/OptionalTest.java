package com.daily.learn.guava.foundation;

import com.google.common.base.Optional;

import java.util.Set;

/**
 * @author Soul
 */
public class OptionalTest {

    public static void main(String[] args) {
        OptionalTest opTest = new OptionalTest();
        System.out.println("*****Integer Test*******");
        opTest.add();

        System.out.println();
        System.out.println("*****String Test*******");
        opTest.string();
    }

    /**
     * Integer 类型使用Optional
     */
    private void add() {
        Integer value1 = null;
        Integer value2 = 10;
        //Optional.of ---> 会检测null 如果是null抛出异常
        /*Optional<Integer> num1 = Optional.of(value1);*/

        Optional<Integer> num1 = Optional.of(value2);
        //Optional.fromNullable --> 允许输入为空
        Optional<Integer> num2 = Optional.fromNullable(value1);

        Optional<Integer> num3 = Optional.absent();
        //value2 = 10  value1 = null;
        //Optional.orNull --> 如果是null，就把null作为值
        System.out.println("orNull() test: " + num1.orNull());
        System.out.println("orNull() test: " + num2.orNull());

        //Optional.isPresent --> 判断对象值是否存在
        System.out.println("isPresent() test: " + num1.isPresent());
        System.out.println("isPresent() test: " + num2.isPresent());

        //Optional.or --> 如果存在，返回值，如果不存在，返回传入的值
        //箱用一个特定值代替null的时候，可以使用这个方法
        Integer num = Optional.fromNullable(value1).or(new Integer(1));

        System.out.println("get() test: " + num1.get());

        System.out.println("a+b :" + (num1.get() + num));
    }

    /**
     * String 类使用Optional
     */
    private void string() {
        String str1 = null;
        String str2 = "";

        Optional<String> opStr1 = Optional.of(str2);

        System.out.println(opStr1.isPresent());

        String opStr2 = Optional.fromNullable(str1).or("这是一个空值");

        System.out.println("opStr1 :" + opStr1.get());
        System.out.println("opStr2 :" + opStr2);

        //size只有0个或1个值
        Set<String> set = opStr1.asSet();
        System.out.println(set.size());
    }
}
