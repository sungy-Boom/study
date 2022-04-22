import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author sunguiyong
 * @date 2022/4/11 6:10 下午
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        Stack<Character> chStack = new Stack<>();
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                chStack.push(s.charAt(i));
            } else {
                if (chStack.isEmpty()) {
                    chStack.push(s.charAt(i));
                    count = 0;
                } else if (chStack.peek() == '(') {
                    count += 2;
                    chStack.pop();
                } else {
                    count = 0;
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return Math.max(maxCount, count);
    }

    public static void main(String[] args) {
//        System.out.println(new LongestValidParentheses().longestValidParentheses("(()"));
//        System.out.println(new LongestValidParentheses().longestValidParentheses("()(()"));
//        System.out.println(new LongestValidParentheses().longestValidParentheses(")()())"));
//        System.out.println(new LongestValidParentheses().longestValidParentheses("(()()())()())"));
//        System.out.println(new LongestValidParentheses().longestValidParentheses("(()()()))()())"));
//        System.out.println(new LongestValidParentheses().longestValidParentheses(""));
        Map<String, String> test = new HashMap<>();
        test.put("tttt", "12");
        test.put("tttts", "12");
        test.put("ttttsa", "12");
        System.out.println("asa".hashCode());
        System.out.println("bsb".hashCode());
        System.out.println();
    }
}
