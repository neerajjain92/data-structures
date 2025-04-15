package com.leetcode.year_2020;

/**
 * @author neeraj on 18/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RandomListNode {
    public int val;
    public RandomListNode next, random;

    public RandomListNode(int val) {
        this.val = val;
    }

    public void printList(RandomListNode head) {
        RandomListNode curr = head;
        String next = "-1";
        String random = "-1";
        System.out.println("NODE |   NEXT  |   RANDOM");
        while (curr != null) {
            next = curr.next != null ? curr.next.val+ " HashCode[ " + curr.next.hashCode() + " ]" : "-1";
            random = curr.random != null ? curr.random.val + " HashCode[ " + curr.random.hashCode() + " ]" : "-1";
            System.out.print(curr.val + " HashCode ["+ curr.hashCode() + " ]" + "    |    " + next + "    |    " + random);
            curr = curr.next;
            System.out.println();
        }
    }
}
