package com.daily.learn;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by SunGuiyong
 * on 2018/1/9.
 */
public class PrimeGenerateTest {

    public static void main(String[] args) {
        PrimeGenerateTest test = new PrimeGenerateTest();
        int[] res = test.primeGenerate(1000);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
            if ((i + 1) % 30 == 0) {
                System.out.println();
            }
        }
    }

    private int[] primeGenerate(int lastNum) {
        int index = 0;
        int[] res = new int[lastNum];
        for (int i = 1; i < lastNum; i++) {
            if (isPrime(i)) {
                res[index++] = i;
            }
        }
        return res;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i < Math.abs(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
