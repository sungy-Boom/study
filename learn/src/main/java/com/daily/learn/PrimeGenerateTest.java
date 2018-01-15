package com.daily.learn;

import com.google.common.collect.ImmutableList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunGuiyong
 * on 2018/1/9.
 */
public class PrimeGenerateTest {

    public static void main(String[] args) {
        PrimeGenerateTest test = new PrimeGenerateTest();
        List<Integer> res = test.generatePrime(10000);
        System.out.println(res.size());

        long start;
        long end;

        int[] primeFactor1 = test.getPrimeFactor(2 * 7 * 13 * 11);
        start = System.currentTimeMillis();
        List<Integer> primeFactor = test.getPrimeFactor_list(2 * 23 * 11 * 29 * 31 * 37 * 42);
        end = System.currentTimeMillis();
        System.out.println(primeFactor.size());
        System.out.println("getPrimeFactor_list use list time " + (end - start));

        start = System.currentTimeMillis();
        System.out.println();
        List<Integer> list = ImmutableList.of(2, 23, 11, 29, 31, 37, 42);
        String result = test.getPrimeProduct(list);
        end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("getPrimeProduct use BigInteger time " + (end - start));

        start = System.currentTimeMillis();
        List<Integer> divRes = test.getPrimeFactor("706905276");
        System.out.println(divRes);
        end = System.currentTimeMillis();
        System.out.println("getPrimeFactor use bigInteger time " + (end - start));
    }

    public List<Integer> generatePrime(int maxNum) {
        List<Integer> resList = new ArrayList<>();

        if (maxNum <= 1) {
            return new ArrayList<>();
        }
        resList.add(2);

        for (int i = 3; i < maxNum; i += 2) {
            if (new PrimeGenerateTest().isPrime(i)) {
                resList.add(i);
            }
        }
        return resList;
    }

    //    O(n*Math.sqrt(n))--> O(n/2*Math.sqrt(n))
    private List<Integer> generatePrime(int startNum, int endNum) {
        List<Integer> resList = new ArrayList<>();

        if (2 > startNum) {
            startNum = 2;
        }
        if (2 == startNum) {
            resList.add(startNum);
        }
        if (0 == startNum % 2) {
            startNum += 1;
        }
        for (int i = startNum; i < endNum; i += 2) {
            if (isPrime(i)) {
                resList.add(i);
            }
        }
        return resList;
    }

    //    O(Math.sqrt(n))
    private boolean isPrime(int num) {

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /*计算质因数，使用int[]*/
    private int[] getPrimeFactor(int num) {
        int[] res = new int[num];
        int index = 0;
        for (int i = 2; i <= num; i++) {
            if (0 == num % i) {
                res[index++] = i;
                num = num / i;
            }
        }
        return res;
    }

    /*计算质因数，使用List*/
    public List<Integer> getPrimeFactor_list(int num) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= num; i++) {
            if (0 == num % i) {
                res.add(i);
                num = num / i;
            }
        }
        return res;
    }

    /*计算质因数，BigInteger*/
    public List<Integer> getPrimeFactor(String num) {
        BigInteger flg = new BigInteger(num);
        List<Integer> res = new ArrayList<>();

        for (int i = 2; ; i++) {
            BigInteger temp = new BigInteger(i + "");
            if (new BigInteger(0 + "").equals(flg.mod(temp))) {
                res.add(i);
                flg = flg.divide(temp);
            }
            if (temp.compareTo(flg) >= 1) {
                break;
            }
        }
        return res;
    }

    /*public List<Integer> getPrimeFactor(BigInteger num) {
        BigInteger flg = num;
        List<Integer> res = new ArrayList<>();

        for (int i = 2; ; i++) {
            BigInteger temp = new BigInteger(i + "");
            if (new BigInteger(0 + "").equals(flg.mod(temp))) {
                res.add(i);
                flg = flg.divide(temp);
            }
            if (temp.compareTo(flg) >= 1) {
                break;
            }
        }
        return res;
    }*/

    /*通过BigInteger 获取乘法结果*/
    private String getPrimeProduct(int num) {

        BigInteger big = new BigInteger("1");
        return big.multiply(new BigInteger(num + "")).toString();
    }

    /*通过BigInteger获取乘法结果*/
    private String getPrimeProduct(List<Integer> list) {

        BigInteger big = new BigInteger("1");

        for (Integer item : list) {
            big = big.multiply(new BigInteger(item + ""));
//            System.out.println(big);
        }

        return big.toString();
    }
}
