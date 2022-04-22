package tree;

import base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author sunguiyong
 * @date 2022/4/19 4:40 下午
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 */
public class PrintTreeUpDown {

    //https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-i-lcof/
    public int[] levelOrderArr(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        List<TreeNode> listNode = new ArrayList<>();

        Queue<TreeNode> treeQueue = new ArrayDeque<>();
        treeQueue.add(root);
        while (!treeQueue.isEmpty()) {
            TreeNode cur = treeQueue.poll();
            listNode.add(cur);
            if (cur.left != null) {
                treeQueue.add(cur.left);
            }
            if (cur.right != null) {
                treeQueue.add(cur.right);
            }
        }
        int[] arr = new int[listNode.size()];
        for (int i = 0; i < listNode.size(); i++) {
            arr[i] = listNode.get(i).val;
        }
        return arr;
    }

    //https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
    public List<List<Integer>> levelOrderii(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> listNode = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();

        Queue<TreeNode> treeQueue = new ArrayDeque<>();
        treeQueue.add(root);
        treeQueue.add(new TreeNode(Integer.MIN_VALUE));
        while (!treeQueue.isEmpty()) {
            TreeNode cur = treeQueue.poll();
            if (cur.val == Integer.MIN_VALUE) {
                if (!treeQueue.isEmpty()) {
                    treeQueue.add(new TreeNode(Integer.MIN_VALUE));
                }
                list.add(listNode);
                listNode = new ArrayList<>();
                continue;
            }
            listNode.add(cur.val);
            if (cur.left != null) {
                treeQueue.add(cur.left);
            }
            if (cur.right != null) {
                treeQueue.add(cur.right);
            }
        }
        return list;
    }

    //https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> listNode = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();

        Queue<TreeNode> treeQueue = new ArrayDeque<>();
        treeQueue.add(root);
        treeQueue.add(new TreeNode(Integer.MIN_VALUE));
        while (!treeQueue.isEmpty()) {
            TreeNode cur = treeQueue.poll();
            if (cur.val == Integer.MIN_VALUE) {
                if (!treeQueue.isEmpty()) {
                    treeQueue.add(new TreeNode(Integer.MIN_VALUE));
                }
                list.add(listNode);
                listNode = new ArrayList<>();
                continue;
            }
            listNode.add(cur.val);
            if (cur.left != null) {
                treeQueue.add(cur.left);
            }
            if (cur.right != null) {
                treeQueue.add(cur.right);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if ((i + 1) % 2 != 0) {
                continue;
            }
            List<Integer> tmpList = list.get(i);
            int mid = tmpList.size() / 2;
            int right = tmpList.size() - 1;
            for (int i1 = 0; i1 < mid; i1++) {
                int tmp = tmpList.get(i1);
                tmpList.set(i1, tmpList.get(right));
                tmpList.set(right, tmp);
                right--;
            }
        }
        return list;
    }
}
