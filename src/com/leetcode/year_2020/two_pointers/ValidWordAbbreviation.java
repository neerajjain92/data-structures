package com.leetcode.year_2020.two_pointers;

import static java.lang.Character.isDigit;

/**
 * https://leetcode.ca/2017-01-11-408-Valid-Word-Abbreviation/
 * https://www.youtube.com/watch?v=ALDB1fi65U8&list=PLtVcREFQDb6ZykTpgFhkQiPjLyrX1Y96D&index=12&ab_channel=CodingwithMinmer
 * Valid Word Abbreviation || Leetcode 408 || Variant Question Big Tech Actually Asks
 */
public class ValidWordAbbreviation {

    public static void main(String[] args) {
        ValidWordAbbreviation obj = new ValidWordAbbreviation();
        System.out.println(obj.validWordAbbreviation("substitution", "s10n"));
        System.out.println(obj.validWordAbbreviation("substitution", "sub4u4"));
        System.out.println(obj.validWordAbbreviation("substitution", "12"));
        System.out.println(obj.validWordAbbreviation("substitution", "su3i1u2on"));
        System.out.println(obj.validWordAbbreviation("substitution", "substitution"));
        System.out.println(obj.validWordAbbreviation("substitution", "s55n"));
        System.out.println(obj.validWordAbbreviation("substitution", "s010n"));
        System.out.println(obj.validWordAbbreviation("substitution", "s0ubstitution"));

        System.out.println("-------------- Wild Card Abbreviation --------------");

        System.out.println(obj.validWordAbbreviationWithWildCard("apple", "a*e")); // Should return  true
        System.out.println(obj.validWordAbbreviationWithWildCard("abc", "*")); // Should return true
        System.out.println(obj.validWordAbbreviationWithWildCard("abc", "a*")); // Should return true
        System.out.println(obj.validWordAbbreviationWithWildCard("abc", "a*d")); // Should return false
        System.out.println(obj.validWordAbbreviationWithWildCard("abc", "*d")); // Should return false
        System.out.println(obj.validWordAbbreviationWithWildCard("test", "test***")); // Should return true
        System.out.println(obj.validWordAbbreviationWithWildCard("test", "test***s")); // Should return false

    }

    public boolean validWordAbbreviation(String word, String abbr) {
        int w = 0;
        int a = 0;

        while (w < word.length() && a < abbr.length()) {
            if (word.charAt(w) == abbr.charAt(a)) {
                w++;
                a++;
            } else {
                // Mismatch
                if (!isDigit(abbr.charAt(a)) || abbr.charAt(a) == '0') {
                    return false; // Nothing abbreviated plain simple different chars
                } else {
                    // Okay so we have a digit here
                    // Check if this digit is expanding in the right
                    int skipDigit = 0;
                    while (a < abbr.length() && isDigit(abbr.charAt(a))) {
                        skipDigit = skipDigit * 10 + (abbr.charAt(a) - '0');
                        a++;
                    }
                    w += skipDigit;
                    // a is already on a char
                }
            }
        }
        return a == abbr.length() && w == word.length(); // they should end exactly at the end of respective length
    }

    public boolean validWordAbbreviationWithWildCard(String word, String abbr) {
        return dfs(word, abbr, 0, 0);
    }

    private boolean dfs(String word, String abbr, int w, int a) {
        if (w == word.length() && a == abbr.length()) return true;
        if (w > abbr.length()) return false;
        if (a == abbr.length()) return false;
        char abbrChar = abbr.charAt(a);

        if (w < word.length() && word.charAt(w) == abbrChar) {
            return dfs(word, abbr, w + 1, a + 1);
        }

        if (abbrChar == '*') {
            // Wild card
            for (int i = w; i <= word.length(); i++) { // Running till <= to wordLength, because wild card can match with 0 or more chars
                // you are trying to match the ith position of word, with the next char of this wildCard
                if (dfs(word, abbr, i, a + 1)) {
                    return true;
                }
            }
            return false;
        }

        if (isDigit(abbrChar)) {
            if (abbrChar == '0') return false;
            int skipDigit = 0;
            while (a < abbr.length() && isDigit(abbr.charAt(a))) {
                skipDigit = skipDigit * 10 + (abbr.charAt(a) - '0');
                a++;
            }
            return dfs(word, abbr, w + skipDigit, a);
        }

        return false; // Looks like nothing worked return false
    }

}
