package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Not available in leetcode
 * https://www.youtube.com/watch?v=WPQ0i0CKIIg&list=PLtVcREFQDb6ZykTpgFhkQiPjLyrX1Y96D&index=5&ab_channel=CodingwithMinmer
 */
public class KthSmallestDigitsSum {

    public static void main(String[] args) {
        KthSmallestDigitsSum ks = new KthSmallestDigitsSum();
        System.out.println(ks.kthSallest(new int[]{555, 108, 30}, 2));
        System.out.println(ks.kthSallest(new int[]{1, 2, 3, 4, 5, 6}, 2));
        System.out.println(ks.kthSallest(new int[]{6, 36, 22222, 101, 80}, 3));
        System.out.println(ks.kthSallest(new int[]{111, 84, 21, 12, 3, 56, 2001, 10000}, 3));
    }

    private List<Integer> kthSallest(int[] nums, int k) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a.digitSum == b.digitSum) {
                return b.index - a.index;
            }
            return b.digitSum - a.digitSum;
        });
        for (int i=0;i<nums.length;i++) {
            int sum = getDigitSum(nums[i]);
            maxHeap.add(new Pair(i, nums[i], sum));
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        List<Pair> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
        }
        result.sort((a, b) -> {
            if (a.digitSum == b.digitSum) {
                return a.index - b.index;
            }
            return a.digitSum - b.digitSum;
        });
        return result.stream().map(a -> a.val).collect(Collectors.toList());
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    class Pair {
        int index;
        int val;
        int digitSum;

        Pair(int index, int val, int digitSum) {
            this.index = index;
            this.val = val;
            this.digitSum = digitSum;
        }
    }
}
