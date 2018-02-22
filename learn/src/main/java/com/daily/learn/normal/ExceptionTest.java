package com.daily.learn.normal;

/**
 * Created by SunGuiyong
 * on 2018/2/22.
 */
public class ExceptionTest {

    public static void main(String[] args) {
        int res = exceptionTest();
        System.out.println(res);
    }

    private static int exceptionTest() {
        try {
            System.out.println("test");
            throw new Exception();
//            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return -1;
        }
    }
}
