package com.daily.learn.guava.mathtools;

import com.google.common.math.LongMath;

import java.math.RoundingMode;

/**
 * Created by sunguiyong on 2018/1/21.
 * Guava LongMath
 * <p>
 * static long checkedAdd(long a, long b)
 * static long checkedMultiply(long a, long b)
 * static long checkedPow(long b, int k)
 * static long checkedSubtract(long a, long b)
 * <p>
 * static long divide(long p, long q, RoundingMode mode)
 * static long factorial(int n)
 * static long gcd(long a, long b)
 * static boolean isPowerOfTwo(long x)
 * static int log10(long x, RoundingMode mode)
 * static int log2(long x, RoundingMode mode)
 * static long mean(long x, long y)
 * static int mod(long x, int m)
 * static long mod(long x, long m)
 * static long pow(long b, int k)
 * static long sqrt(long x, RoundingMode mode)
 */
public class LongMathTest {
    public static void main(String args[]) {
        LongMathTest tester = new LongMathTest();
        tester.testLongMath();
    }

    private void testLongMath() {
        try {
            System.out.println(LongMath.checkedAdd(Long.MAX_VALUE, Long.MAX_VALUE));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(LongMath.divide(100, 5, RoundingMode.UNNECESSARY));
        try {
            //exception will be thrown as 100 is not completely divisible by 3 thus rounding
            // is required, and RoundingMode is set as UNNESSARY
            System.out.println(LongMath.divide(100, 3, RoundingMode.UNNECESSARY));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Log2(2): " + LongMath.log2(2, RoundingMode.HALF_EVEN));

        System.out.println("Log10(10): " + LongMath.log10(10, RoundingMode.HALF_EVEN));

        System.out.println("sqrt(100): " + LongMath.sqrt(LongMath.pow(10, 2), RoundingMode.HALF_EVEN));

        System.out.println("gcd(100,50): " + LongMath.gcd(100, 50));

        System.out.println("modulus(100,50): " + LongMath.mod(100, 50));

        System.out.println("factorial(5): " + LongMath.factorial(5));
    }
}
