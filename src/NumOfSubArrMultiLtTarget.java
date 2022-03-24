/**
 * @author sunguiyong
 * @date 2022/3/22 5:42 下午
 * https://leetcode-cn.com/problems/ZVAVXX/
 */
public class NumOfSubArrMultiLtTarget {

    //10, 5, 2, 6 100
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0 || k == 1) {
            return 0;
        }
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int left = 0, right = 0;
        int ans = 0;
        int temp = 1;
        while (right < n) {
            temp *= nums[right];
            while (temp >= k) {
                temp /= nums[left];
                left++;
            }
            ans += right - left + 1;
            right++;
        }
        return ans;
    }
}
