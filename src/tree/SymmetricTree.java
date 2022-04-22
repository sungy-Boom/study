package tree;

import base.TreeNode;

/**
 * @author sunguiyong
 * @date 2022/4/20 4:39 下午
 * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return judgeSymmetric(root, root);
    }

    public boolean judgeSymmetric(TreeNode root, TreeNode symmetricTree) {
        if (root == null && symmetricTree == null) {
            return true;
        }
        if (root == null || symmetricTree == null) {
            return false;
        }

        return root.val == symmetricTree.val
                && judgeSymmetric(root.left, symmetricTree.right)
                && judgeSymmetric(root.right, symmetricTree.left);
    }
}
