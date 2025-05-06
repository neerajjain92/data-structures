package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.HashMap;
import java.util.Map;

/**
 * A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).
 * <p>
 * You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the maximum number of times you may perform the following operation:
 * <p>
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
 * Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: answerKey = "TTFF", k = 2
 * Output: 4
 * Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
 * There are four consecutive 'T's.
 * Example 2:
 * <p>
 * Input: answerKey = "TFFT", k = 1
 * Output: 3
 * Explanation: We can replace the first 'T' with an 'F' to make answerKey = "FFFT".
 * Alternatively, we can replace the second 'T' with an 'F' to make answerKey = "TFFF".
 * In both cases, there are three consecutive 'F's.
 * Example 3:
 * <p>
 * Input: answerKey = "TTFTTFTT", k = 1
 * Output: 5
 * Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
 * Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT".
 * In both cases, there are five consecutive 'T's.
 */
public class MaximizetheConfusionofanExam {

    public static void main(String[] args) {
        MaximizetheConfusionofanExam obj = new MaximizetheConfusionofanExam();
        System.out.println(obj.maxConsecutiveAnswers("FFFTTFTTFT", 3));
        System.out.println(obj.maxConsecutiveAnswers("TTFF", 2));
        System.out.println(obj.maxConsecutiveAnswers("TFFT", 1));
        System.out.println(obj.maxConsecutiveAnswers("TTFTTFTT", 1));
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
//        // try twice
//        // Once for true and other time for false
//        int max = 0;
//        max = Math.max(max, findMax(answerKey, k, 'T'));
//        max = Math.max(max, findMax(answerKey, k, 'F'));
//        return max;
        return findMaxUsingSinglePassSlidingWindow(answerKey, k);
    }

    private int findMax(String answerKey, int k, char otherAllowedChar) {
        int begin = 0, end = 0, countOfOtherChar = 0;
        char[] chars = answerKey.toCharArray();
        int max = 0;
        while (end < chars.length) {
            if (chars[end] == otherAllowedChar) {
                countOfOtherChar++;
            }
            while (countOfOtherChar > k) {
                if (chars[begin] == otherAllowedChar) {
                    countOfOtherChar--;
                }
                begin++;
            }
            max = Math.max(max, end - begin + 1);
            end++;
        }
        return max;
    }

    /**
     * Intuition is pretty simple
     * 'T F F T F' ====> In this tell me which one you want to replace the one with low frequency or the one with high frequency
     * Obviously the one with less frequency, and we will check if we are allowed to replace the smaller one.
     */
    private int findMaxUsingSinglePassSlidingWindow(String answerKey, int k) {
        int begin = 0, end = 0, max = 0, n = answerKey.length();
        Map<Character, Integer> map = new HashMap<>();

        while (end < n) {
            char charAtEnd = answerKey.charAt(end);
            map.put(charAtEnd, map.getOrDefault(charAtEnd, 0) + 1);
            while (smallerElementGreaterThanK(map, k)) {
                char charAtBegin = answerKey.charAt(begin);
                map.put(charAtBegin, map.get(charAtBegin) - 1);
                begin++;
            }
            max = Math.max(max, end - begin + 1);
            end++;
        }
        return max;
    }

    private boolean smallerElementGreaterThanK(Map<Character, Integer> map, int k) {
        return map.size() == 2 && Math.min(map.get('T'), map.get('F')) > k;
    }
}
