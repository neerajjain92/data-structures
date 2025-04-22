package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/description/
 */
public class MinimumDeletionstoMakeCharacterFrequenciesUnique {

    public static void main(String[] args) {
        MinimumDeletionstoMakeCharacterFrequenciesUnique obj = new MinimumDeletionstoMakeCharacterFrequenciesUnique();
//        System.out.println(obj.minDeletions("aab"));
//        System.out.println(obj.minDeletions("aaabbbcc"));
//        System.out.println(obj.minDeletions("ceabaacb"));
//        System.out.println(obj.minDeletions("abcdabcd"));
//
//        System.out.println(obj.minDeletionsApproach2("aab"));
//        System.out.println(obj.minDeletionsApproach2("aaabbbcc"));
//        System.out.println(obj.minDeletionsApproach2("ceabaacb"));
//        System.out.println(obj.minDeletionsApproach2("abcdabcd"));
        System.out.println(obj.minDeletionsApproach2("abcabc"));
    }

    /**
     * Intution simple, start storing frequencies of each characters
     * since it's lower-case so we can use just int[] of size 26
     * <p>
     * Post that start traversing and keep a set of visited frequencies
     * if you encounter a frequencies visited earlier, keep decrementing until we put in visited or it's complete 0
     * and simultaneously keep increasing the deletion counter
     * <p>
     * https://www.youtube.com/watch?v=uCbOYh01wYU&list=PLpIkg8OmuX-J8_n8Vy9P9I3KvyDcPMzRU&index=17&ab_channel=codestorywithMIK
     */
    public int minDeletions(String s) {
        int[] frequencies = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
        }
        Set<Integer> visitedFrequencies = new HashSet<>();
        int deletions = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if (!visitedFrequencies.contains(frequencies[i])) {
                visitedFrequencies.add(frequencies[i]);
            } else {
                while (frequencies[i] > 0) {
                    frequencies[i]--;
                    deletions++;
                    if (!visitedFrequencies.contains(frequencies[i])) {
                        visitedFrequencies.add(frequencies[i]);
                        break;
                    }
                }
            }
        }
        return deletions;
    }

    /*
     * In this method, we still use frequency but kindaa not use the extra
     * Set, instead we sort the frequencies in ascending order
     * so all duplicate frequencies are together
     *
     * ABCDABCD
     *
     * freq -----> 0 0 0 0 0 0 ............... 2  2  2  2
     * ArrIndex -> 0 1 2 3 4 5                22 23 24 25
     *
     * Now start from last - 1 (24th index) and compare with next one
     * if your frequency is same reduce one and increment deletion
     *
     *                                               i
     * freq -----> 0 0 0 0 0 0 ............... 2  2  1  2
     * ArrIndex -> 0 1 2 3 4 5                22 23 24 25
     * <p>
     * Now decrement i,
     * <p>
     *                                            i
     * freq -----> 0 0 0 0 0 0 ............... 2  2  1  2
     * ArrIndex -> 0 1 2 3 4 5                22 23 24 25
     * <p>
     * and compare with  right if you notice 2 != 1
     * but i and i+2 is still the same so we can't simply decrement 1 from current freq
     * but instead we should set the freq to freq[i+1]-1, Why ???
     * because if you have the same freq as your next buddy and it decremented
     * one because it had a freq matching to his neighbour then shouldn't we be decrementing
     * 1 less than it's neighbour than itself, that's exactly what we will be doing and at same time maintaining the sorted order
     *
     *                                         i
     * freq -----> 0 0 0 0 0 0 ............... 2  0  1  2 [Why 0 because we can't keep the charater it's gone as someone already have 2 and 1 as frequency]
     * ArrIndex -> 0 1 2 3 4 5                22 23 24 25
     *
     *                                      i
     * freq -----> 0 0 0 0 0 0 ............... 0  0  1  2 [Why 0 because we can't keep the charater it's gone as someone already have 2 and 1 as frequency]
     * ArrIndex -> 0 1 2 3 4 5              21 22 23 24 25
     *
     *
     *
     */
    public int minDeletionsApproach2(String s) {
        int[] frequencies = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
        }

        Arrays.sort(frequencies);
        int deletions = 0;
        int prevFreq;
        for (int i = 24; i >= 0 && frequencies[i] > 0; i--) {
            if (frequencies[i] >= frequencies[i + 1]) {
                prevFreq = frequencies[i];
                frequencies[i] = Math.max(0, frequencies[i + 1] - 1);
                deletions += prevFreq - frequencies[i];
            }
        }
        return deletions;
    }
}
