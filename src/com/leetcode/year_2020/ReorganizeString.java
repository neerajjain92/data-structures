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
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : S.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> charFreq.get(b) - charFreq.get(a));
        maxHeap.addAll(charFreq.keySet());
        StringBuilder result = new StringBuilder();
        while (maxHeap.size() > 1) {
            char first = maxHeap.poll();
            char second = maxHeap.poll();
            result.append(first).append(second);

            if (charFreq.get(first) > 1) {
                charFreq.put(first, charFreq.get(first) - 1);
                maxHeap.add(first);
            }
            if (charFreq.get(second) > 1) {
                charFreq.put(second, charFreq.get(second) - 1);
                maxHeap.add(second);
            }

        }

        if (!maxHeap.isEmpty()) {
            if (charFreq.get(maxHeap.peek()) > 1) {
                return "";
            } else {
                result.append(maxHeap.poll());
            }
        }
        return result.toString();
    }
}
