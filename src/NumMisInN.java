/**
 * @author sunguiyong
 * @date 2022/4/11 4:09 下午
 */
public class NumMisInN {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int left = 0, right = nums.length - 1;
        int lastLeft = left, mid = right;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] != mid) {
                break;
            }
            left = mid + 1;
            lastLeft = left;
        }
        for (int i = lastLeft; i <= mid; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        System.out.println(new NumMisInN().missingNumber(new int[]{0, 1, 3}));
        System.out.println(new NumMisInN().missingNumber(new int[]{3}));
    }
}
