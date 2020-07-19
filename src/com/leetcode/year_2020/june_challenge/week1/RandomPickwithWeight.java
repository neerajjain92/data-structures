package com.leetcode.year_2020.june_challenge.week1;

import com.company.google.RandomStateNameGeneratorBasedOnProportionateToProbabilityOfTotalPopulation;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 * <p>
 * Probability Proportion
 *
 * @author neeraj on 05/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RandomPickwithWeight {

    private static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        /**
         * This problem is similar to {@link RandomStateNameGeneratorBasedOnProportionateToProbabilityOfTotalPopulation}
         * problem, where we will calculate the probability based on state population, here we can calculate them based
         * on weight of the input. And then divide the slot
         */

        int[] w = new int[]{2, 5, 3, 4};
//        Solution solution = new Solution(w);

        SolutionUsingBinarySearchAndPrefixSum solution = new SolutionUsingBinarySearchAndPrefixSum(w);


        Map<Integer, Integer> weightAndItsFrequency = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int index = solution.pickIndex();
            int actualWeight = w[index];
            weightAndItsFrequency.put(actualWeight, weightAndItsFrequency.getOrDefault(actualWeight, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : weightAndItsFrequency.entrySet()) {
            System.out.println(entry.getKey() + " -> " + (Double.parseDouble(df.format(entry.getValue() / 10000d)) * 100) + " %");
        }


        /**
         * Okay so there is  another approach as well using which we can solve this problem
         * via prefix sum and binary search
         */
    }


}

class SolutionUsingBinarySearchAndPrefixSum {

    int[] prefixSum;
    Random random;

    public SolutionUsingBinarySearchAndPrefixSum(int[] w) {
        /**
         * w[] = {2,5,3,4}
         * prefixSum[] = {2,7,10,14}
         */
        this.random = new Random();
        prefixSum = new int[w.length];
        prefixSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        /**
         * Assume we want to get the
         * w[] = {2,5,3,4}
         * prefixSum[] = {2,7,10,14}
         *
         * We want random value from 1 to 14...
         * so that we can divide values from
         * 0 to 2 -----to --> index 0
         * 3 to 7 ----- to ----> index 1
         * 8 to 10 ----- to-----> index 2
         * 11 to 14 ----- to ------> index 3
         */
        int len = prefixSum.length;
        int index = random.nextInt(prefixSum[len - 1]) + 1;
        int left = 0;
        int right = len - 1;
        ;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prefixSum[mid] == index) {
                return mid;
            }

            if (prefixSum[mid] < index) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

class Solution {

    private static DecimalFormat df = new DecimalFormat("0.00");
    double[] probabilityProportion;
    Map<Double, Integer> probabilityAndItsIndex = new HashMap<>();
    int[] w;

    public Solution(int[] w) {
        Arrays.sort(w); // Sorting weights in ascending order and we will use then in reverse order.
        this.w = w;
        int totalWeight = 0;
        for (int i : w) {
            totalWeight += i;
        }

        probabilityProportion = new double[w.length];
        double previousWeightage = 0.0d;
        for (int i = w.length - 1; i >= 0; i--) {
            probabilityProportion[w.length - 1 - i] = previousWeightage + (w[i]) / (double) totalWeight;
            probabilityAndItsIndex.putIfAbsent(probabilityProportion[w.length - 1 - i], i);
            previousWeightage = probabilityProportion[w.length - 1 - i];
        }

        for (int i = 0; i < w.length; i++) {
            System.out.println(w[i] + " -> " +
                    (Double.parseDouble(df.format(w[i] / Double.valueOf(totalWeight))) * 100) + " %");
        }
        System.out.println("==========================OUTPUT with its percentage=========================");
    }

    public int pickIndex() {
        double random = Math.random(); // equally distributed random value.
        double previousWeightage = 0.0d;
        for (int i = 0; i < probabilityProportion.length; i++) {
            if (random >= previousWeightage && random <= probabilityProportion[i]) {
                return probabilityAndItsIndex.get(probabilityProportion[i]);
            }
            previousWeightage = probabilityProportion[i];
        }
        return -1;
    }
}
