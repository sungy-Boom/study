package linklist;

import base.ListNode;

/**
 * @author sunguiyong
 * @date 2022/4/7 3:45 下午
 */
public class MergeTwoLink {
    //合并两个有序链表
    public ListNode mergeTwo(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }

        ListNode head = new ListNode();
        ListNode tail = head;

        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                tail.next = node1;
                node1 = node1.next;
            } else {
                tail.next = node2;
                node2 = node2.next;
            }
            tail = tail.next;
        }
        tail.next = node1 != null ? node1 : node2;
        return head.next;
    }
}
