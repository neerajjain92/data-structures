package com.datastructures.recursion;

/**
 * @author neeraj on 08/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AllPossibleBinaryNumbersOfLength_N_WithEqualSumInBothHalves {

    public static void main(String[] args) {
        combination(4, "", "", 0);
        combination(5, "", "", 0);
    }

    private static void combination(int n, String left, String right, int differenceBetweenLeftSumAndRightSum) {
        if (n == 0) {
            if (differenceBetweenLeftSumAndRightSum == 0) {
                System.out.println(left + right);
            }
            return;
        }

        // What about when N is odd, then add first 1 then 0 to the middle one by one
        if (n == 1) {
            combination(0, left + "0", right, differenceBetweenLeftSumAndRightSum);
            combination(0, left + "1", right, differenceBetweenLeftSumAndRightSum);
            return;
        }


        // 4 cases
        // Add "0" to both end (to balance out equation), no difference generated
        combination(n - 2, left + "0", right + "0", differenceBetweenLeftSumAndRightSum);

        // Make left heavy first, (So adding 1 to the left)
        combination(n - 2, left + "1", right + "0", differenceBetweenLeftSumAndRightSum + 1);

        // Make right heavy
        combination(n - 2, left + "0", right + "1", differenceBetweenLeftSumAndRightSum - 1);

        // Add "1" to both end, no difference generated
        combination(n - 2, left + "1", right + "1", differenceBetweenLeftSumAndRightSum);
    }
}
