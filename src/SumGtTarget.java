/**
 * @author sunguiyong
 * @date 2022/3/22 5:04 下午
 */
public class SumGtTarget {

    public int minSubArrayLen2(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum >= target) {
                return 1;
            }
            if (i == nums.length - 1) {
                break;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    len = Math.min(len, j - i + 1);
                    break;
                }
            }
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = Integer.MAX_VALUE;

        int start = 0, end = 0;
        int sum = 0;

        while (end < nums.length) {
            sum += nums[end];
            while (sum >= target) {
                len = Math.min(len, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
