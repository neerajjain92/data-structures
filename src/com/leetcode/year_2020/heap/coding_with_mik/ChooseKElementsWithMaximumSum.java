package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ChooseKElementsWithMaximumSum {

    public static void main(String[] args) {
        ChooseKElementsWithMaximumSum obj = new ChooseKElementsWithMaximumSum();
        obj.findMaxSum(new int[]{4, 2, 1, 5, 3}, new int[]{10, 20, 30, 40, 50}, 2);
    }


    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            tuples.add(new Tuple(nums1[i], nums2[i], i));
        }
        tuples.sort(Comparator.comparingInt(a -> a.num1_value));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long[] result = new long[nums1.length];
        long sum = 0L;
        for (int i = 0; i < tuples.size(); i++) {
            Tuple currentTuple = tuples.get(i);
            if (i > 0 && tuples.get(i - 1).num1_value == currentTuple.num1_value) {
                // Since number is same so their result will also be same
                result[currentTuple.index] = result[tuples.get(i - 1).index];
            } else {
                result[currentTuple.index] = sum;
            }
            sum += currentTuple.num2_value;
            minHeap.add(currentTuple.num2_value);
            if (minHeap.size() > k) {
                sum -= minHeap.poll();
            }
        }
        return result;
    }

    static class Tuple {
        int num1_value, num2_value, index;

        public Tuple(int num1_value, int num2_value, int index) {
            this.num1_value = num1_value;
            this.num2_value = num2_value;
            this.index = index;
        }
    }
}
