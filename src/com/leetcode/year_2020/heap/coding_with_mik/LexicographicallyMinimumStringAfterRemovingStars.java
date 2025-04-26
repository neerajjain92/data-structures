package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LexicographicallyMinimumStringAfterRemovingStars {

    public static void main(String[] args) {
        LexicographicallyMinimumStringAfterRemovingStars ls = new LexicographicallyMinimumStringAfterRemovingStars();
//        System.out.println(ls.clearStars("aaba*"));
//        System.out.println(ls.clearStarsOptimized("aaba*"));
        System.out.println(ls.clearStarsOptimized("a*q"));
    }


    public String clearStarsOptimized(String s) {
        char[] chars = s.toCharArray();
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.letter == b.letter) {
                return b.index - a.index;
            } else {
                return a.letter - b.letter;
            }
        });
        for (int i = 0; i < chars.length; i++) {
            char letter = chars[i];
            if (letter == '*') {
                chars[minHeap.poll().index] = '*';
            } else {
                minHeap.add(new Tuple(letter, i));
            }
        }

        StringBuilder res = new StringBuilder();
        for(char c: chars) {
            if (c != '*') {
                res.append(c);
            }
        }
        return res.toString();
    }

    public String clearStars(String s) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.letter == b.letter) {
                return b.index - a.index;
            } else {
                return a.letter - b.letter;
            }
        });
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == '*' && !minHeap.isEmpty()) {
                Tuple tuple = minHeap.poll();
                if (tuple.letter == sb.charAt(sb.length() - 1)) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                minHeap.add(new Tuple(s.charAt(i), i));
                sb.append(minHeap.peek().letter);
            }
        }

        List<Tuple> remainingEntries = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            remainingEntries.add(minHeap.poll());
        }
        remainingEntries.sort(Comparator.comparingInt(a -> a.index));
        StringBuilder stringBuilder = new StringBuilder();
        for (Tuple tuple : remainingEntries) {
            stringBuilder.append(tuple.letter);
        }
        return stringBuilder.toString();
    }

    static class Tuple {
        char letter;
        int index;

        public Tuple(char letter, int index) {
            this.letter = letter;
            this.index = index;
        }
    }
}
