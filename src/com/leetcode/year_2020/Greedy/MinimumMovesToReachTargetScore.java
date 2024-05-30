package com.leetcode.year_2020.Greedy;

/**
 * https://leetcode.com/problems/minimum-moves-to-reach-target-score/description/
 * <p>
 * 2139. Minimum Moves to Reach Target Score
 * <p>
 * similar to {@link BrokenCalculator}
 */
public class MinimumMovesToReachTargetScore {

    public static void main(String[] args) {
        System.out.println(minMoves(5, 0));
        System.out.println(minMoves(19, 2));
        System.out.println(minMoves(10, 4));
        System.out.println(minMoves(1000000000, 5));
        System.out.println(minMoves(766972377, 92));
    }

    public static int minMoves(int target, int maxDoubles) {
        int minMoves = 0;
        while (target > 1) {
            if (target % 2 == 0) {
                if (maxDoubles > 0) {
                    maxDoubles--;
                    minMoves++;
                } else {
                    minMoves += target / 2;
                }
                target /= 2;
            } else {
                target -= 1;
                minMoves++;
            }
        }
        return minMoves;
    }
}
