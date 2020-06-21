package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 20/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PermutationSequence {

    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3));
        System.out.println(getPermutation(4, 9));
        System.out.println(getPermutation(9, 94626));
        System.out.println(getPermutation(9, 278082));
        System.out.println(getPermutation(9, 233794));
    }

    public static String getPermutation(int n, int k) {
        List<String> all = new ArrayList<>();
        boolean[] used = new boolean[n];
        permuteFindK(n, k, new StringBuilder(), all, used);
        return all.get(k - 1);
    }

    public static void permuteFindK(int n, int k, StringBuilder currentPermutation, List<String> all, boolean[] used) {
        if (all.size() >= k) return;
        if (currentPermutation.length() == n) {
            all.add(currentPermutation.toString());
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (used[i - 1]) continue;
            used[i - 1] = true;
            currentPermutation.append("" + i);
            permuteFindK(n, k, currentPermutation, all, used);
            currentPermutation.deleteCharAt(currentPermutation.length() - 1);
            used[i - 1] = false;
        }
    }
}
