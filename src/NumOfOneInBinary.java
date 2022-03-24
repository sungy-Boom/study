/**
 * 二进制中1的个数
 *
 * @author sunguiyong
 * @date 2022/3/16 5:55 下午
 * https://leetcode-cn.com/problems/w3tCBm/
 */
public class NumOfOneInBinary {

    public static void main(String[] args) {
        //Arrays.stream(new NumOfOneInBinary().countBits(3)).boxed().forEach(System.out::println);
        System.out.println(new NumOfOneInBinary().singleNumber(new int[]{2, 2, 3, 2}));
    }

    public int[] countBits(int n) {
        int[] oneBitArr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int oneBitCount = 0;
            int temp = i;
            while (temp > 0) {
                temp = temp & (temp - 1);
                oneBitCount++;
            }
            oneBitArr[i] = oneBitCount;
        }
        return oneBitArr;
    }

    public int singleNumber(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int ans = 0;
        //右移的位数
        for (int i = 0; i < 32; i++) {
            int curIndexCount = 0;
            for (int num : nums) {
                curIndexCount += num >> i & 1;
            }
            //如果对3取余不为0，说明只出现一次的数，在当前位值为1
            if (curIndexCount % 3 != 0) {
                ans = ans | 1 << i;
            }
        }
        return ans;
    }
}
