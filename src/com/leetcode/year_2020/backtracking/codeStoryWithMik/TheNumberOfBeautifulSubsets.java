package com.leetcode.year_2020.backtracking.codeStoryWithMik;

import java.util.*;

public class TheNumberOfBeautifulSubsets {


    Map<Integer, Integer> itemFreq;

    public static void main(String[] args) {
        TheNumberOfBeautifulSubsets subset = new TheNumberOfBeautifulSubsets();
        System.out.println(subset.beautifulSubsets(new int[]{1, 1, 2, 3}, 1));
        System.out.println(subset.beautifulSubsets(new int[]{2, 4, 6}, 2));
    }

    public int beautifulSubsets(int[] nums, int k) {
        // The Map is required to qucikly check without iterating the whole subset
        // that if adding new number will voilate your |num1 - num2| == k
        // so we can quickly check if map#containKey(num1 - k) and map#containKey(num1+k)
        // and there can be duplicates as well in the question, so pretty easy solution would
        // be to maintain frequency as well instead of just a subset
        // Else this will fail
        /*
         * nums = [1,1,2,3]
         * k = 1
         * Expected
         * 8
         */
        itemFreq = new HashMap<>();
        return solve(0, nums, k) - 1; // For the empty Subset explained below
    }

    private int solve(int index, int[] nums, int k) {
        if (index == nums.length) {
            // we reached at end so we must have found at least 1 subset
            // if we didn't find any other subset and this turn out to be empty subset
            // For [2] k = 2, the output should be 0 so we substract 1 from the parent caller
            // but for [3] k = 2, the NOT-TAKE call will give you +1 and TAKE call will also give you +1 so parent will
            // -1 and hence the final answer would be 1, which is correct
            return 1;
        }

        int count = 0;

        // Don't take
        count += solve(index + 1, nums, k); // here k won't matter since we are choosing not to take it

        // Now we should take it but if we blindly take it this would be a generate all subset problem
        // but we should be smart about it that we want to follow the rule of |num1 - nums2| != k in the subset
        int currItem = nums[index];
        if (!itemFreq.containsKey(currItem - k) && !itemFreq.containsKey(currItem + k)) {
            // Choose
            itemFreq.put(currItem, itemFreq.getOrDefault(currItem, 0) + 1);

            // Explore
            count += solve(index + 1, nums, k); // here k matters and we checked with map

            // UnChoose
            itemFreq.put(currItem, itemFreq.get(currItem) - 1);
            if (itemFreq.get(currItem) == 0) {
                itemFreq.remove(currItem);
            }
        }
        return count;
    }

//    public int beautifulSubsets(int[] nums, int k) {
//        // The Map is required to qucikly check without iterating the whole subset
//        // that if adding new number will voilate your |num1 - num2| == k
//        // so we can quickly check if map#containKey(num1 - k) and map#containKey(num1+k)
//        // and there can be duplicates as well in the question, so pretty easy solution would
//        // be to maintain frequency as well instead of just a subset
//        // Else thihs will fail
//        /*
//         * nums = [1,1,2,3]
//         * k = 1
//         * Expected
//         * 8
//         */
//        itemFreq = new HashMap<>();
//        List<List<Integer>> res = new ArrayList<>();
//        List<Integer> curr = new ArrayList<>();
//        solve(0, nums, k, curr, res);
//        return res.size();
//    }
//
//    /**
//     * [2,4,6], k = 2
//     * [1], k = 1
//     */
//    private void solve(int index, int[] nums, int k, List<Integer> curr, List<List<Integer>> res) {
//        if (!curr.isEmpty()) {
//            res.add(new ArrayList<>(curr));
//        }
//
//        for (int i = index; i < nums.length; i++) {
//            if (!itemFreq.containsKey(nums[i] - k) && !itemFreq.containsKey(nums[i] + k)) {
//                itemFreq.put(nums[i], itemFreq.getOrDefault(nums[i], 0) + 1);
//                curr.add(nums[i]);
//                solve(i + 1, nums, k, curr, res);
//                curr.remove(curr.size() - 1);
//                itemFreq.put(nums[i], itemFreq.get(nums[i]) - 1);
//                if (itemFreq.get(nums[i]) == 0) {
//                    itemFreq.remove(nums[i]);
//                }
//            }
//        }
//    }
}
