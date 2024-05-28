package com.leetcode.year_2020.list;

import com.leetcode.year_2020.ListNode;

/**
 * https://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
 */
public class FlattenMultiLevelLinkedList {


    /**
     * // Main level: 10 -> 5 -> 12 -> 7 -> 11
     * //             |                     |
     * //             v                     v
     * // Level 1:    4 -> 20 -> 13        17 -> 6
     * //                  |      |        |
     * //                 v       v        v
     * // Level 2:        2       16       9 -> 8
     * //                          |       |
     * //                          v       v
     * // Level 3:                 3      19 -> 15
     * <p>
     * Output
     * 10->5->12->7->11->4->20->13->17->6->2->16->9->8->3->19->15
     */
    public static void main(String[] args) {
        // Creating the main level
        ListNode head = appendNodes(10, 5, 12, 7, 11);
        // Creating the child levels
        ListNode level1_a = appendNodes(4, 20, 13);
        ListNode level1_b = appendNodes(17, 6);

        ListNode level2_a = appendNodes(2);
        ListNode level2_b = appendNodes(16);
        ListNode level2_c = appendNodes(9, 8);

        ListNode level3_A = appendNodes(3);
        ListNode level3_b = appendNodes(19, 15);


        setChild(head, level1_a);                     // 10 -> 4
        setChild(head.next.next.next, level1_b);     // 7 -> 17
        setChild(level1_a.next, level2_a);          // 20 -> 2
        setChild(level1_a.next.next, level2_b);    // 13 -> 16
        setChild(level1_b, level2_c);             // 17 -> 9
        setChild(level2_b, level3_A);            // 16 -> 3
        setChild(level2_c, level3_b);           // 9 -> 19

        flattenList(head);
    }

    public static void flattenList(ListNode head) {
        ListNode workingPtr = head;
        ListNode tailPtr = head;
        ListNode childLevelWorkingPtr;

        // First move tailPtr to the end
        while (tailPtr.next != null) {
            tailPtr = tailPtr.next;
        }

        /**
         * We will continue iterating the workingPtr, and mind you we will explore each and every node with this
         * How ?????????
         * head                       tail
         * ---> | ---> | --> | --> | -->
         *                               \
         *                               |
         * <------------------------------(we need a way to just connect the tail to the childNode
         * |
         * |
         * ---> | --> | --> | --> | --> | --> (Same process in next level)
         */
        while (workingPtr != tailPtr) {
            if (workingPtr.child != null) {
                // if there is a child Node that's basically an new level
                // So let's connect tail to this
                tailPtr.next = workingPtr.child;

                // Now explore the child node level
                childLevelWorkingPtr = workingPtr.child;
                while (childLevelWorkingPtr.next != null) {
                    childLevelWorkingPtr = childLevelWorkingPtr.next;
                }
                tailPtr = childLevelWorkingPtr; // Since tail connected to the next level, workingPtr can easily keep moving forward
            }
            workingPtr = workingPtr.next;
        }
        head.printList(head);
    }

    public static ListNode appendNodes(int... values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            ListNode newNode = new ListNode(values[i]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        return head;
    }

    public static void setChild(ListNode parent, ListNode child) {
        parent.child = child;
    }
}

