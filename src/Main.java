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
        //System.out.println(new SumEtTargetOfSubArr().subarraySum(new int[]{1, -1, 0}, 0));
        //System.out.println(new SumEtTargetOfSubArr().subarraySum(new int[]{1}, 0));

        //包含min函数的栈
        /*MinStack minStack = new MinStack();
        minStack.push(12);
        minStack.push(9);
        minStack.push(10);
        minStack.push(8);

        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
        minStack.pop();*/

        //merger两个有序链表
        /*ListNode node1 = ListNode.generate(new int[]{1, 2, 3, 4, 7, 8, 9});
        ListNode node2 = ListNode.generate(new int[]{2,32,34,212});
        MergeTwoLink mergeTwoLink = new MergeTwoLink();
        ListNode res = mergeTwoLink.mergeTwo(node1, node2);
        ListNode.print(res);*/

        //合并多个有序链表
        /*ListNode[] list = new ListNode[3];
        list[0] = ListNode.generate(new int[]{1, 4, 5});
        list[1] = ListNode.generate(new int[]{1, 3, 4});
        list[2] = ListNode.generate(new int[]{2, 6});
        MergeLinks mergeLinks = new MergeLinks();
        ListNode node = mergeLinks.mergeKLists(list);
        ListNode.print(node);*/

        //链表反向打印
        /*ListNode node = ListNode.generate(new int[]{3, 1, 2, 4});
        LinkPrintFromEnd2Head end2Head = new LinkPrintFromEnd2Head();
        int[] arr = end2Head.reversePrint(node);
        for (int si : arr) {
            System.out.println(si);
        }*/

        //单链表反转
        ListNode node = ListNode.generate(new int[]{3, 1, 2, 4});
        LinkReverse linkReverse = new LinkReverse();
        node = linkReverse.reverseList(node);
        ListNode.print(node);
    }
}
