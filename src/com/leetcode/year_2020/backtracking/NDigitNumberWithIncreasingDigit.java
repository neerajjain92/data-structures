package com.leetcode.year_2020.backtracking;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=xlKrk3ZO3iM&list=PL_z_8CaSLPWdbOTog8Jxk9XOjzUs3egMP&index=11
 */
public class NDigitNumberWithIncreasingDigit {

    public static void main(String[] args) {
        nDigitNumber(1);
        nDigitNumber(2);
        nDigitNumber(3);
    }

    private static void nDigitNumber(int n) {
        List<Integer> result = new ArrayList<>();
        nDigitNumber(n, new StringBuilder(), result);
        LogUtil.logIt(String.format("N:==> %d DigitNumber With Increasing Digit is \n", n));
        System.out.println(result);
    }

    private static void nDigitNumber(int n, StringBuilder generatedNum, List<Integer> result) {
        if (n == 0) {
            result.add(Integer.parseInt(generatedNum.toString()));
            return;
        }

        // Choices
        for (int i = 1; i <= 9; i++) {
            if (generatedNum.length() > 0 && (generatedNum.charAt(generatedNum.length() - 1) - '0') >= i) {
                continue;
            }
            generatedNum.append(i);
            nDigitNumber(n - 1, generatedNum, result);
            generatedNum.deleteCharAt(generatedNum.length() - 1);
        }
    }
}
