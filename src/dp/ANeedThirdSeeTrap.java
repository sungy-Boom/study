package dp;

import java.util.Stack;

/**
 * @author guiyong
 * @date 2022/5/10 22:19
 * https://leetcode.cn/problems/trapping-rain-water/
 */
public class ANeedThirdSeeTrap {
    //使用leftMax， rightMax记录当前点左右最大值
    //然后用leftMax，rightMax中的最小值，减去height，就是当前点可以接收到的雨水数量
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    public int trapV2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int len = height.length;
        Stack<Integer> posStack = new Stack<>();
        int ans = 0;

        for (int i = 0; i < len; i++) {
            while (!posStack.isEmpty() && height[i] > height[posStack.peek()]) {
                int topIndex = posStack.pop();
                if (posStack.isEmpty()) {
                    break;
                }
                int left = posStack.peek();
                int width = i - left - 1;
                int h = Math.min(height[left], height[i]) - height[topIndex];
                ans += width * h;
            }
            posStack.push(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new ANeedThirdSeeTrap().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new ANeedThirdSeeTrap().trap(new int[]{4, 2, 0, 3, 2, 5}));

        System.out.println(new ANeedThirdSeeTrap().trapV2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new ANeedThirdSeeTrap().trapV2(new int[]{4, 2, 0, 3, 2, 5}));
    }
}
