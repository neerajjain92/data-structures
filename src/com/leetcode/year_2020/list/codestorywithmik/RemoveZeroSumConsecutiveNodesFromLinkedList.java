package com.leetcode.year_2020.list.codestorywithmik;

import com.leetcode.year_2020.ListNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/
 */
public class RemoveZeroSumConsecutiveNodesFromLinkedList {

    public static void main(String[] args) {
        RemoveZeroSumConsecutiveNodesFromLinkedList list = new RemoveZeroSumConsecutiveNodesFromLinkedList();
        ListNode removed = list.removeZeroSumSublists(ListNode.getInstance(new int[]{1, 2, -3, 3, 1}));
        removed.printList(removed);

        removed = list.removeZeroSumSublists(ListNode.getInstance(new int[]{1, 2, 3, -3, 4}));
        removed.printList(removed);

        removed = list.removeZeroSumSublists(ListNode.getInstance(new int[]{1, 2, 3, -3, -2}));
        removed.printList(removed);

        removed = list.removeZeroSumSublists(ListNode.getInstance(new int[]{1,-1}));
        if (removed != null) {
            removed.printList(removed);
        }
    }

    /**
     * Kindaa similar to the prefix sum technique using a Map
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        Map<Integer, ListNode> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, dummyNode);

        int prefixSum = 0;
        while (head != null) {
            prefixSum += head.val;

            if (prefixSumMap.containsKey(prefixSum)) {
                ListNode start = prefixSumMap.get(prefixSum);
                ListNode temp = start;
                int sum = prefixSum;
                while(temp != head) {
                    temp = temp.next;
                    sum += temp.val;
                    if (temp != head) {
                        // Since this head is not yet addded in the Map so let's not remove it also
                        prefixSumMap.remove(sum);
                    }
                }

                // At last simple
                start.next = head.next;
            } else {
                prefixSumMap.put(prefixSum, head);
            }
            head = head.next;
        }
        return dummyNode.next;
    }
}
