package com.daily.learn.guava.mathtools;

import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Created by sunguiyong on 2018/1/21.
 * Guava BigIntegerMath
 * <p>
 * static BigInteger divide(BigInteger p, BigInteger q, RoundingMode mode)
 * static BigInteger factorial(int n)
 * static boolean isPowerOfTwo(BigInteger x)
 * static int log10(BigInteger x, RoundingMode mode)
 * static int log2(BigInteger x, RoundingMode mode)
 * static BigInteger sqrt(BigInteger x, RoundingMode mode)
 */
public class BigIntegerMathTest {
    public static void main(String args[]) {
        BigIntegerMathTest tester = new BigIntegerMathTest();
        tester.testBigIntegerMath();
    }

    private void testBigIntegerMath() {
        System.out.println(BigIntegerMath.divide(BigInteger.TEN, new BigInteger("2"), RoundingMode.UNNECESSARY));
        try {
            //exception will be thrown as 100 is not completely divisible by 3 thus rounding
            // is required, and RoundingMode is set as UNNESSARY
            System.out.println(BigIntegerMath.divide(BigInteger.TEN, new BigInteger("3"), RoundingMode.CEILING));
            System.out.println(BigIntegerMath.divide(BigInteger.TEN, new BigInteger("3"), RoundingMode.UNNECESSARY));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Log2(2): " + BigIntegerMath.log2(new BigInteger("2"), RoundingMode.HALF_EVEN));

        System.out.println("Log10(10): " + BigIntegerMath.log10(BigInteger.TEN, RoundingMode.HALF_EVEN));

        System.out.println("sqrt(100): " + BigIntegerMath.sqrt(BigInteger.TEN.multiply(BigInteger.TEN), RoundingMode.HALF_EVEN));

        System.out.println("factorial(5): " + BigIntegerMath.factorial(5));

        BigInteger test1 = new BigInteger(123+"");
        System.out.println(test1.divide(new BigInteger(4+"")));
    }
}
