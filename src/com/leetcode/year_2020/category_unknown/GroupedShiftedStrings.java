package com.leetcode.year_2020.category_unknown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/group-shifted-string/
 */
public class GroupedShiftedStrings {

    public static void main(String[] args) {
        groupShiftedStrings(new String[]{"acd", "dfg", "wyz", "yab", "mop", "bdfh", "a", "x", "moqs"});
        groupShiftedStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"});
    }

    public static List<List<String>> groupShiftedStrings(final String[] words) {
        final Map<String, List<String>> consecutiveLetterDifferenceMap = new HashMap<>();

        for (String word : words) {
            final String difference = consecutiveLetterDifference(word);
            consecutiveLetterDifferenceMap.putIfAbsent(difference, new ArrayList<>());
            consecutiveLetterDifferenceMap.get(difference).add(word);
        }
        System.out.println(consecutiveLetterDifferenceMap);
        return null;
    }

    public static String consecutiveLetterDifference(final String A) {
        if (A.length() == 0 || A.length() == 1) return "0";
        StringBuilder difference = new StringBuilder();
        for (int i = 1; i < A.length(); i++) {
            int diff = A.charAt(i) - A.charAt(i - 1);
            if (diff < 0)
                diff += 26; // This is to balance out situation such as az so difference will be -25 but we should make it -25+26 = 1 which is actual difference.
            difference.append(diff);
        }
        return difference.toString();
    }
}
