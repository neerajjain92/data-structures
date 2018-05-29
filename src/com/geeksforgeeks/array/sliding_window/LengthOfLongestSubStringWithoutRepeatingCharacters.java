package com.geeksforgeeks.array.sliding_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LengthOfLongestSubStringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        findLongestSubString("GEEKSFORGEEKS");
        findLongestSubString("ABDEFGABEF");
        findLongestSubString("AAAA");
    }

    public static void findLongestSubString(String str) {
        char[] arr = str.toCharArray();
        Integer LONGEST_WINDOW_WIDTH = 0;
        Integer longestWindowLeft = 0;
        Integer longestWindowRight = 0;

        Integer wL = 0; // Left Side of Window
        Integer wR = 0; // Right Side of Window
        List<Character> allVisitedCharacters = new ArrayList<>();

        while (wR < arr.length) {

            while (allVisitedCharacters.contains(arr[wR])) {
                allVisitedCharacters.remove(Character.valueOf(arr[wL++]));
            }

            allVisitedCharacters.add(arr[wR++]);
            if (wR - wL > LONGEST_WINDOW_WIDTH) {
                LONGEST_WINDOW_WIDTH = wR - wL;
                longestWindowLeft = wL;
                longestWindowRight = wR;
            }
        }
        char[] longestSubString = Arrays.copyOfRange(arr, longestWindowLeft, longestWindowRight);
        printArr(longestSubString);
    }

    public static void printArr(char[] arr) {
        for (char c : arr) {
            System.out.print(c + ",");
        }
        System.out.println();
    }
}
