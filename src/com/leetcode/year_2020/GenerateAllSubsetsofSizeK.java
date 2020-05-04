package com.leetcode.year_2020;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer value n and an integer value k, generate all subsets of the sequence from 1…n of size k.
 * <p>
 * https://backtobackswe.com/platform/content/generate-all-subsets-of-size-k
 * <p>
 * This is analogous to extracting all sets of size k from the sequence's power set (the set of all subsets).
 *
 * @author neeraj on 12/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GenerateAllSubsetsofSizeK {

    public static void main(String[] args) {
        System.out.println(subsetsSizeK(10, 5));
    }

    public static List<List<Integer>> subsetsSizeK(int n, int k) {
        List<List<Integer>> subsets = new ArrayList<>();
        produceSubsets(n, k, 1, subsets, new ArrayList<>());
        return subsets;
    }

    private static void produceSubsets(int n, int k, int currentPointer, List<List<Integer>> allSubSets, ArrayList<Integer> subset) {
        if (k == 0) {
            allSubSets.add(new ArrayList<>(subset));
            return;
        }
        for (int i = currentPointer; i <= n - k; i++) {
            System.out.println("RUnning Loop upto " + (n - k + 1));
            subset.add(i);
            produceSubsets(n, k - 1, i + 1, allSubSets, subset);
            subset.remove(subset.size() - 1);
        }
    }
}
