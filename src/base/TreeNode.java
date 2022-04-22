package base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunguiyong
 * @date 2022/4/19 3:35 下午
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode() {
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>() {
            {
                add(3);
                add(9);
                add(20);
                add(null);
                add(null);
                add(15);
                add(7);
            }
        };

        TreeNode tree = TreeNode.generate(list);
        tree.print(tree);
    }

    public static TreeNode generate(List<Integer> dataList) {
        return gen(dataList, 0);
    }

    private static TreeNode gen(List<Integer> dataList, int index) {
        if (index > dataList.size() - 1 || dataList.get(index) == null) {
            return null;
        }

        TreeNode t = new TreeNode(dataList.get(index));

        t.left = gen(dataList, 2 * index + 1);
        t.right = gen(dataList, 2 * index + 2);
        return t;
    }

    public void print(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        print(treeNode.left);
        print(treeNode.right);
    }
}