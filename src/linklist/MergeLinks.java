package linklist;

import base.ListNode;

/**
 * @author sunguiyong
 * @date 2022/4/7 4:22 下午
 */
public class MergeLinks {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] nodes, int left, int right) {
        if (left == right) {
            return nodes[left];
        }
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        return mergeTwo(merge(nodes, left, mid), merge(nodes, mid + 1, right));
    }

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
