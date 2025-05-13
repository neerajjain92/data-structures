package com.leetcode.year_2020.backtracking;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://backtobackswe.com/platform/content/additive-sequence/code
 * https://leetcode.com/problems/additive-number/
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * *             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * <p>
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199.
 * *             1 + 99 = 100, 99 + 100 = 199
 */
public class AdditiveNumber {

    public static void main(String[] args) {
        AdditiveNumber additiveNumber = new AdditiveNumber();
        additiveNumber.checkAddtiveNumber("112358");
        additiveNumber.checkAddtiveNumber("199100199");
        additiveNumber.checkAddtiveNumber("347111829");
        additiveNumber.checkAddtiveNumber("15051101152");
        additiveNumber.checkAddtiveNumber("15141161152");
        additiveNumber.checkAddtiveNumber("101");
        additiveNumber.checkAddtiveNumber("1001");
    }

    public void checkAddtiveNumber(String input) {
        System.out.println(isAdditiveNumber(input));
    }

    public boolean isAdditiveNumber(String num) {
        List<Long> seq = new ArrayList<>();
        return isAdditive(0, num, seq);
    }

    private boolean isAdditive(int index, String num, List<Long> seq) {
        if (index == num.length() && seq.size() >= 3) {
            // Atleast 3 number criteria is fixed from here
            return true;
        }

        for (int i = 1; i <= num.length() && index + i <= num.length(); i++) {

            // Solve this  Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
            // Simple we just check if the substring we are going to fetch contains 0 in the front
            if ( i > 1 && num.charAt(index) == '0') {
                break;
            }

            long item = Long.parseLong(num.substring(index, index + i));
            int seqSize = seq.size();
            if (seqSize < 2 || item == (seq.get(seqSize - 1) + seq.get(seqSize - 2))) {
                // Sum of last 2 digits
                seq.add(item);
                if (isAdditive(index + i, num, seq)) {
                    return true;
                }
                seq.remove(seq.size() - 1);
            }
        }
        return false;
    }

//    /**
//     * Okay so if we were able to select 1st and 2nd number correctly, then we can recursively
//     * check for the remaining string, recursively.
//     *
//     * @param num
//     * @return
//     */
//    public static boolean isAdditiveNumber(String num) {
//        if (num.length() < 3) return false;
//
//        for (int firstItemIndex = 1; firstItemIndex < num.length(); firstItemIndex++) {
//            long firstNumber = parse(num.substring(0, firstItemIndex));
//            if (firstNumber == -1) continue; // Looks like leading 0 came.
//            for (int secondNumberIndex = firstItemIndex + 1; secondNumberIndex < num.length(); secondNumberIndex++) {
//                long secondNumber = parse(num.substring(firstItemIndex, secondNumberIndex));
//                if (secondNumber == -1) continue; // Looks like leading 0 came.
//                if (isAdditiveNumber(firstNumber, secondNumber, num.substring(secondNumberIndex))) {
//                    LogUtil.logIt("Num : " + num + " is Additive !!");
//                    return true;
//                }
//            }
//        }
//        LogUtil.logIt("Num : " + num + " is not Additive !!");
//        return false;
//    }
//
//    private static boolean isAdditiveNumber(final long firstNumber, final long secondNumber, final String remaining) {
//        if (remaining.length() == 0) {
//            return true; // So there is nothing remaining in the input means firstNum and secondNum are last 2 numbers
//            // hence they are additive since they reached last.
//        }
//
//        for (int offset = 1; offset <= remaining.length(); offset++) {
//            long thirdNumber = parse(remaining.substring(0, offset));
//            if (thirdNumber == -1) continue;
//
//            // Now we have all 3 numbers,
//            // if first+second === third && isAdditive(forRemaining) then whole string is additive.
//            // So once first+second === third or we can also write this in third - second == first,
//            // then we just forget about first Number and focus change to second and third number and so on.
//
//            if (thirdNumber - secondNumber == firstNumber &&
//                    isAdditiveNumber(secondNumber, thirdNumber, remaining.substring(offset))) {
//                // Notice how we are putting secondNumber as firstNumber argument, and third num as the secondNum argument.
//                return true;
//            }
//        }
//        return false; // No there is no third number which is sum(firstNum, secondNum)
//    }
//
//    /**
//     * If {@param substring} isn't a valid long return -1, long number otherwise
//     */
//    private static long parse(final String substring) {
//        if (substring.equals("0")) return 0;
//        if (substring.startsWith("0")) {
//            return -1;
//        }
//        try {
//            return Long.parseLong(substring);
//        } catch (NumberFormatException ex) {
//            return -1;
//        }
//    }
}
