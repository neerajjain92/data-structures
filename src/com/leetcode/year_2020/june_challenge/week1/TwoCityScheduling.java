package com.leetcode.year_2020.june_challenge.week1;

import java.util.Arrays;

/**
 * @author neeraj on 03/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TwoCityScheduling {

    public static void main(String[] args) {
        System.out.println(twoCitySchedCost(new int[][]{
                {10, 20},
                {30, 200},
                {400, 50},
                {30, 20}
        }));
        System.out.println(twoCitySchedCost(new int[][]{
                {30, 100},
                {40, 90},
                {50, 50},
                {70, 50}
        }));
    }

    public static int twoCitySchedCost(int[][] costs) {
        // Why are we sorting on savings
        // Simple we have to send N people to both A and B cities equally (total 2N people)
        // So if we select first N B cost as high as possible in comparision to A
        // So we are sending first N people to City A in as small price as possible
        // Now we have no options but to send remaining N people to city B at the remaining cost only.

        // Sort the Cost based on Savings in descending order.

        // Subtracting Cost of travelling to A from Cost of Travelling to B
        // Hence our savings
        Arrays.sort(costs, (A, B) -> { // We want to have maximum savings first.
            return (B[1] - B[0]) - (A[1] - A[0]);
        });

        // Maximizing savings for A
        // [[10,20],[30,200],[400,50],[30,20]]
        // Cost B - Cost A
        // [10, 170, -350, -10]
        // So we definitely want to send 1st N people to A
        // and remaining N people to B

        int cost = 0;
        int n = costs.length;
        int i = 0;
        for (; i < n / 2; i++) {
            cost += costs[i][0];
        }

        // Now remaining n/2 people to B
        for (; i < n; i++) {
            cost += costs[i][1];
        }
        return cost;
    }
}
