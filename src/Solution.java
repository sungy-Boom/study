import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        int[] t1 = new int[]{1, 2, 1};
        int[] t2 = new int[]{1, 1, 2};
        TreeNode node1 = new Solution().generate(t1);
        TreeNode node2 = new Solution().generate(t2);
        System.out.println(new Solution().isSameTree(node1, node2));
    }

    public TreeNode generate(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        TreeNode root = new TreeNode(arr[0]);
        nodeList.add(root);
        int i = 1;
        while (i < arr.length) {
            List<TreeNode> tmpList = new ArrayList<>();
            for (TreeNode node : nodeList) {
                if (i < arr.length) {
                    int left = arr[i];
                    if (left != 0) {
                        node.left = new TreeNode(left);
                    }
                }
                i += 1;
                if (i < arr.length) {
                    int right = arr[i];
                    if (right != 0) {
                        node.right = new TreeNode(right);
                    }
                }

                if (node.left != null) {
                    tmpList.add(node.left);
                }
                if (node.right != null) {
                    tmpList.add(node.right);
                }
                i += 1;
            }
            nodeList = tmpList;
        }
        return root;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        if (q.val != p.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}