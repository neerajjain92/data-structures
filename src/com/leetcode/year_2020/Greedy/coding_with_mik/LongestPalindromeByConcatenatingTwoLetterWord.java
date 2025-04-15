package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/description/
 * 2131. Longest Palindrome by Concatenating Two Letter Words
 */
public class LongestPalindromeByConcatenatingTwoLetterWord {

    public static void main(String[] args) {
        System.out.println(longestPalindrome(new String[]{"lc", "cl", "gg"})); // lc-gg-cl [Palindrome]
        System.out.println(longestPalindrome(new String[]{"ab", "ty", "yt", "lc", "cl", "ab"})); // lc-ty-yt-cl [Palindrome
        System.out.println(longestPalindrome(new String[]{"cc", "ll", "xx"})); // Any of word
        System.out.println(longestPalindrome(new String[]{"em", "pe", "mp", "ee", "pp", "me", "ep", "em", "em", "me"})); // Any of word
    }

    public static int longestPalindrome(String[] words) {
        int palindromeLength = 0;
        // Now we have frequency of every word
        // if you consider every word as 1 unit and not 2 characters
        // For Even length we need first half to match the second half  : ABCCBA [Length  of 6]
        // For Odd length we need single character in middle, and matching left and right half [M A (D) A M]
        // Now since here each word is of 2 length so the logic still remaiins the same [MM AA XX AA MM] ===> Check how the middle one itself is also palindrome
        Map<String, Integer> wordFreqency = new HashMap<>();
        for (String word : words) {
            wordFreqency.put(word, wordFreqency.getOrDefault(word, 0) + 1);
        }

        boolean centerUsed = false;
        for (String word : words) {
            String reverse = new StringBuilder(word).reverse().toString();
            if (!word.equals(reverse)) {// "ab" "ba"
                if (freq(wordFreqency, word) > 0 && freq(wordFreqency, reverse) > 0) {
                    palindromeLength += 4;
                    reduceFrequency(word, wordFreqency, 1);
                    reduceFrequency(reverse, wordFreqency, 1);
                }
            } else {
                if (freq(wordFreqency, word) >= 2) {
                    palindromeLength += 4;
                    reduceFrequency(word, wordFreqency, 2);
                } else if (freq(wordFreqency, word) == 1 && !centerUsed) {
                    palindromeLength += 2;
                    reduceFrequency(word, wordFreqency, 1);
                    centerUsed = true;
                }
            }
        }
        return palindromeLength;
    }

    private static void reduceFrequency(String word, Map<String, Integer> wordFreqency, int freq) {
        wordFreqency.put(word, wordFreqency.get(word) - freq);
    }

    private static int freq(Map<String, Integer> wordFreqency, String word) {
        return wordFreqency.getOrDefault(word, 0);
    }
}
