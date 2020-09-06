package com.leetcode.year_2020.august_challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 19/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumbersWithSameConsecutiveDifference {

    public static void main(String[] args) {
        numsSameConsecDiff(3, 7);
        numsSameConsecDiff(2, 1);
        numsSameConsecDiff(1, 0);
    }

    public static int[] numsSameConsecDiff(int N, int K) {
        List<Integer> sameConsecDiffItems = new ArrayList<>();
        dfs(N, K, "", sameConsecDiffItems);
        int[] answer = new int[sameConsecDiffItems.size()];
        int counter = 0;
        for (int result : sameConsecDiffItems) {
            answer[counter++] = result;
        }
        System.out.println(sameConsecDiffItems);
        return answer;
    }

    public static void dfs(int N, int K, String num, List<Integer> sameConsecDiffItems) {
        if (num.length() == N) {
            if (num.charAt(0) == '0' && num.length() > 1) return;
            sameConsecDiffItems.add(Integer.parseInt(num));
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (num.length() == 0 || Math.abs(i - Integer.parseInt("" + num.charAt(num.length() - 1))) == K) {
                dfs(N, K, num + i, sameConsecDiffItems);
            }
        }
    }
}
