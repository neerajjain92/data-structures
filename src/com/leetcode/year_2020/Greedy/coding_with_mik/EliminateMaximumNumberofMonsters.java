package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EliminateMaximumNumberofMonsters {

    public static void main(String[] args) {
        EliminateMaximumNumberofMonsters obj = new EliminateMaximumNumberofMonsters();
        System.out.println(obj.eliminateMaximum(new int[]{1, 3, 4}, new int[]{1, 1, 1}));
        System.out.println(obj.eliminateMaximum(new int[]{1, 1, 2, 3}, new int[]{1, 1, 1, 1}));
        System.out.println(obj.eliminateMaximum(new int[]{3, 2, 4}, new int[]{5, 3, 2}));
        System.out.println(obj.eliminateMaximum(new int[]{4, 2, 3}, new int[]{2, 1, 1}));
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        double[] timeToReach = new double[dist.length];
        for (int i = 0; i < timeToReach.length; i++) {
            timeToReach[i] = Math.ceil((double) dist[i] / speed[i]);
        }

        Arrays.sort(timeToReach);

        for (int i = 0; i < dist.length; i++) {
            if (timeToReach[i] <= i) {
                return i;
            }
        }
        return dist.length;
    }
}
