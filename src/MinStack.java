import java.util.Stack;

/**
 * @author sunguiyong
 * @date 2022/4/7 3:11 下午
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        int curMin = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
        if (curMin > x) {
            curMin = x;
        }
        minStack.push(curMin);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
        minStack.pop();
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public int min() {
        if (minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return minStack.peek();
    }
}
