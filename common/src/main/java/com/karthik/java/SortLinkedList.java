package com.karthik.java;

import java.util.PriorityQueue;

public class SortLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    private ListNode getMiddle(ListNode head) {
        ListNode slow, fast, midPrev;
        slow = fast = head;
        midPrev = null;
        while (fast != null && fast.next != null) {
            midPrev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        midPrev.next = null;
        return slow;
    }

    private ListNode getPrev(ListNode head, ListNode node) {
        while (head != null && head.next != node)
            head = head.next;
        return head;
    }


    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        PriorityQueue<ListNode> minPQ = new PriorityQueue<>((c, d) -> c.val - d.val);
        minPQ.offer(a);
        minPQ.offer(b);
        while (!minPQ.isEmpty()) {
            ListNode l = minPQ.poll();
            tail.next = l;
            tail = l;
            if (l.next != null)
                minPQ.offer(l.next);
        }
        return dummyHead.next;
    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = getMiddle(head);
        head = sortList(head);
        mid = sortList(mid);
        return merge(head, mid);
    }
}
