package com.leetcode.year_2020.prefix_sum_technique;

import java.util.*;

public class RandomPickIndex {

    public static void main(String[] args) {

    }

    class Solution {

        private Random random = new Random();
        private Map<Integer, List<Integer>> itemToIndexListMap = new HashMap<>();
        public Solution(int[] nums) {
            for(int i = 0; i < nums.length; i++) {
                itemToIndexListMap.putIfAbsent(nums[i], new ArrayList<>());
                itemToIndexListMap.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> allIndexes = itemToIndexListMap.get(target);
            int randomIndex = random.nextInt(allIndexes.size());
            return allIndexes.get(randomIndex);
        }
    }

}
