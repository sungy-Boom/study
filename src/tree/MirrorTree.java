package tree;

import base.TreeNode;

/**
 * @author sunguiyong
 * @date 2022/4/20 4:27 下午
 * https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 */
public class MirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        return doMirror(root, root);
    }

    public TreeNode doMirror(TreeNode root, TreeNode newRoot) {
        TreeNode tmp = root.left;
        newRoot.left = root.right;
        newRoot.right = tmp;

        newRoot.left = mirrorTree(root.left);
        newRoot.right = mirrorTree(root.right);
        return root;
    }
}
