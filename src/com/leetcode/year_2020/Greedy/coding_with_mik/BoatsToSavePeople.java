package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/boats-to-save-people/description/
 * https://www.youtube.com/watch?v=UsQzOL6r0HY&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=2
 * Leetcode 881
 */
public class BoatsToSavePeople {

    public static void main(String[] args) {

    }

    /**
     * Max People allowed on boat ==> 2
     * Infinite Boats
     * Initial limit of a boat is provided
     * No person is heavier than capacity of boat
     * <p>
     * Idea: If we can somehow just send the heaviest person on the boat first (and cherry on the cake if some thin guy can
     * also go along with him then that's great, we sent 2, else we will send just 1 because that's the only possible way if
     * only 2 are allowed, if this had been a problem where multiple are allowed then we need to think differently
     * <p>
     * [1, 2, 4, 9] ==> capacity is 10
     * <p>
     * So i can send heavy => 9 and with him 1 as well, so in one boat 2 members gone
     * now 2 and 4 are limited, we repeat the same process
     * <p>
     * Now imagine [2, 4, 6, 9] ==> cap = 10
     * Now 9+2 is 11 so both can't go we just send heavy person, so that thin person can be stuffed with anyone
     * [2,4,6] left, now pull same 2+6 = 8 yeah so on boat 2 these can go and 4 remaning can go in last boat
     */
    public int numRescueBoats(int[] weight, int capacity) {
        Arrays.sort(weight);
        int i=0, j=weight.length-1;
        int boats = 0;
        while (i <= j) {
            if (weight[i] + weight[j] <= capacity) {
                // Both can fit
                i++;
                j--;
                boats++;
            } else {
                // we will ignore the thinner guy
                // Only fat person can fit
                j--;
                boats++;
            }
        }
        return boats;
    }
}
