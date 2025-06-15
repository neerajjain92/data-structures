package com.leetcode.year_2020.list.codestorywithmik;

import com.leetcode.year_2020.ListNode;

public class ReverseLinkedList2 {

    public static void main(String[] args) {
        ReverseLinkedList2 reverseLinkedList2 = new ReverseLinkedList2();
        ListNode head = ListNode.getInstance(new int[]{1, 2, 3, 4, 5});
        head = reverseLinkedList2.reverseBetween(head, 2, 4);
        head.printList(head);

        head = ListNode.getInstance(new int[]{3, 5});
        head = reverseLinkedList2.reverseBetween(head, 1, 1);
        head.printList(head);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;

        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode prev = dummyNode;

        // Find the leftNode
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // Reverse sublist
        ListNode leftNode = prev.next;
        prev.next = reverse(leftNode, right - left + 1);
        return dummyNode.next;
    }

    private ListNode reverse(ListNode start, int count) {
        ListNode prev = null;
        ListNode curr = start;

        while (count -- > 0)  {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // To basically join the remaining part after right Node;
        start.next = curr;

        return prev;
    }
}
