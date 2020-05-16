package com.leetcode.year_2020.MayChallenge.week3;

import com.leetcode.year_2020.ListNode;

/**
 * @author neeraj on 16/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.printList(oddEvenList(head));
    }

    public static ListNode oddEvenList(ListNode head) {
        // Single Node linked list
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode EVEN_HEAD = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = EVEN_HEAD;
        return head;
    }
}
