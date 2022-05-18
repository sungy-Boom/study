package dp;

/**
 * @author guiyong
 * @date 2022/5/10 23:14
 * https://leetcode.cn/problems/jump-game-ii/
 */
public class MinJump {

    public static void main(String[] args) {
        System.out.println(new MinJump().jump(new int[]{2, 3, 1, 1, 4}));
    }

    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
