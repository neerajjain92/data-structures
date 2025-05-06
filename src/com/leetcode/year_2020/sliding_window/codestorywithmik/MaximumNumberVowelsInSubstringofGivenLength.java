package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.Set;

public class MaximumNumberVowelsInSubstringofGivenLength {

    Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) {
        MaximumNumberVowelsInSubstringofGivenLength solution = new MaximumNumberVowelsInSubstringofGivenLength();
        System.out.println(solution.maxVowels("abciiidef", 3));
        System.out.println(solution.maxVowels("aeiou", 2));
        System.out.println(solution.maxVowels("leetcode", 3));
    }

    public int maxVowels(String s, int k) {
        int count = 0, beg = 0, end = 0, n = s.length(), maxVowels = Integer.MIN_VALUE;
        while (end < n) {
            if (vowels.contains(s.charAt(end))) {
                count++;
            }
            if (end - beg + 1 == k) {
                maxVowels = Math.max(maxVowels, count);
                if (vowels.contains(s.charAt(beg))) {
                    count--;
                }
                beg++;
            }
            end++;
        }
        return maxVowels;
    }
}
