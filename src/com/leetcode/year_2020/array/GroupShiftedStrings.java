package com.leetcode.year_2020.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=uEOaAs-KY3M&list=PLtVcREFQDb6ZykTpgFhkQiPjLyrX1Y96D&index=16&ab_channel=CodingwithMinmer
 * https://leetcode.ca/all/249.html
 * <p>
 * <p>
 * 249. Group Shifted Strings
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * <p>
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 * <p>
 * Example:
 * <p>
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Output:
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 */
public class GroupShiftedStrings {

    public static void main(String[] args) {
        GroupShiftedStrings groupShiftedStrings = new GroupShiftedStrings();
        System.out.println(groupShiftedStrings.groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ab", "ba", "a", "z"}));

        System.out.println(groupShiftedStrings.rightShitAlphanumericString("89-yfZZ@", 5));
    }

    public String rightShitAlphanumericString(String str, int rotationalFactor) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append((char) (((c - '0') + rotationalFactor) % 10 + '0'));
            } else if (Character.isLowerCase(c)) {
                result.append((char) (((c - 'a') + rotationalFactor) % 26 + 'a'));
            } else if (Character.isUpperCase(c)) {
                result.append((char) (((c - 'A') + rotationalFactor) % 26 + 'A'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groupedStrings = new HashMap<>();
        for (String key : strings) {
            String uniqueKey = getUniqueKey(key);
            groupedStrings.computeIfAbsent(uniqueKey, k -> new ArrayList<>()).add(key);
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : groupedStrings.entrySet()) {
            result.add(new ArrayList<>(entry.getValue()));
        }
        return result;

    }

    private String getUniqueKey(String key) {
        if (key.length() == 1) return "0";
        StringBuilder uniqueKey = new StringBuilder();
        for (int i = 1; i < key.length(); i++) {
            int diff = (key.charAt(i) - key.charAt(i - 1));
            if (diff < 0) {
                // From A to Z you are just jumping 1 step in anti-clock direction
                // So Total(26) - 1(negative shift) ==> 25 steps to be taken in positive direction
                // Multiply -1 to both end since 0 - 25 ==> -25
                // -[26 - X] ==> - 25
                // X(positive shift) = -25 + 26, this is correct equation
                // So whenever you get negative difference you add 26 to it
                diff += 26;
            }
            uniqueKey.append(diff);
        }
        return uniqueKey.toString();
    }
}
