package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.HashMap;
import java.util.Map;

public class BinarySubarraysWithSum {

    public static void main(String[] args) {
        BinarySubarraysWithSum obj = new BinarySubarraysWithSum();
        System.out.println(obj.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        System.out.println(obj.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
        System.out.println(obj.numSubarraysWithSum(new int[]{0, 1, 0, 0, 1}, 2));

        System.out.println(obj.numSubarraysWithSumWithSlidingWindow(new int[]{1, 0, 1, 0, 1}, 2));
        System.out.println(obj.numSubarraysWithSumWithSlidingWindow(new int[]{0, 0, 0, 0, 0}, 0));
        System.out.println(obj.numSubarraysWithSumWithSlidingWindow(new int[]{0, 1, 0, 0, 1}, 2));
    }

    public int numSubarraysWithSumWithSlidingWindow(int[] nums, int goal) {
        int begin = 0, end = 0, sum = 0, total = 0, zeroCount = 0;
        while (end < nums.length) {
            sum += nums[end];
            // This OR condition beautifully handles
            // [0 0 0 0 0 0] goal = 0
            while (begin < end && (nums[begin] == 0 || sum > goal)) {
                if (nums[begin] == 0) {
                    zeroCount++;
                } else {
                    zeroCount = 0;
                }
                sum -= nums[begin++];
            }
            if (sum == goal) {
                total = total + 1 + zeroCount; // +1 because we reached to a valid subArray
                // and adding zeroCount so that we can check how many zeros are in prefix which isn't bothering
                // our subArraySUM
            }
            end++;
        }
        return total;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> cumulativeSumCount = new HashMap<>();
        cumulativeSumCount.put(0, 1);
        int sum = 0, result = 0;
        for (int i : nums) {
            sum += i;
            int diff = sum - goal;
            if (cumulativeSumCount.containsKey(diff)) {
                result += cumulativeSumCount.get(diff);
            }
            cumulativeSumCount.put(sum, cumulativeSumCount.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
