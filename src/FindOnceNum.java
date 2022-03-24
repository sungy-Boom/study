import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sunguiyong
 * @date 2022/3/1 8:38 下午
 * https://leetcode-cn.com/problems/single-number-ii/
 */
public class FindOnceNum {

    public static void main(String[] args) {
        System.out.println(new FindOnceNum().singleNumber(new int[]{0, 1, 3, 1, 0, 3, 99}));
        System.out.println(new FindOnceNum().singleNumber2(new int[]{99, 1, 99, 1, 9, 1, 99}));
        for (int i : new FindOnceNum().singleNumber3(new int[]{99, 1, 3, 5, 1, 99})) {
            System.out.println(i);
        }
    }

    //一个数出现一次，其余的出现两次
    //https://leetcode-cn.com/problems/single-number/
    public int singleNumber(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int temp = 0;
            for (int num : nums) {
                temp += num >> i & 1;
            }
            if (temp % 2 != 0) {
                ans = ans | 1 << i;
            }
        }
        return ans;
    }

    //有一个数，只出现一次,其他的数出现三次
    //https://leetcode-cn.com/problems/single-number-ii/
    public int singleNumber2(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int temp = 0;
            for (int num : nums) {
                temp += num >> i & 1;
            }
            if (temp % 3 != 0) {
                ans = ans | 1 << i;
            }
        }
        return ans;
    }

    //有两个数，只出现一次
    //https://leetcode-cn.com/problems/single-number-iii/
    public int[] singleNumber3(int[] nums) {
        if (nums.length == 0) {
            return new int[]{};
        }

        //计算两个不同数的异或
        int numOr = 0;
        for (int num : nums) {
            numOr ^= num;
        }

        //计算异或值，最后一个一出现的位置。因为是numi ^ numj异或，如果出现了1，说明在这一位上可以区分numi numj
        int xorLastOne = numOr == Integer.MIN_VALUE ? numOr : numOr & (-numOr);
        int type1 = 0;
        for (int num : nums) {
            if ((num & xorLastOne) == 0) {
                type1 ^= num;
            }
        }
        return new int[]{type1, type1 ^ numOr};
    }
}
