import java.util.HashMap;
import java.util.Map;

/**
 * @author guiyong
 * @date 2022/3/26 21:53
 */
class SameZeroAndOne {
    int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> numMap = new HashMap<>();

        int maxLen = 0;
        int sum = 0;
        numMap.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum--;
            } else {
                sum++;
            }

            if (numMap.containsKey(sum)) {
                int preIndex = numMap.get(sum);
                maxLen = Math.max(maxLen, i - preIndex);
            } else {
                numMap.put(sum, i);
            }
        }

        return maxLen;
    }
}
