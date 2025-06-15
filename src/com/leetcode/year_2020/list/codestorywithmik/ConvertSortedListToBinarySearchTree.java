package com.leetcode.year_2020.list.codestorywithmik;

import com.leetcode.year_2020.ListNode;
import com.leetcode.year_2020.TreeNode;

import static com.leetcode.year_2020.ListNode.getInstance;

public class ConvertSortedListToBinarySearchTree {

    public static void main(String[] args) {
        LeetCodeSolution leetCodeSolution = new LeetCodeSolution();
        TreeNode root = leetCodeSolution.buildBST(getInstance(new int[]{-10, -3, 0, 5, 9}));
        TreeNode.inorder(root);
    }

    static class LeetCodeSolution {
        public TreeNode buildBST(ListNode head) {
            return buildBST(head, null);
        }

        private TreeNode buildBST(ListNode head, ListNode tail) {
            if (head == tail) return null;

            ListNode middle = findMiddle(head, tail);
            TreeNode root = new TreeNode(middle.val);
            root.left = buildBST(head, middle);
            root.right = buildBST(middle.next, tail);
            return root;
        }

        private ListNode findMiddle(ListNode head, ListNode tail) {
            ListNode slow = head;
            ListNode fast = head;

            while (fast != tail && fast.next != tail) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

}
