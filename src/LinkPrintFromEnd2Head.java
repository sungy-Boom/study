import java.util.Stack;

/**
 * @author sunguiyong
 * @date 2022/4/8 5:41 下午
 */
public class LinkPrintFromEnd2Head {
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return null;
        }

        Stack<ListNode> nodeStack = new Stack<>();
        while (head != null) {
            nodeStack.push(head);
            head = head.next;
        }
        int size = nodeStack.size();
        int[] arr = new int[size];
        for (int i1 = 0; i1 < size; i1++) {
            arr[i1] = nodeStack.pop().val;
        }
        /*for (ListNode node : nodeStack) {
            arr[i++] = node.val;
        }*/
        return arr;
    }
}
