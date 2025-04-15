package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/bag-of-tokens/description/
 * https://www.youtube.com/watch?v=LCx1WzlYgvw&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU
 * 948. Bag of Tokens
 */
public class BagOfTokens {

    public static void main(String[] args) {
        System.out.println(bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
        System.out.println(bagOfTokensScore(new int[]{100}, 50));
        System.out.println(bagOfTokensScore(new int[]{200, 100}, 150));
        System.out.println(bagOfTokensScore(new int[]{25, 91}, 99));
        System.out.println(bagOfTokensScore(new int[]{83, 67, 0}, 49));
    }

    public static int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens); // Sort so that minToken is in front and we loose minimumPower to gain maxScore
        int score = 0;
        int maxScore = 0;
        int i = 0, j = tokens.length - 1; // take min token from front and maxToken from back
        while (i <= j) {
            if (power >= tokens[i]) {
                power -= tokens[i];
                score++;
                i++;
                maxScore = Math.max(maxScore, score);
            } else if (score >= 1) {
                power += tokens[j];
                score--;
                j--;
            } else {
                return maxScore;
            }
        }
        return maxScore;
    }

}
