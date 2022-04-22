package tree;

import base.TreeNode;

/**
 * @author sunguiyong
 * @date 2022/4/20 3:10 下午
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 */
public class JudgeChildTree {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) {
            return false;
        }
        return isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    //如果一颗树里元素不重复，可以用这个方法
    public boolean isSub(TreeNode a, TreeNode b) {
        if (b == null) { //a不为null，b为null，说明b被遍历完了，且所有节点在a中都存在
            return true;
        }
        if (a == null) { //a为空，b不为空，说明b有节点，在a中没有
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSub(a.left, b.left) && isSub(a.right, b.right);
    }
}
