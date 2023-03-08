package tree;

import base.TreeNode;

/**
 * @author sunguiyong
 * @date 2022/7/13 8:30 下午
 * https://leetcode.cn/problems/validate-binary-search-tree/
 */
public class ValidBst {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return valid(root, null, null);
    }

    public boolean valid(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }
        if (max != null && max.val <= root.val) {
            return false;
        }
        return valid(root.left, min, root)
                && valid(root.right, root, max);
    }
}
