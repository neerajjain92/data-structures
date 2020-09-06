package com.leetcode.year_2020.august_challenge;

import com.util.LogUtil;

/**
 * @author neeraj on 17/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DistributeCandiesToPeople {

    public static void main(String[] args) {
        distributeCandies(7, 4);
        distributeCandies(10, 3);
        distributeCandies(60, 4);
    }

    public static int[] distributeCandies(int candies, int num_people) {
        int[] answer = new int[num_people];
        int i = 0;
        int previousValue = 0;
        while (candies > 0) {
            answer[i] += Math.min(candies, ++previousValue);
            i = (i + 1) % num_people;
            candies -= previousValue;
        }
        LogUtil.printArray(answer);
        return answer;
    }
}
