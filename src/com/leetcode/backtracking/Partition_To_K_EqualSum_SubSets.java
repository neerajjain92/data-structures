package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * <p>
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * @author neeraj on 2019-05-13
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Partition_To_K_EqualSum_SubSets {

    private static List<List<Integer>> allPartitions = new ArrayList<>();
    private static List<Integer> currentPartition = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(canPartitionKEqualSumSubSets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(canPartitionKEqualSumSubSets(new int[]{2, 2, 2, 2, 3, 4, 5}, 4));
    }

//    public static void canPartitionKEqualSumSubSets(int[] nums, int partitions) {
//        int targetSum = IntStream.of(nums).sum();
//        Boolean result;
//
//         /*
//            1.) k can not be negative or 0 because we can not fill 0
//                or negative buckets
//            2.) k can not be greater than the length of the array,
//                we can't partition more buckets than there are elements in the array.
//            3.) sumOfAllArrayItems % k must be 0. If it is not then
//                we would have to have to fill buckets to a floating point sum which would be impossible with only
//                 integers.
//          */
//        if (partitions <= 0 || partitions > nums.length || targetSum % partitions != 0) {
//            result = false;
//        } else {
//            for (int i = 0; i < partitions; i++) {
//                currentPartition = new ArrayList<>();
//                allPartitions.add(currentPartition);
//            }
//            result = canPartition(0, partitions, nums, new boolean[nums.length], 0, targetSum / partitions);
//        }
//
//        LogUtil.logIt("Is " + partitions + " Equal Sum Partition possible for " +
//                Arrays.stream(nums).boxed().collect(Collectors.toList()) + " ? " + result);
//
//        LogUtil.logIt("Items used", true);
//        LogUtil.printList(allPartitions);
//    }
//
//    private static boolean canPartition(int iterationStart, int partitions, int[] nums, boolean[] used, int currentPartitionSum, int targetSum) {
//
//          /*
//            If we have filled all k - 1 buckets up to this point and we are now on
//            our last bucket, we can stop and be finished.
//
//            Example:
//            arr = [4, 3, 2, 3, 5, 2, 1]
//            k = 4
//            targetBucketSum = 5
//            If we get to the point in our recursion that k = 1 that means we have filled
//            k - 1 buckets (4 - 1 = 3). 3 buckets have been filled, each a value of 5 meaning
//            we have "eaten" 15 "points" of value from an array that sums to 20.
//            This means we have 5 "points" to extract from the array and that for sure will fill
//            the last bucket. So at the point there is 1 bucket left, we know we can complete the
//            partitioning (we don't have to though, we just want to know whether we can or not).
//          */
//        if (partitions == 1) {
//            for (int i = 0; i < used.length; i++) {
//                if (!used[i]) {
//                    used[i] = true;
//                    allPartitions.get(partitions - 1).add(nums[i]);
//                }
//            }
//
//            return true;
//        }
//
//         /*
//            Bucket full. continue the recursion with k - 1 as the new k value, BUT the
//            targetBucketSum stays the same. We just have 1 less bucket to fill.
//          */
//        if (currentPartitionSum == targetSum) {
//            return canPartition(0, partitions - 1, nums, used, 0, targetSum);
//        }
//
//        for (int i = iterationStart; i < nums.length; i++) {
//
//            // Let's explore all options
//            if (!used[i] && currentPartitionSum + nums[i] <= targetSum) { // If we haven't used this item, in the partition, let's choose it and explore further
//                allPartitions.get(partitions - 1).add(nums[i]);
//                used[i] = true;
//
//                if (canPartition(i + 1, partitions, nums, used, currentPartitionSum + nums[i], targetSum)) {
//                    return true;
//                }
//
//                // We have explored all options with this item, so let's back track
//                used[i] = false;
//            }
//        }
//
//        // There is no solution possible, so let's return false
//        return false;
//    }


    /**
     * Neetcode Intuition
     * https://www.youtube.com/watch?v=mBk4I0X46oI&ab_channel=NeetCode
     */
    public static boolean canPartitionKEqualSumSubSets(int[] nums, int k) {
        int totalSum = IntStream.of(nums).sum();

        // Since we have to partition into k equal sum  subset
        // so it should be divisible by k
        if (totalSum % k != 0) return false;

        // Move similar number together to avoid backtracking as that would yield same result
        Arrays.sort(nums);

        // For every partition we have 2 choices whether to include the number
        // or don't and the numbers used in 1 partitions shouldn't be used in another
        // partition, so we need to track that information
        boolean[] used = new boolean[nums.length];
        return canPartition(0, k, nums, used, 0, totalSum / k);
    }

    private static boolean canPartition(int index, int partition, int[] nums,
                                        boolean[] used, int currentPartitonSum, int targetSum) {
        if (partition == 0) {
            return true;
        }

        if (currentPartitonSum == targetSum) {
            // We finished this  partition, so try filling remaining one
            return canPartition(0, partition - 1, nums, used, 0, targetSum);
        }

        // here we have 2 decision to make, either choose or don't
        for (int i = index; i < nums.length; i++) {
            if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) continue;
            if (used[i] || currentPartitonSum + nums[i] > targetSum) {
                continue;
            }
            used[i] = true;
            if (canPartition(index + 1, partition, nums, used, currentPartitonSum + nums[i], targetSum)) {
                return true;
            }
            used[i] = false;
        }
        return false;
    }


}
