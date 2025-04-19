package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

public class MaximumIceCreamBars {

    /**
     * 1833. Maximum Ice Cream Bars
     * https://leetcode.com/problems/maximum-ice-cream-bars/description/
     */
    public static void main(String[] args) {

    }

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int maxIceCream = 0;
        for (int cost : costs) {
            if (cost <= coins) {
                coins -= cost;
                maxIceCream++;
            } else {
                break;
            }
        }
        return maxIceCream;
    }
}
