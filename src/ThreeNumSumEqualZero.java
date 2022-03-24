import java.util.*;

/**
 * @author sunguiyong
 * @date 2022/3/18 7:34 下午
 */
public class ThreeNumSumEqualZero {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        Map<String, Integer> containsMap = new HashMap<>();

        List<List<Integer>> list = new ArrayList<>();
        int tempSum;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            int temp = -nums[i];

            while (left < right) {
                tempSum = nums[left] + nums[right];
                if (tempSum < temp) {
                    left++;
                } else if (tempSum > temp) {
                    right--;
                } else {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(nums[i]);
                    tempList.add(nums[left]);
                    tempList.add(nums[right]);
                    String key = "" + nums[i] + nums[left] + nums[right];
                    if (!containsMap.containsKey(key)) {
                        list.add(tempList);
                    }
                    containsMap.put(key, 1);
                    right--;
                }
            }
        }
        return list;
    }

}
