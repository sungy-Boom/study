package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guiyong
 * @date 2022/5/15 21:52
 * https://leetcode.cn/problems/generate-parentheses/
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        List<String> str = new GenerateParenthesis().generateParenthesis(3);
        System.out.println();
    }

    private List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generate("", n, n);
        return res;
    }

    public void generate(String str, int leftCnt, int rightCnt) {
        if (leftCnt == 0 && rightCnt == 0) {
            res.add(str);
            return;
        }
        if (leftCnt == rightCnt) {
            generate(str + "(", leftCnt - 1, rightCnt);
        } else if (leftCnt < rightCnt) {
            if (leftCnt > 0) {
                generate(str + "(", leftCnt - 1, rightCnt);
            }
            generate(str + ")", leftCnt, rightCnt - 1);
        }
    }
}
