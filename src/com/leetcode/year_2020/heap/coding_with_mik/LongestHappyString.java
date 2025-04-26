package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.PriorityQueue;

public class LongestHappyString {

    public static void main(String[] args) {
        LongestHappyString obj = new LongestHappyString();
        System.out.println(obj.longestDiverseString(1, 1, 1));
        System.out.println(obj.longestDiverseString(1, 1, 7));
        System.out.println(obj.longestDiverseString(7, 1, 0));
        System.out.println(obj.longestDiverseString(2, 2, 1));
        System.out.println(obj.longestDiverseString(4, 4, 3));
    }

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Tuple> maxHeap = new PriorityQueue<>((x, y) -> {
            if (x.freq == y.freq) {
                return x.c - y.c;
            } else {
                return y.freq - x.freq;
            }
        });
        if (a > 0) maxHeap.add(new Tuple('a', a));
        if (b > 0) maxHeap.add(new Tuple('b', b));
        if (c > 0) maxHeap.add(new Tuple('c', c));

        // We know the following rules
        /*
         * s only contains the letters 'a', 'b', and 'c'.
         * s does not contain any of "aaa", "bbb", or "ccc" as a substring.
         * s contains at most a occurrences of the letter 'a'.
         * s contains at most b occurrences of the letter 'b'.
         * s contains at most c occurrences of the letter 'c'.
         */
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Tuple mostFrequent = maxHeap.poll();

            if (sb.length() >= 2 && isLastTwoCharSameAsCurrentOne(sb, mostFrequent.c)) {
                // We can't add this most frequent, we should add someone else
                if (maxHeap.isEmpty()) {
                    break; // no-one left to put a break in between and if i add mostFrequent it will break
                }

                Tuple secondMostFrequent = maxHeap.poll();
                sb.append(secondMostFrequent.c);
                secondMostFrequent.freq -= 1;

                if (secondMostFrequent.freq > 0) maxHeap.add(secondMostFrequent);

                // Add back the mostFrequent since it didn't contribute
                maxHeap.add(mostFrequent);
            } else {
                int totalToAppend = Math.min(2, mostFrequent.freq);
                append(mostFrequent.c, totalToAppend, sb);
                mostFrequent.freq -= totalToAppend;
                if (mostFrequent.freq > 0) maxHeap.add(mostFrequent);
            }
        }
        return sb.toString();
    }

    private boolean isLastTwoCharSameAsCurrentOne(StringBuilder sb, char c) {
        String subString = sb.substring(sb.length() - 2);
        return subString.charAt(0) == c && subString.charAt(1) == c;
    }

    private void append(char c, int freq, StringBuilder sb) {
        sb.append(String.valueOf(c).repeat(Math.max(0, freq)));
    }

    static class Tuple {
        char c;
        int freq;

        public Tuple(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
}
