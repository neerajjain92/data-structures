package com.leetcode.year_2020;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author neeraj on 10/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReorganizeString {

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
        System.out.println(reorganizeString("abcabc"));
        System.out.println(reorganizeString("vvvlo"));
    }

    public static String reorganizeString(String S) {
        Map<Character, Integer> freqCount = new HashMap<>();
        for (char c : S.toCharArray()) {
            freqCount.put(c, freqCount.getOrDefault(c, 0) + 1);
        }

        // Now we need a Max Priority queue which maintains characters based
        // on their frequency, so that we can arrange the characters based
        // on most frequent first followed by second most frequent and so on.
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) ->
                freqCount.get(b) - freqCount.get(a));

        maxHeap.addAll(freqCount.keySet());

        StringBuilder stringBuilder = new StringBuilder();
        while (maxHeap.size() > 1) {
            char mostFrequent = maxHeap.remove();
            char nextMostFrequent = maxHeap.remove();

            stringBuilder.append(mostFrequent).append(nextMostFrequent);

            freqCount.put(mostFrequent, freqCount.get(mostFrequent) - 1);
            freqCount.put(nextMostFrequent, freqCount.get(nextMostFrequent) - 1);

            if (freqCount.get(mostFrequent) > 0) {
                maxHeap.add(mostFrequent);
            }
            if (freqCount.get(nextMostFrequent) > 0) {
                maxHeap.add(nextMostFrequent);
            }
        }
        if (freqCount.get(maxHeap.peek()) != null &&
                freqCount.get(maxHeap.peek()) > 1) {
            return "";
        }
        while (!maxHeap.isEmpty()) {
            stringBuilder.append(maxHeap.remove());
        }
        return stringBuilder.toString();
    }
}
