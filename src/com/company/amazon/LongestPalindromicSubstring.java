package com.company.amazon;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String input = "MADAM";
        System.out.println(getLongestPalindromicSubstring(input));
        input = "forgeeksskeegfor";
        System.out.println(getLongestPalindromicSubstring(input));
    }

    public static int getLongestPalindromicSubstring(String str) {
        System.out.println("============================");
        char[] input = str.toCharArray();
        int longestPalindromicSubstringLength = 0;
        int startPointOfPalindromicString = 0;

        for (int i = 1; i < input.length; i++) {

            // For Even Length String
            int low = i - 1;
            int high = i;
            while (low >= 0 && high < input.length && (input[low] == input[high])) {
                int lengthOfCurrentPalindromicString = (high - low) + 1;
                if (longestPalindromicSubstringLength < lengthOfCurrentPalindromicString) {
                    startPointOfPalindromicString = low;
                    longestPalindromicSubstringLength = lengthOfCurrentPalindromicString;
                }
                low--;
                high++;
            }

            // For Odd Length String
            low = i - 1;
            high = i + 1;
            while (low >= 0 && high < input.length && (input[low] == input[high])) {
                int lengthOfCurrentPalindromicString = (high - low) + 1;
                if (longestPalindromicSubstringLength < lengthOfCurrentPalindromicString) {
                    longestPalindromicSubstringLength = lengthOfCurrentPalindromicString;
                }
                low--;
                high++;
            }
        }
        System.out.println(str.substring(startPointOfPalindromicString, startPointOfPalindromicString + longestPalindromicSubstringLength));
        return longestPalindromicSubstringLength;
    }
}
