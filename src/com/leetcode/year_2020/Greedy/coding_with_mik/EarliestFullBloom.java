package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 2136. Earliest Possible Day of Full Bloom
 * https://leetcode.com/problems/earliest-possible-day-of-full-bloom/description/
 * <p>
 * This problem is just confusing folks with a random unnecessary example, while all you need is a simple intuition
 * What do you do when someone gives you the task with  their respective completion time or the level of complexity
 * you usually try to get rid of the lengthier or complex job first, similar to how in gym workout i follow that let me
 * do the Legs workout on Monday so that burden goes away during entire week4
 * <p>
 * So we do the same here, plant the seed which takes the most days to bloom first
 * and then while they are growing, plant the other seed during their growing phase so you aren't really wasting days
 */
public class EarliestFullBloom {

    public static int earliestFullBloom(int[] plantTime, int[] growTime) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < plantTime.length; i++) {
            pairs.add(new Pair(plantTime[i], growTime[i]));
        }
        Collections.sort(pairs);
        // Now we have entries sorted in descending order by grow time

        // we have to find earliestFullBloom but it can't be any less than the maxBloomDays any seed will take
        // unfortunately hence we are calculating maxBloomDays here.
        int maxBloomDays = Integer.MIN_VALUE;
        int prevPlantDays = 0;
        for (Pair pair : pairs) {
            maxBloomDays = Math.max(maxBloomDays, prevPlantDays + pair.plantTime + pair.growTime);
            prevPlantDays = prevPlantDays + pair.plantTime;
        }
        return maxBloomDays;
    }

    static class Pair implements Comparable<Pair> {
        int plantTime;
        int growTime;

        public Pair(int plantTime, int growTime) {
            this.plantTime = plantTime;
            this.growTime = growTime;
        }

        @Override
        public int compareTo(Pair o) {
            return o.growTime - growTime;
        }
    }

    public static void main(String[] args) {
        System.out.println(earliestFullBloom(new int[]{1, 4, 3}, new int[]{2, 3, 1}));
        System.out.println(earliestFullBloom(new int[]{1,2,3,2}, new int[]{2, 1, 2, 1}));
        System.out.println(earliestFullBloom(new int[]{1}, new int[]{1}));
    }
}
