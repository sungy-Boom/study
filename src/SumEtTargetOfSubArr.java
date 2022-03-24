import java.util.HashMap;
import java.util.Map;

/**
 * @author sunguiyong
 * @date 2022/3/24 4:46 下午
 */
public class SumEtTargetOfSubArr {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> preSumMap = new HashMap<>();
        int[] preSum = new int[nums.length + 1];
        int subCount = 0;
        preSum[0] = nums[0];
        preSumMap.put(nums[0], 1);
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];

            subCount += preSumMap.getOrDefault(preSum[i] - k, 0);

            int value = preSumMap.getOrDefault(preSum[i], 0);
            preSumMap.put(preSum[i], value + 1);
        }

        return subCount;
    }
}
