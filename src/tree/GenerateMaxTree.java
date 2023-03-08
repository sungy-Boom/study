package tree;

import base.TreeNode;

/**
 * 构造最大树
 * https://leetcode.cn/problems/maximum-binary-tree/
 *
 * @author sunguiyong
 * @date 2022/7/5 8:47 下午
 */
public class GenerateMaxTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {

        return generate(nums, 0, nums.length);
    }

    public TreeNode generate(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }

        //先查询数组，找到最大值的下标
        int maxNum = 0, maxNumIndex = start;
        for (int i = start; i < end; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
                maxNumIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxNum);
        TreeNode leftTree = generate(nums, start, maxNumIndex);
        TreeNode rightTree = generate(nums, maxNumIndex + 1, end);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }

    public static void main(String[] args) {
        TreeNode tree = new GenerateMaxTree().constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        System.out.println();
    }
}
