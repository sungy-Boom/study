package com.daily.leetcode;

/**
 * Created by SunGuiyong
 * on 2017/12/28.
 * <p>
 * 6. ZigZag Conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        ZigZagConversion test = new ZigZagConversion();
        String res = test.convert("ABCDE", 4);
        System.out.println(res);
    }

    public String convert(String s, int numRows) {

        char[] ch = s.toCharArray();
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        int index = 0;
        int column = 0;
        while (index < s.length()) {
            if (column % 2 == 0) {
                for (int i = 0; i < numRows && index < s.length(); i++) {
                    sb[i].append(ch[index++]);
                }
                column++;
            }
            if (column % 2 != 0) {
                for (int i = numRows - 2; i >= 1 && index < s.length(); i--) {
                    sb[i].append(ch[index++]);
                }
                column++;
            }
        }
        StringBuilder sbRes = new StringBuilder();
        for (int i = 0; i < sb.length; i++) {
            sbRes.append(sb[i]);
        }
        return sbRes.toString();
    }
}
