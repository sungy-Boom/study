package base;

/**
 * @author sunguiyong
 * @date 2022/4/7 3:44 下午
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode generate(int[] arr) {
        ListNode head = new ListNode();
        ListNode tail = head;
        for (int i : arr) {
            tail.next = new ListNode(i);
            tail = tail.next;
        }
        return head.next;
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}