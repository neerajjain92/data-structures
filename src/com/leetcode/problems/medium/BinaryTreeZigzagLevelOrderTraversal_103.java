package com.leetcode.problems.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import definition.TreeNode;

/**
 * @author mohitkhosla01 on 07/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BinaryTreeZigzagLevelOrderTraversal_103 {
	
	static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

	public static void main(String[] args) {

		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);

		//		TreeNode root = new TreeNode(2);
		//		root.left = new TreeNode(5);
		//		root.right = new TreeNode(20);
		//		root.left.left = new TreeNode(4);
		//		root.left.right = new TreeNode(8);
		//		root.right.left = new TreeNode(15);
		//		root.right.right = new TreeNode(12);
		//		root.left.left.left = new TreeNode(1);
		//		root.left.left.right = new TreeNode(3);
		//		root.left.right.left = new TreeNode(9);
		//		root.left.right.right = new TreeNode(7);

		//		TreeNode root = new TreeNode(2);

		//		TreeNode root = new TreeNode(5);
		//		root.left = new TreeNode(6);
		//		root.right = new TreeNode(7);
		//		root.left.left = new TreeNode(10);
		//		root.left.right = new TreeNode(3);
		//		root.right.left = new TreeNode(20);
		//		root.right.right = new TreeNode(8);
		//		root.left.left.left = new TreeNode(2);
		//		root.left.left.right = new TreeNode(9);
		//		root.left.right.left = new TreeNode(11);
		//		root.left.right.right = new TreeNode(12);
		//		root.right.right.left = new TreeNode(15);
		//		root.right.right.right = new TreeNode(16);

		//		TreeNode root = null;

		List<List<Integer>> numLists = new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);

		for(List<Integer> numList : numLists) {
			for(Integer num : numList) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

		List<List<Integer>> finalLists = new ArrayList<List<Integer>>();

		if(root != null) {

			int direction = 1;

			Stack<TreeNode> currentList = new Stack<TreeNode>();
			currentList.add(root);

			Stack<TreeNode> nextLevelList = new Stack<TreeNode>();

			List<Integer> listToBeAdded = new ArrayList<Integer>();

			while(!currentList.isEmpty()) {

				TreeNode node = currentList.pop();
				listToBeAdded.add(node.val);

				if(direction == -1) {
					if(node.right != null) {
						nextLevelList.add(node.right);
					}
					if(node.left != null) {
						nextLevelList.add(node.left);
					}
				}
				else {
					if(node.left != null) {
						nextLevelList.add(node.left);
					}
					if(node.right != null) {
						nextLevelList.add(node.right);
					}
				}

				if(currentList.isEmpty()) {
					currentList = nextLevelList;
					nextLevelList = new Stack<TreeNode>();
					direction *= -1;
					finalLists.add(listToBeAdded);
					listToBeAdded = new ArrayList<Integer>();
				}
			}
		}

		return finalLists;
	}
}
