/**
 * @author guiyong
 * @date 2022/4/10 18:20
 */
public class FindItemInSortedArr {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int mid = binarySearchIndex(nums, target);
        if (mid == -1) {
            return 0;
        }
        int count = 1;
        int left = mid - 1, right = mid + 1;

        while (left >= 0 && nums[left] == target) {

            if (nums[left] == target) {
                count++;
                left--;
            }
        }

        while (right < nums.length && nums[right] == target) {
            if (nums[right] == target) {
                count++;
                right++;
            }
        }
        return count;
    }

    public int binarySearchIndex(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FindItemInSortedArr().search(new int[]{5, 7, 7, 8, 8, 10}, 6));
        System.out.println(new FindItemInSortedArr().search(new int[]{8}, 8));
        System.out.println(new FindItemInSortedArr().search(new int[]{}, 8));
        System.out.println(new FindItemInSortedArr().search(new int[]{2,2}, 8));
    }
}
