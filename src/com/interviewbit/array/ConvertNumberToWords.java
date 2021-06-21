package com.interviewbit.array;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Convert Number into Words.
 * <p>
 * This is Extensible method, if you have to add any greater digits that the system currently
 * support just add it's word and the entry if it's associated to tens(prefix)
 *
 * @author neeraj on 2019-07-30
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ConvertNumberToWords {

    static Map<Integer, String> digitToWordMap = new HashMap<>();
    static Map<Integer, String> placeValueWordMap = new HashMap<>();

    // This list contains the indexes which are on tens(prefixed values)
    // such as 74256 ==> Seventy Four Thousand Two hundred Fifty Six.
    // here placeValue index are
    //  7   4   2   5   6
    // (5) (4) (3) (2) (1) <-- Tens Place such as, Seventy  and Fifty
    static List<Integer> placeValueIndexForTensPlace = Arrays.asList(9, 7, 5, 2);
    static String WHITESPACE = " ";

    static {
        digitToWordMap.put(1, "One");
        digitToWordMap.put(2, "Two");
        digitToWordMap.put(3, "Three");
        digitToWordMap.put(4, "Four");
        digitToWordMap.put(5, "Five");
        digitToWordMap.put(6, "Six");
        digitToWordMap.put(7, "Seven");
        digitToWordMap.put(8, "Eight");
        digitToWordMap.put(9, "Nine");
        digitToWordMap.put(10, "Ten");
        digitToWordMap.put(11, "Eleven");
        digitToWordMap.put(12, "Twelve");
        digitToWordMap.put(13, "Thirteen");
        digitToWordMap.put(14, "Fourteen");
        digitToWordMap.put(15, "Fifteen");
        digitToWordMap.put(16, "Sixteen");
        digitToWordMap.put(17, "Seventeen");
        digitToWordMap.put(18, "Eighteen");
        digitToWordMap.put(19, "Nineteen");
        digitToWordMap.put(20, "Twenty");
        digitToWordMap.put(30, "Thirty");
        digitToWordMap.put(40, "Forty");
        digitToWordMap.put(50, "Fifty");
        digitToWordMap.put(60, "Sixty");
        digitToWordMap.put(70, "Seventy");
        digitToWordMap.put(80, "Eighty");
        digitToWordMap.put(90, "Ninety");


        placeValueWordMap.put(8, "Crore");
        placeValueWordMap.put(6, "Lakh");
        placeValueWordMap.put(4, "Thousand");
        placeValueWordMap.put(3, "Hundred");
    }

    public static void main(String[] args) {
//        convert(1);
//        convert(12);
        int a = 10; int b = 20;
        if(a == 10 || b == 20) {
            System.out.println("Hello.........");
        }
        convert(12456);
        convert(123);
        convert(1234);
        convert(12345);
        convert(74256);
        convert(14256);
        convert(874256);
        convert(112345);
        convert(4112345);
        convert(948765432);
    }

    public static void convert(int input) {
        LogUtil.logIt("Converting [" + input + "] ==>  " + convertNumberIntoWords(input));
    }


    private static String convertNumberIntoWords(int input) {
        String number = String.valueOf(input);
        int placeValueIndex = number.length();
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < number.length(); i++) {

            if (placeValueIndexForTensPlace.contains(placeValueIndex)) {
                Integer valAtI = Integer.parseInt(number.substring(i, i + 1));
                if (valAtI > 1) {
                    result.append(digitToWordMap.get(valAtI * 10));
                    result.append(WHITESPACE);
                    placeValueIndex--;
                } else {
                    // We have to take next digit also into consideration
                    // Like for 12456 == This translates to Twelve Thousand Four Hundred Fifty Six.
                    // when we are at 1(
                    result.append(digitToWordMap.get(Integer.parseInt(number.substring(i, i + 2))));
                    if (placeValueWordMap.get(placeValueIndex - 1) != null) {
                        result.append(WHITESPACE);
                        result.append(placeValueWordMap.get(placeValueIndex - 1));
                    }
                    result.append(WHITESPACE);
                    i = i + 1;
                    // Since We have covered 2 indexes,so lets reduce placeValueIndex by 2
                    placeValueIndex = placeValueIndex - 2;
                }
            } else {
                result.append(digitToWordMap.get(Integer.parseInt("" + number.charAt(i))))
                        .append(WHITESPACE)
                        .append(placeValueWordMap.get(placeValueIndex) == null ? WHITESPACE : placeValueWordMap.get(placeValueIndex))
                        .append(WHITESPACE);
                placeValueIndex--;
            }
        }
        return result.toString();
    }
}
