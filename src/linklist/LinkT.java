package linklist;

import base.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LinkT {

    public static void main(String[] args) {
        int[] arr = new int[101];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        ListNode head;
        ListNode link = new ListNode(arr[0]);
        head = link;
        for (int i = 1; i < arr.length; i++) {
            link.next = new ListNode(arr[i]);
            link = link.next;
        }
        long start = System.currentTimeMillis();
        ListNode node = new LinkT().reverseKGroup(head, 7);
//        base.ListNode node1 = new linklist.LinkT().reverseKGroup1(head, 7);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
//        while (node != null) {
//            System.out.print(node.val + " ");
//            node = node.next;
//        }
        //单链表反转
//        base.ListNode pre = null;
//        base.ListNode cur = null;
//        while (head != null) {
//            cur = head;
//            head = head.next;
//            cur.next = pre;
//            pre = cur;
//
//        }
//        while (cur != null) {
//            System.out.println(cur.val);
//            cur = cur.next;
//        }
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode node = null;
        ListNode tail = null;
        ListNode ht = head;
        Stack<ListNode> stack = new Stack<>();
        List<ListNode> nodeList = new ArrayList<>();
        int count = 0;
        while (head != null) {
            if (stack.isEmpty()) {
                ht = head;
            }
            stack.push(head);
            count++;
            if (count == k) {
                count = 0;
                ht = null;
                while (!stack.isEmpty()) {
                    nodeList.add(stack.pop());
                }
            }
            head = head.next;
        }
        while (ht != null) {
            nodeList.add(ht);
            ht = ht.next;
        }
        for (ListNode ln : nodeList) {
            if (node == null) {
                node = ln;
                tail = node;
            } else {
                tail.next = ln;
                tail = tail.next;
            }
        }
        tail.next = null;
        return node;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode cur = null;
        ListNode pre = null;
        ListNode curTail = null;
        ListNode resHead = null;
        ListNode resTail = null;
        int count = 0;
        while (head != null) {
            count++;
            if (curTail == null) {
                curTail = head;
            }
            cur = head;
            head = head.next;
            cur.next = pre;
            pre = cur;
            if (count != k) {
                continue;
            }
            if (resHead == null) {
                resHead = cur;
            } else {
                resTail.next = cur;
            }
            curTail.next = null;
            resTail = curTail;
            curTail = null;
            pre = null;
            count = 0;

        }
        ListNode reverseH = null;
        ListNode reverseP = null;
        while (pre != null) {
            reverseH = pre;
            pre = pre.next;
            reverseH.next = reverseP;
            reverseP = reverseH;

        }
        if (reverseH != null) {
            if (resHead == null) {
                resHead = reverseH;
            } else {
                resTail.next = reverseH;
            }
        }
        return resHead;
    }
}