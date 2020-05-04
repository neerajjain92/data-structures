package com.leetcode.year_2020.MayChallenge.week1;

/**
 * @author neeraj on 04/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class JwelsAndStones {

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
    }

    public static int numJewelsInStones(String J, String S) {
        int counter = 0;
        for (char c : S.toCharArray()) {
            if (J.indexOf(c) >= 0) counter++;
        }
        return counter;
    }
}
