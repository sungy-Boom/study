import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author sunguiyong
 * @date 2022/4/11 6:10 下午
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * https://leetcode.cn/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        Stack<Integer> chStack = new Stack<>();
        chStack.push(-1);
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                chStack.push(i);
            } else {
                if (!chStack.isEmpty()) {
                    chStack.pop();
                }
                if (chStack.isEmpty()) {
                    chStack.push(i);
                } else {
                    maxCount = Math.max(maxCount, i - chStack.peek());
                }
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()"));
        System.out.println(new LongestValidParentheses().longestValidParentheses("()(()"));
        System.out.println(new LongestValidParentheses().longestValidParentheses(")()())"));
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()()())()())"));
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()()()))()())"));
        System.out.println(new LongestValidParentheses().longestValidParentheses(""));
    }
}
