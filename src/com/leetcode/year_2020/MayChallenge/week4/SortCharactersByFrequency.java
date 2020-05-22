package com.leetcode.year_2020.MayChallenge.week4;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author neeraj on 22/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SortCharactersByFrequency {

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("loveleetcode"));
        System.out.println(frequencySort("cccaaa"));
    }

    public static String frequencySort(String s) {
        if (s.length() == 0) return s;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Maintain unique characters in heap based on their frequency.
        PriorityQueue<Character> maxHeapBasedOnFreq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        maxHeapBasedOnFreq.addAll(map.keySet());


        // Fetch out the max Frequency item and append the same characters..upto it's frequency times.
        StringBuilder str = new StringBuilder();
        while (!maxHeapBasedOnFreq.isEmpty()) {
            char c = maxHeapBasedOnFreq.poll();
            for (int i = 0; i < map.get(c); i++) {
                str.append(c);
            }
        }
        return str.toString();
    }

}
