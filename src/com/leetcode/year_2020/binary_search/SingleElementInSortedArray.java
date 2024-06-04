package com.leetcode.year_2020.binary_search;

/**
 * 540. Single Element in a Sorted Array
 * https://www.youtube.com/watch?v=AZOmHuHadxQ&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=9
 */
public class SingleElementInSortedArray {

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    public static int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (!hasPair(mid, nums)) {
                return nums[mid];
            }
            if (mid % 2 == 0) {
                // On even index, the i and i+1 should be equal
                if (nums[mid] != nums[mid + 1]) {
                    // Certainly that single value on left
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // On Odd Index, the i and i-1 should be equal
                if (nums[mid] != nums[mid - 1]) {
                    // Certainly that single value on left
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        return -1;

    }

    private static boolean hasPair(int i, int[] nums) {
        return nums[i] == ((i == 0) ? -1 : nums[i - 1])
                || nums[i] == ((i == nums.length - 1) ? -1 : nums[i + 1]);
    }
}
