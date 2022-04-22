/**
 * 下一个排列
 *
 * @author sunguiyong
 * @date 2022/4/8 7:34 下午
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null) {
            return;
        }
        boolean findRes = false;
        int firstDecNumIndex = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) { //变为降序的索引
                firstDecNumIndex = i;
                findRes = true;
                break;
            }
        }
        if (!findRes) {
            swap(nums, 0, nums.length - 1);
        } else {
            int firstHigher;
            for (int i = nums.length - 1; i > firstDecNumIndex; i--) {
                if (nums[i] > nums[firstDecNumIndex]) {
                    firstHigher = i;
                    int tmp = nums[firstDecNumIndex];
                    nums[firstDecNumIndex] = nums[firstHigher];
                    nums[firstHigher] = tmp;
                    break;
                }
            }
            swap(nums, firstDecNumIndex + 1, nums.length - 1);
        }
    }

    //范围内的数组进行反转
    public void swap(int[] nums, int left, int right) {
        while (left < right) {
            int num = nums[left];
            nums[left] = nums[right];
            nums[right] = num;
            right--;
            left++;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 6, 3, 5, 2};
        new NextPermutation().nextPermutation(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
