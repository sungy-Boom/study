/**
 * @author sunguiyong
 * @date 2022/2/28 8:59 下午
 * https://leetcode-cn.com/problems/JFETK5/
 */
public class BinaryStrAdd {

    public static void main(String[] args) {
        String a = "1010", b = "1011";
        System.out.println(new BinaryStrAdd().addBinary(a, b));
    }

    public String addBinary2(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return b;
        }
        int aLen = a.length();
        int bLen = b.length();
        String[] binaryArr = new String[Math.max(aLen + 1, bLen + 1)];
        int index = 0;
        int up = 0;
        int aIndex = aLen - 1, bIndex = bLen - 1;
        while (aIndex >= 0 && bIndex >= 0) {

            if (a.charAt(aIndex) == '1' && b.charAt(bIndex) == '1') {
                if (up == 1) {
                    binaryArr[index++] = "1";
                } else {
                    binaryArr[index++] = "0";
                }
                up = 1;
            } else if (a.charAt(aIndex) == '1' || b.charAt(bIndex) == '1') {
                if (up == 1) {
                    binaryArr[index++] = "0";
                    up = 1;
                } else {
                    binaryArr[index++] = "1";
                    up = 0;
                }
            } else {
                if (up == 1) {
                    binaryArr[index++] = "1";
                } else {
                    binaryArr[index++] = "0";
                }
                up = 0;
            }
            aIndex--;
            bIndex--;
        }
        if (aIndex >= 0) {
            while (aIndex >= 0) {
                if (up == 1) {
                    if (a.charAt(aIndex) == '1') {
                        binaryArr[index++] = "0";
                        up = 1;
                    } else {
                        binaryArr[index++] = "1";
                        up = 0;
                    }
                } else {
                    binaryArr[index++] = a.charAt(aIndex) + "";
                }
                aIndex--;
            }
        }

        if (bIndex >= 0) {
            while (bIndex >= 0) {
                if (up == 1) {
                    if (b.charAt(bIndex) == '1') {
                        binaryArr[index++] = "0";
                        up = 1;
                    } else {
                        binaryArr[index++] = "1";
                        up = 0;
                    }
                } else {
                    binaryArr[index++] = b.charAt(bIndex) + "";
                }
                bIndex--;
            }
        }
        if (up == 1) {
            binaryArr[index] = "1";
        }
        StringBuilder res = new StringBuilder();
        for (int i = binaryArr.length - 1; i >= 0; i--) {
            if (binaryArr[i] == null) {
                continue;
            }
            res.append(binaryArr[i]);
        }
        return res.toString();
    }

    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return b;
        }
        StringBuilder res = new StringBuilder();
        int aLen = a.length() - 1, bLen = b.length() - 1;
        int tempSum = 0;
        while (aLen >= 0 || bLen >= 0) {
            tempSum += aLen >= 0 ? a.charAt(aLen) - '0' : 0;
            tempSum += bLen >= 0 ? b.charAt(bLen) - '0' : 0;
            res.append(tempSum % 2);
            tempSum = tempSum / 2;
            aLen--;
            bLen--;
        }
        if (tempSum > 0) {
            res.append("1");
        }
        res.reverse();
        return res.toString();
    }
}
