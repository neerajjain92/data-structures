package com.leetcode.year_2020.june_challenge.week1;

import com.leetcode.year_2020.ListNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 *
 * @author neeraj on 02/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DeleteNodeInLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(4);
        listNode.next = new ListNode(5);
        listNode.next.next = new ListNode(1);
        listNode.next.next.next = new ListNode(9);

        listNode.printList(listNode);
        deleteNode(listNode.next.next);
        listNode.printList(listNode);
    }

    public static void deleteNode(ListNode node) {
        if (node.next == null) return; // We don't have to delete the tail node
        node.val = node.next.val;
        node.next = node.next.next;
    }


}
