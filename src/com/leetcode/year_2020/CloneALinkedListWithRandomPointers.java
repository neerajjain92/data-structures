package com.leetcode.year_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 18/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CloneALinkedListWithRandomPointers {

    static Map<RandomListNode, RandomListNode> OG_TO_CN;

    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(1);
        RandomListNode head_2 = new RandomListNode(2);
        RandomListNode head_3 = new RandomListNode(3);
        RandomListNode head_4 = new RandomListNode(4);
        RandomListNode head_5 = new RandomListNode(5);

        head.next = head_2;
        head_2.next = head_3;
        head_3.next = head_4;
        head_4.next = head_5;

        head.random = head_3;
        head_2.random = head_4;
        head_3.random = head;


        head.printList(head);
        head = copyRandomListConstantSpace(head);
        head.printList(head);

//        head = copyRandomListWithMap(head);
//        head.printList(head);

    }

    public static RandomListNode copyRandomListConstantSpace(RandomListNode head) {
        RandomListNode curr = head;
        RandomListNode nextOfCurr;
        RandomListNode clonedHead = null;

        // First pass to set the next of original node to the cloned node and
        // keeping the original node's next to the cloned node next.
        while (curr != null) {
            nextOfCurr = curr.next;
            curr.next = new RandomListNode(curr.val);
            curr.next.next = nextOfCurr;
            curr = nextOfCurr;
        }

        // Second pass to keep the random pointer in cloned list
        curr = head;
        while (curr != null && curr.next != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }


        // Now let's make the original list intact.
        curr = head;
        while (curr != null) {
            if (clonedHead == null) { // This is our main head to the cloned copy.
                clonedHead = curr.next;
            }
            RandomListNode clonedNode = curr.next;
            curr.next = curr.next.next;
            if (curr.next != null) {
                clonedNode.next = curr.next.next;
            }
            curr = curr.next;
        }
        return clonedHead;
    }

    public static RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode clonedCopy = new RandomListNode(-1);
        RandomListNode originalCurr = head;
        RandomListNode clonedNode;

        Map<RandomListNode, RandomListNode> nodeToRandomNodeMap = new HashMap<>();

        // First Let's keep all the copy in memory for first pass
        while (originalCurr != null) {
            nodeToRandomNodeMap.put(originalCurr, new RandomListNode(originalCurr.val));
            originalCurr = originalCurr.next;
        }

        originalCurr = head;
        while (originalCurr != null) {
            clonedNode = nodeToRandomNodeMap.get(originalCurr);
            if (clonedCopy.next == null) {
                clonedCopy.next = clonedNode;
            }
            clonedNode.next = nodeToRandomNodeMap.get(originalCurr.next);
            clonedNode.random = nodeToRandomNodeMap.get(originalCurr.random);
            originalCurr = originalCurr.next;
        }
        // Now since we have the map
        return clonedCopy.next;
    }

    private static RandomListNode copyRandomListWithMap(RandomListNode head) {
        RandomListNode t1 = head;
        RandomListNode dummyHead = new RandomListNode(-1);
        RandomListNode t2 = dummyHead;
        OG_TO_CN = new HashMap<>();
        while (t1 != null) {
            boolean containsT1 = contains(t1);
            boolean hasRandom = t1.random != null;
            RandomListNode clonedRandomListNode = containsT1 ? get(t1) : new RandomListNode(t1.val);

            t2.next = clonedRandomListNode;
            put(t1, clonedRandomListNode);

            boolean containsT1_RANDOM = contains(t1.random);
            RandomListNode randomRandomListNode = containsT1_RANDOM ? get(t1.random) : (hasRandom ? new RandomListNode(t1.random.val) : null);
            clonedRandomListNode.random = randomRandomListNode;

            if (hasRandom) {
                put(t1.random, randomRandomListNode);
            }
            t1 = t1.next;
            t2 = t2.next;
        }
        return dummyHead.next;
    }

    private static boolean contains(RandomListNode node) {
        return OG_TO_CN.containsKey(node);
    }

    private static RandomListNode get(RandomListNode node) {
        return OG_TO_CN.get(node);
    }

    private static void put(RandomListNode node1, RandomListNode node2) {
        OG_TO_CN.putIfAbsent(node1, node2);
    }
}
