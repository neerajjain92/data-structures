package com.leetcode.backtracking;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Compute All Mnemonics For A Phone Number (Recursion/Backtracking Problem)
 * <p>
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * * <p>
 * * <p>
 * * *    Phone KEY - Pad
 * * * -----------------------------
 * * *   1   |      2    |       3
 * * *       |     ABC   |      DEF
 * * * -----------------------------
 * * *   4   |      5    |       6
 * * *  GHI  |     JKL   |      MNO
 * * * -----------------------------
 * * *   7   |      8    |       9
 * * *  PQRS |     TUV   |      WXYZ
 * * * ------------------------------
 * * *   *   |      0    |       #
 * * *       |           |
 * * * ------------------------------
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author neeraj on 2019-05-18
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * *
 */
public class AllMneMonicsOfPhoneNumber {

    private static String[] MAPPINGS = {
            "0",
            "1",
            "ABC",  // Represent Digit 2 in Phone Number
            "DEF",  // Represent Digit 3 in Phone Number
            "GHI",  // Represent Digit 4 in Phone Number
            "JKL",  // Represent Digit 5 in Phone Number
            "MNO",  // Represent Digit 6 in Phone Number
            "PQRS", // Represent Digit 7 in Phone Number
            "TUV",  // Represent Digit 8 in Phone Number
            "WXYZ"  // Represent Digit 9 in Phone Number
    };

    public static void main(String[] args) {
        solveMnemonics("23");
        solveMnemonics("789");
    }

    public static List<String> solveMnemonics(String digit) {
        // This will hold you current mnemonic constructed from different combinations of digit
        char[] currentMnemonicInProcess = new char[digit.length()];
        List<String> allMnemonics = new ArrayList<>();
        int currentMnemonicPointer = 0;

        solveMnemonicsHelper(digit, currentMnemonicInProcess, allMnemonics, currentMnemonicPointer);
        LogUtil.logIt("All Mnemonics which can be generated using a digit "+ digit);
        LogUtil.printList(allMnemonics);
        return allMnemonics;
    }

    private static void solveMnemonicsHelper(String digit, char[] currentMnemonicInProcess, List<String> allMnemonics, int currentMnemonicPointer) {

        // Base Case
        // If you reached the end of currentMnemonicInProcess array
        if (currentMnemonicPointer == currentMnemonicInProcess.length) {
            allMnemonics.add(new String(currentMnemonicInProcess));
        } else {
            // Here we don't have any constraints, so we just choose-explore-un-choose
            // Let's check each number in the input digit

            // So if the digit is suppose "23"
            // We see that MAPPINGS has entry "ABC" for digit "2"
            // So we will explore "ABC" from 0th Index to it's full length i.e 3;
            // Why this : MAPPINGS[digit.charAt(currentMnemonicPointer) - '0']
            // Subtracting 0 char from char gives integer, which can be used to access to the correct index
            for (int i = 0; i < MAPPINGS[digit.charAt(currentMnemonicPointer) - '0'].length(); i++) {

                char letterAtCurrentMnemonicPointer = MAPPINGS[digit.charAt(currentMnemonicPointer) - '0'].charAt(i);

                // Let's choose it
                currentMnemonicInProcess[currentMnemonicPointer] = letterAtCurrentMnemonicPointer;

                // Let's explore it, by incrementing the currentMnemonicPointer as we already choose at current index, so want to explore what's next
                solveMnemonicsHelper(digit, currentMnemonicInProcess, allMnemonics, currentMnemonicPointer + 1);

                // Unchoose (Not really necessary in this case
                currentMnemonicInProcess[currentMnemonicPointer] = '0';
            }
        }
    }


}
