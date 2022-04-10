/**
 * @author sunguiyong
 * @date 2022/4/8 6:03 下午
 */
public class LinkReverse {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = null;
        while (head != null) {
            ListNode originNext = head.next;
            head.next = tail;
            tail = head;
            head = originNext;
        }
        return tail;
    }
}
