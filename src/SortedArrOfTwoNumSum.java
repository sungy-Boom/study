/**
 * @author sunguiyong
 * @date 2022/3/18 7:17 下午
 * https://leetcode-cn.com/problems/kLl5u1/
 */
public class SortedArrOfTwoNumSum {

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 1) {
            return new int[]{};
        }

        int left = 0, right = numbers.length - 1;
        int tempSum;
        while (left < right) {
            tempSum = numbers[left] + numbers[right];
            if (tempSum < target) {
                left++;
            } else if (tempSum > target) {
                right--;
            } else {
                return new int[]{left, right};
            }
        }

        return new int[]{};
    }

}
