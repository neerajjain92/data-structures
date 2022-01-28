package com.leetcode.problems.medium;

/**
 * @author mohitkhosla01 on 07/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindFirstAndLastPositionOfElementInSortedArray_34 {

	public static void main(String[] args) {

		int[][] nums = {
				{},
				null,
				{1,2,3},
				{1,2,3},
				{1,2,3},
				{1,2,3},
				{5,7,7,8,8,10},
				{5,7,7,8,8,8,8,8,10},
				{5,8,8,8,8,8,8,8,10},
				{5,7,7,8,8,10},
				{1,2,3,3,3,4,5},
				{1,2,3,3,3,4,5},
				{1,2,3,3,3,4,5},
				{1,2,3,3,4,4,4},
				{1,2,3,3,3,4,4},
				{1,2,3,3,3,3,4},
				{1,1},
				{1,2},
				{1,1},
				{1,2},
				{1},
				{2},
				{2}
		};

		int[] target = {
				1,
				1,
				1,
				2,
				3,
				4,
				8,
				8,
				8,
				6,
				4,
				5,
				1,
				4,
				4,
				4,
				1,
				1,
				2,
				2,
				1,
				1,
				2
		};

		for(int i=0; i<nums.length; ++i) {
			int[] range = searchRange(nums[i], target[i]);
			System.out.println(range[0] + " " + range[1]);
		}
	}

	public static int[] searchRange(int[] nums, int target) {
		
		int[] range = {-1, -1};
		
		if(nums == null) {
			return range;
		}

		int b = 0, e = nums.length - 1;
		int b1 = 0, e1 = nums.length - 1;
		boolean leftFound = false;

		while(b <= e) {
			int mid = (b + e)/2;

			if(nums[mid] != target) {
				if(nums[mid] > target) {
					e = mid - 1;
					e1 = mid - 1;
				}
				else {
					b = mid + 1;
					b1 = mid + 1;
				}
			}
			else {
				if(range[0] == -1) {
					range[0] = mid;
				}
				if(range[1] == -1) {
					range[1] = mid;
				}

				if(!leftFound) {
					if(mid == 0 || nums[mid-1] != nums[mid]) {
						leftFound = true;
						range[0] = mid;
						b = b1;
						e = e1;
					}
					else {
						b = b1;
						e = mid;
					}
				}
				else {
					if(mid == nums.length - 1 || nums[mid+1] != nums[mid]) {
						range[1] = mid;
						break;
					}
					else {
						b = mid + 1;
						e = e1;
					}
				}
			}
		}

		return range;
	}
}
