/**
 * @author sunguiyong
 * @date 2022/3/18 5:46 下午
 */
public class Main {

    public static void main(String[] args) {

        //计算字符数组中，无相同位的两串最大乘积
        /*System.out.println(new MaxMultiOfTwoStr().maxProduct(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"}));
        System.out.println(new MaxMultiOfTwoStr().maxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}));
        System.out.println(new MaxMultiOfTwoStr().maxProduct(new String[]{"a", "aa", "aaa", "aaaa"}));
        System.out.println(new MaxMultiOfTwoStr().maxProduct(new String[]{}));

        //计算数组中，两数相加为目标值
        for (int i : new SortedArrOfTwoNumSum().twoSum(new int[]{1, 2, 4, 6, 10}, 8)) {
            System.out.println(i);
        }*/

        //三个数和为0
        /*for (List<Integer> integers : new ThreeNumSumEqualZero().threeSum(new int[]{-1, 0, 1, 2, -1, -4})) {
            System.out.println(Arrays.toString(integers.toArray()));
        }*/

        //和为target的最小连续子数组
        /*System.out.println(new SumGtTarget().minSubArrayLen(7, new int[]{1, 1, 1, 1, 7}));
        System.out.println(new SumGtTarget().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(new SumGtTarget().minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println(new SumGtTarget().minSubArrayLen(11, new int[]{1, 4, 4}));
        System.out.println(new SumGtTarget().minSubArrayLen(11, new int[]{1, 2, 3, 4, 5}));*/

        //数组内乘积小于 k 的连续的子数组的个数
        //System.out.println(new NumOfSubArrMultiLtTarget().numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));

        //数组中和为 k 的连续子数组的个数
        //System.out.println(new SumEtTargetOfSubArr().subarraySum(new int[]{1, 1, 1}, 2));
        //System.out.println(new SumEtTargetOfSubArr().subarraySum(new int[]{2}, 2));
        System.out.println(new SumEtTargetOfSubArr().subarraySum(new int[]{1, -1, 0}, 0));
        //System.out.println(new SumEtTargetOfSubArr().subarraySum(new int[]{1}, 0));
    }
}
