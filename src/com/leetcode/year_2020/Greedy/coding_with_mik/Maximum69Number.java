package com.leetcode.year_2020.Greedy.coding_with_mik;

/**
 * https://leetcode.com/problems/maximum-69-number/
 * 1323. Maximum 69 Number
 *
 */
public class Maximum69Number {

    public static void main(String[] args) {

    }

    public int maximum69Number(int num) {
        String numStr = String.valueOf(num);
        for (int i = 0; i < numStr.length(); i++) {
            if (numStr.charAt(i) == '6') {
                return Integer.parseInt(numStr.substring(0, i) + '9' + numStr.substring(i + 1));
            }
        }
        return num;
    }

    public int maximum69NumberApproach2(int num) {
        int numCopy = num;
        int remainder = num;
        int sumToBeAdded = 0, places = 0;
        /*
         * Basically what we are doing
         * if we have number
         * 9 6 6 9 ==> we know max number will be 9 9 6 9
         * What we did added 3 to first 9 from left
         * but essentially it's 9669 + 300 => 9969.
         *
         * So find the 10's place where you find the last 6
         * and multiply that by 3 since we have to add 3 to 6 to convert it 9
         *
         */
        while (numCopy > 0) {
            remainder = numCopy % 10;
            if (remainder == 6) {
                sumToBeAdded = (int) (3 * Math.pow(10, places));
            }
            numCopy = numCopy / 10;
            places++;
        }
        return num + sumToBeAdded;
    }
}
