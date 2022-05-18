/**
 * @author guiyong
 * @date 2022/4/10 17:28
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 */
public class ReverseNStr {
    public String reverseLeftWords(String s, int n) {
        if (s == null || "".equals(s) || n == 0) {
            return s;
        }

        String sub = s.substring(0, n);
        String sub2 = s.substring(n);
        return sub2 + sub;
    }
}
