package dp;

/**
 * @author guiyong
 * @date 2022/5/15 15:24
 * https://leetcode.cn/problems/regular-expression-matching/
 */
public class PatternMatch {

    public static void main(String[] args) {
        System.out.println(new PatternMatch().isMatch("aa", "a"));
        System.out.println(new PatternMatch().isMatch("aa", "a*"));
        System.out.println(new PatternMatch().isMatch("ab", ".b"));
        System.out.println(new PatternMatch().isMatch("ab", ".*"));
        System.out.println(new PatternMatch().isMatch("aab", "c*a*b"));
        System.out.println(new PatternMatch().isMatch("mississippi", "mis*is*ip*."));
    }

    public boolean isMatch(String s, String p) {
        if (s == null || s.equals("")) {
            return false;
        }
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] flag = new boolean[sLen + 1][pLen + 1];
        flag[0][0] = true;
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {
                    flag[i][j] = flag[i][j - 2];
                    if (match(s, p, i, j - 1)) {
                        flag[i][j] = flag[i - 1][j] || flag[i][j];
                    }
                } else {
                    if (match(s, p, i, j)) {
                        flag[i][j] = flag[i - 1][j - 1];
                    }
                }
            }
        }
        return flag[sLen][pLen];
    }

    private boolean match(String s, String p, int sIndex, int pIndex) {
        if (sIndex == 0) {
            return false;
        }
        if (p.charAt(pIndex - 1) == '.') {
            return true;
        }
        return s.charAt(sIndex - 1) == p.charAt(pIndex - 1);
    }
}
