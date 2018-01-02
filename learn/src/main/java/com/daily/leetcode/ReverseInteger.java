package com.daily.leetcode;

/**
 * Created by SunGuiyong
 * on 2017/12/28.
 * <p>
 * 7. Reverse Integer
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output:  321
 * Example 2:
 * <p>
 * Input: -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger test = new ReverseInteger();
        int res = test.reverse(1);
        System.out.println(res);
    }

    /**
     * 先把int转成string，然后利用String reverse，再转回int，在转的过程中注意符号
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int num = Math.abs(x);
        String str = String.valueOf(num);
        StringBuilder sb = new StringBuilder(str);
        str = sb.reverse().toString();
        StringBuilder maxSb = new StringBuilder("7463847412");
        if (str.compareTo(maxSb.toString()) > 0) {
            return 0;
        }
        num = Integer.valueOf(str);
        if (x < 0) {
            return -num;
        } else {
            return num;
        }
    }
}
