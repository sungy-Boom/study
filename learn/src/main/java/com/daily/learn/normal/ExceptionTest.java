package com.daily.learn.normal;

/**
 * Created by SunGuiyong
 * on 2018/2/22.
 */
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            exceptionCatchTest();
        }catch (Exception e){
            System.out.println("null pointer occur");
        }
    }

    private static int exceptionTest() {
        int a = 1;
        try {
            return a;
//            System.out.println("test");
//            throw new Exception("exception");
//            return 1;
        } catch (Exception e) {
//            throw e;
        } finally {
            a = -1;
        }
        return 0;
    }

    public static void exceptionCatchTest() {
        Integer f = null;
        String t = String.valueOf(f);
        System.out.println(t);
    }
}
