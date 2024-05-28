package com.leetcode.year_2020.list;

import com.leetcode.linkedlist.LinkedListUtil;
import com.leetcode.linkedlist.LinkedListUtil.Node;

/**
 * - https://chatgpt.com/share/20d9fc3e-82ce-4ff9-9497-ededc73b05a4
 * - https://leetcode.com/discuss/interview-question/395171/google-phone-screen-delete-every-alternative-node-in-a-circular-linked-list
 * <p>
 * You are given a circular linked list. Your task is to delete every alternate node in the circular linked list starting from the head node.
 * <p>
 * A circular linked list is a linked list where the last node points back to the first node, creating a circular structure. You must ensure that the list remains circular after deletions.
 * <p>
 * Input
 * A circular linked list where each node contains an integer value.
 * Output
 * The circular linked list after deleting every alternate node.
 * <p>
 * <p>
 * Input 1
 * 1 -> 2 -> 3 -> 4 -> 5
 * ^                   |
 * |                   v
 * +-------------------+
 * Output1
 * 1 -> 3 -> 5
 * ^         |
 * |         v
 * +---------+
 * <p>
 * Input 2
 * 10 -> 20 -> 30 -> 40 -> 50 -> 60
 * ^                             |
 * |                             v
 * +-----------------------------+
 * <p>
 * Output2
 * 10 -> 30 -> 50
 * ^           |
 * |           v
 * +-----------+
 * <p>
 * Input3
 * 5
 * ^
 * |
 * + (self-loop)
 * Output3
 * 5
 * ^
 * |
 * + (self-loop)
 * <p>
 * Input4
 * 1 -> 2
 * ^    |
 * |    v
 * +----+
 * <p>
 * 1
 * ^
 * |
 * + (self-loop)
 */
public class DeleteEveryAlternativeNodeInCircularList {

    public static void main(String[] args) {
        deleteEveryAlternativeNodeInCircularList(1, 2, 3, 4, 5);
        deleteEveryAlternativeNodeInCircularList(10, 20, 30, 40, 50, 60);
        deleteEveryAlternativeNodeInCircularList(10, 30, 50);
//        deleteEveryAlternativeNodeInCircularList(5);
        deleteEveryAlternativeNodeInCircularList(1,2);
        deleteEveryAlternativeNodeInCircularList(100,200,300,400);

    }

    private static void deleteEveryAlternativeNodeInCircularList(Integer... data) {
        LinkedListUtil util = new LinkedListUtil();
        util.appendAll(data);
        // Add cycle
        joinTailToHead(util.head);
        System.out.println("Before Deleting....");
        printList(util.head);
        deleteAlternativeNode(util.head);
        System.out.println("After Deleting....");
        printList(util.head);
    }

    private static void joinTailToHead(Node head) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;
    }

    public static void deleteAlternativeNode(Node head) {
        Node workingPtr = head;
        if (workingPtr == null) return;
        Node next = head.next;
        if (next == null) return;

        do {
            workingPtr.next = next.next;
            workingPtr = workingPtr.next;
            next.next = null;
            next = workingPtr.next;
        } while (workingPtr != head && next != head);
    }

    private static void printList(Node head) {
        Node temp = head;
        Node prev = null;
        do {
            System.out.print(temp.data + "\t");
            prev = temp;
            temp = temp.next;
            if (temp == head) {
                System.out.println(String.format("===>[Tail %d joining to head %d]", prev.data, temp.data));
            }
        } while (temp != head);
        System.out.println();
    }
}
