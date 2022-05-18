import java.util.Stack;

/**
 * @author guiyong
 * @date 2022/5/12 14:18
 * (()()))()()()() 8
 * )() 2
 * )()()(()) 8
 */
public class Main5 {
    public static void main(String[] args) {
        System.out.println(new Main5().maxCount(""));
        System.out.println(new Main5().maxCount("()()"));
        System.out.println(new Main5().maxCount("(()(()()()"));
        System.out.println(new Main5().maxCount(")()()((()()()))"));


    }

    public int maxCount(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }

        int max = 0;
        Stack<Integer> indexStack = new Stack<>();
        indexStack.push(-1);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                indexStack.push(i);
            } else {
                indexStack.pop();
                if (indexStack.isEmpty()) {
                    indexStack.push(i);
                } else {
                    max = Math.max(max, i - indexStack.peek());
                }
            }
        }

        return max;
    }
}
