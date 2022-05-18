/**
 * @author guiyong
 * @date 2022/4/10 17:49
 */
public class SearchDuplicateInArr {
    int findRepeatNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int k = nums[i];
            if (k < 0) k += n;
            if (nums[k] < 0) {
                return k;
            }
            nums[k] -= n;
        }

        return -1;
    }
}
