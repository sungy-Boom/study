package com.daily.learn.guava.mathtools;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

/**
 * Created by sunguiyong on 2018/1/20.
 * Guava IntMath
 * <p>
 * static int checkedAdd(int a, int b)
 * static int checkedMultiply(int a, int b)
 * static int checkedPow(int b, int k)
 * static int checkedSubtract(int a, int b)
 * <p>
 * static int divide(int p, int q, RoundingMode mode)
 * static int factorial(int n)
 * static int gcd(int a, int b)
 * //判断x是否是有两个数的幂得来的
 * static boolean isPowerOfTwo(int x)
 * <p>
 * static int log10(int x, RoundingMode mode)
 * static int log2(int x, RoundingMode mode)
 * static int mean(int x, int y)
 * static int mod(int x, int m)
 * static int pow(int b, int k)
 * static int sqrt(int x, RoundingMode mode)
 */
public class IntMathTest {
    public static void main(String args[]) {
        IntMathTest tester = new IntMathTest();
        tester.testIntMath();
    }

    private void testIntMath() {
        try {
            System.out.println(IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(IntMath.divide(100, 5, RoundingMode.UNNECESSARY));
        try {
            //exception will be thrown as 100 is not completely divisible by 3 thus rounding
            // is required, and RoundingMode is set as UNNESSARY
            System.out.println(IntMath.divide(100, 3, RoundingMode.UNNECESSARY));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Log2(2): " + IntMath.log2(2, RoundingMode.HALF_EVEN));

        System.out.println("Log10(10): " + IntMath.log10(10, RoundingMode.HALF_EVEN));

        System.out.println("sqrt(100): " + IntMath.sqrt(IntMath.pow(10, 2), RoundingMode.HALF_EVEN));

        System.out.println("gcd(100,50): " + IntMath.gcd(100, 50));

        System.out.println("modulus(100,50): " + IntMath.mod(100, 50));

        System.out.println("factorial(5): " + IntMath.factorial(5));
    }
}
