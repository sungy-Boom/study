package com.daily.learn.guava.foundation;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Preconditions test
 *
 * @author Soul
 */
public class PreconditionTest {

    public static void main(String[] args) {
        PreconditionTest test = new PreconditionTest();
        String str = null;

        //checkArgument
        System.out.println("*********checkArgument**********");
        try {
            str = null;
            test.argumentCondition(str, 0);
        } catch (Exception e) {
            System.out.println("Exception： " + e.getMessage());
        }

        try {
            str = "test";
            test.argumentCondition(str, 0);
        } catch (Exception e) {
            System.out.println("name： " + e.getMessage());
        }

        try {
            str = "测试年龄";
            test.argumentCondition(str, 0);
        } catch (Exception e) {
            System.out.println("age： " + e.getMessage());
        }

        //checkNotNull
        System.out.println();
        System.out.println("********checkNotNull********");
        Integer age;

        try {
            age = null;
            String name = "test";
            age = Preconditions.checkNotNull(age, "age is null,name is %s", name);
            System.out.println(age);
        } catch (Exception e) {
            System.out.println("age null: " + e.getMessage());
        }

        try {
            age = null;
            age = Preconditions.checkNotNull(age, "age is null", age, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1232);
            System.out.println(age);
        } catch (Exception e) {
            System.out.println("age null: " + e.getMessage());
        }

        try {
            age = 10;
            age = Preconditions.checkNotNull(age);
            System.out.println(age);
        } catch (Exception e) {
            System.out.println("age null: " + e.getMessage());
        }

        //indexCondition
        System.out.println();
        System.out.println("********indexCheck********");
        int res = 0;

        List<String> list = new ArrayList<>();
        try {
            res = Preconditions.checkElementIndex(-1, list.size());
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            list.add("test");
            res = Preconditions.checkElementIndex(-1, list.size(), "this list is not right");
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            list.add("test");
            res = Preconditions.checkElementIndex(0, list.size(), "this list is not right");
            Preconditions.checkPositionIndex(0, list.size());
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            list.add("test");
            list.add("12");
            Preconditions.checkPositionIndexes(0, 1, list.size());
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // checkState
        System.out.println();
        System.out.println("********checkState********");
        String state = null;

        try {
            Preconditions.checkState(state == null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            state = "test";
            Preconditions.checkState("test".equals(state), "the expression is not right");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            state = "test1234";
            Preconditions.checkState("test".equals(state), "the expression is not right,it's value is", state, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            state = "test1234";
            Preconditions.checkState("test".equals(state), "the expression is not right,it's value is", state, 1, 2, 2, 1, 2, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Preconditions.checkArgument / checkState(这两个方法相同) 当条件不符合的时候，抛出异常
     * checkArgument有三种重载
     * <p>
     * //expression判断条件是否符合，如果不符合，抛出异常，异常信息为null
     * Preconditions.checkArgument(boolean expression)
     * <p>
     * //expression 表达式 errorMessage 当抛出异常时，携带的异常信息
     * Preconditions.checkArgument(boolean expression, String errorMessage)
     * <p>
     * //expression 表达式 errorMessage 当抛出异常时，携带的异常信息 args 异常信息中可以携带要输出的值
     * Preconditions.checkArgument(boolean expression, String errorMessage, Object... args)
     *
     * @param name
     * @throws Exception
     */
    private void argumentCondition(String name, int age) throws Exception {
        Preconditions.checkArgument(name != null);

        Preconditions.checkArgument(name != "test", "this is a test");

        Preconditions.checkArgument(age > 0 && name != null, "%s's age is %s is valid", name, age);
    }


    /**
     * checkNotNull
     *
     * //reference 输入参数，检查是否为空，若空，抛出异常，否则返回reference
     * <T> T Preconditions.checkNotNull(T reference)
     *
     * //reference 输入参数，检查是否为空，若空，抛出异常，否则返回reference errorMessage 返回异常的信息
     * <T> T Preconditions.checkNotNull(T reference, Object errorMessage)
     *
     * //reference 输入参数，检查是否为空，若空，抛出异常，否则返回reference errorMessage 返回异常的信息
     * <T> T Preconditions.checkNotNull(T reference, String errorMessageTemplate, Object... objects)
     *
     * checkElementIndex /checkPositionIndex 保证索引在数组中的位置是合理的
     *
     * //index 要操作的索引，size 数组的大小 index>0 && index < size 返回index，否则抛出异常
     * int Preconditions.checkElementIndex(index, size)
     *
     * //index 要操作的索引，size 数组的大小 index>0 && index < size 返回index，否则抛出异常,desc为自定义异常信息
     * int Preconditions.checkElementIndex(index, size, desc)
     *
     * checkPositionIndex 只比 checkElementIndex 多了一个方法
     * //start 开始index end 结束index size 集合大小
     * // 如果start < 0  || end < start || end > size 抛出异常
     * void Preconditions.checkPositionIndexes(int start, int end, int size)
     */
}
