package tree;

import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunguiyong
 * @date 2022/7/21 9:00 下午
 */
public class GenerateTreeByPreAndInTreeOrder {

    private Map<Integer, Integer> headValIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            headValIndexMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, 0);
    }

    public TreeNode build(int[] preOrder, int preLeft, int preRight, int inLeft) {
        if (preLeft > preRight) {
            return null;
        }
        int val = preOrder[preLeft];
        int headValInOrderIndex = headValIndexMap.get(val);

        TreeNode tree = new TreeNode(val);
        int leftSize = headValInOrderIndex - inLeft;
        tree.left = build(preOrder, preLeft + 1, preLeft + leftSize, inLeft);
        tree.right = build(preOrder, preLeft + leftSize + 1, preRight, headValInOrderIndex + 1);
        return tree;
    }
}
