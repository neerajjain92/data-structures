package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinimumSprinklersToWaterThePlant {

    public static void main(String[] args) {
        MinimumSprinklersToWaterThePlant obj = new MinimumSprinklersToWaterThePlant();
        System.out.println(obj.minSprinklers(new int[]{-1, 2, 2, -1, 0, 0}));
    }

    public int minSprinklers(int[] sprinkerCapacity) {
        List<Pair> pairs = new ArrayList<>();
        int n = sprinkerCapacity.length;
        for (int i = 0; i < n; i++) {
            if (sprinkerCapacity[i] == -1) {
                continue;
            }
            pairs.add(new Pair(Math.max(0, i - sprinkerCapacity[i]), Math.min(n - 1, i + sprinkerCapacity[i])));
        }

        // Sort the sprinklers capacity so we capture from the start
        pairs.sort(Comparator.comparingInt(pair -> pair.left));

        int target = 0; // we want to check that all our garden is covered so we start from the left most area of garden
        int index = 0, sprinklers = 0;
        while (target < n) {
            int maxRight = -1;
            while (index < pairs.size()) {
                if (pairs.get(index).left <= target) {
                    // Meaning you guys are already covered, so no issues
                    maxRight = Math.max(maxRight, pairs.get(index).right);
                    index++;
                } else {
                    break; // couldn't reach to we need to increment the sprinkler
                }
            }
            if (maxRight < target) {
                return -1;
            } else {
                sprinklers++;
                target = maxRight + 1;
            }
        }
        return sprinklers;
    }

    static class Pair {
        int left, right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

}
