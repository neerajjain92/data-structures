package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumSubsequenceScore {

    public static void main(String[] args) {
        MaximumSubsequenceScore obj = new MaximumSubsequenceScore();
        System.out.println(obj.maxScore(new int[]{1, 3, 3, 2}, new int[]{2, 1, 3, 4}, 3));
        System.out.println(obj.maxScore(new int[]{4, 2, 3, 1, 1}, new int[]{7, 5, 10, 9, 6}, 1));
        System.out.println(obj.maxScore(new int[]{2, 1, 14, 12}, new int[]{11, 7, 13, 6}, 1));
        System.out.println(obj.maxScore(new int[]{2, 1, 14, 12}, new int[]{11, 7, 13, 6}, 3));
    }

    /*
     * Intuition:
     * we have to pick any k numbers from nums1 and out of these k the smalles number on same index in nums2 as the multiplier
     * Since we know multiplication has a greater impact to get maxSum, so how about i pick the maximumElement from nums2 to be the part of my k selections
     * so that my answer is maximum, So i know for sure i should be sorting the array using nums2, but always rememeber they have to keep their nums1 value together
     * else everything will be messed up
     *
     * So Make a Pair(num1, nums2) sort the list based on nums2
     *
     * Now what you should be doing is to maximimze the product we should be choosing a value which is minimum in the k values till now but maximum then all
     * and we try for all such values till the end
     *
     * So for nums1 = {2, 1, 14, 12} and nums2 = {11, 7, 13, 6}
     * once i sort [{14, 13}, {2, 11}, {1, 7}, {12, 6}}
     *
     * Now you just answer if my k = 3, and if i pick 13 as one of the multiplier are there any 2 bigger elements than 13 which makes 13 the smallest element in group of k picked items
     * No right so essentially we should only be starting exploring from k-1th (0-indexed) items for our exploration, till than we can ignore and simply do the summation of nums1 values,
     * because 13 might not be the multiplier but 7 can become the multiplier and it's smallest amongst [{14, 13}, {2, 11}, {1, 7}] so sum will help us
     *
     * Now post that when we move forward from 7 we should take 6 now logically but won't that make our sum of k+1 elements but we can only take just k
     * so we should remove 1 element whom should we remove ?? ???? {14, 13} since we added them first, Nopes if you remove {13} you are essentially loosing the highest value in sum which is 14
     * so we should be removing the smallest element, and which datastructure gets access to min and max items in O(1) time, yes it's Heap
     *
     * So we should be using MinHep to keep only k largest element in the queue.
     *
     */
    public long maxScore(int[] nums1, int[] nums2, int k) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            pairs.add(new Pair(nums1[i], nums2[i]));
        }

        // Step 1
        pairs.sort((a, b) -> b.num2 - a.num2);

        // Step 2:  Min Heap to keep the k largest nums1 so far
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long sum = 0, max = 0;
        for (Pair pair : pairs) {
            sum += pair.num1;
            minHeap.add(pair.num1);

            if (minHeap.size() > k) { // We crossed k kick the smallest nums1 entry so far out and remove from sum
                sum -= minHeap.poll();
            }
            if (minHeap.size() == k) {
                long score = sum * pair.num2;
                max = Math.max(max, score);
            }
        }
        return max;
    }

    static class Pair {
        int num1, num2;

        public Pair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
    }
}
