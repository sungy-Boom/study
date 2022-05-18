/**
 * https://leetcode-cn.com/problems/xoh6Oh/
 *
 * @author sunguiyong
 * @date 2022/2/24 11:12 上午
 */
public class TwoNumberDivided {

    public static void main(String[] args) {
        TwoNumberDivided twoNumberDivided = new TwoNumberDivided();
        int res = twoNumberDivided.divide(10, -20);
        System.out.println(res);
    }

    // 32 位最大值：2^31 - 1 = 2147483647
    // 32 位最小值：-2^31 = -2147483648
    //把除法转为减法，一次次减，最多循环 Integer.MIN_VALUE + 1次
    public int divide(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = a < 0 ^ b < 0 ? -1 : 1;
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int remain = a;
        int res = 0;
        while (remain <= b) {
            remain -= b;
            res++;
        }
        return sign == -1 ? -res : res;
    }

    public int divide2(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = a < 0 ^ b < 0 ? -1 : 1;
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;
        while (a <= b) {
            int k = 1;
            while (k <= Integer.MAX_VALUE / 2 && a >= b + b) {

                k += k;
            }
            a -= b;
            res++;
        }
        return sign == -1 ? -res : res;
    }
}
