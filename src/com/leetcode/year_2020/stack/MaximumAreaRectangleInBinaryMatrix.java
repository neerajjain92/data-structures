package com.leetcode.year_2020.stack;

import com.util.LogUtil;

import static com.util.LogUtil.getArrayAsString;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 *
 * @author neeraj on 14/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximumAreaRectangleInBinaryMatrix {

    public static void main(String[] args) {
        System.out.println(maximalRectangle(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));

        System.out.println(maximalRectangle(new char[][]{
                {'0', '1', '1', '0'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'1', '1', '0', '0'}
        }));
    }

    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        /**
         * If you notice carefully, we can break down this problem into {@link MaximumAreaOfHistogram},
         * how we will convert the 2D array into 1D array. and check the histogram for that.
         *
         * [
         *   ["1","0","1","0","0"],
         *   ["1","0","1","1","1"],
         *   ["1","1","1","1","1"],
         *   ["1","0","0","1","0"]
         * ]
         *
         * So let's start from top-most row. 1*5 matrix into 1d
         * if we put it into 1D histogram then height will become --> 1 0 1 0 0  callHistogramOnIt();
         *
         * Also important point when you are standing on a 1D array of 0s and 1s
         *
         * [ 1 1 0 1 1 1 1 0 1 1]
         *   i
         *
         *   When standing at i how much right you can strech until you will meet a 0
         *   same on the left side
         *   so basically on every i which has 1 we are checking nearest 0 in both side
         *   So it's kinda similar to finding NEAREST SMALLEST ELEMENT TO THE RIGHT and to THE LEFT as well
         *   because 0 is smaller than 1
         *   and ALSO whenever building a RECTANGLE, you want to make sure it's height is consitent as long as it's width is present right
         *
         *   this is a rectangle         This is not a rectangle, because a dip in height in between
         *   |--------------|          |-------     ----------
         *   |              |          |      |     |        |
         *   |              |          |      |-----|        |
         *   |             |          |       |     |       |
         *   ------------------------------------------------
         *
         * Now let's try to include 2nd Row(2*4 matrix into 1d) in this and see how much height we can achieve
         * [2, 0, 2, 1, 1]
         *
         * For Row 3  [3, 1, 3, 2, 2] (3*5 matrix into 1d)
         *
         * Now 4th Row is interesting... we can't do [4,1,3,3,2]
         * Reason : ===> Column 2 building will be in air, since at 4th row height is 0.... similar case for 3rd column.
         * So whenever we encounter 0 .. i.e we can't include height coming from top row.
         * So for this reason the matrix 4*4 in 1d will be [4,0,0,3,0]
         *
         */
        int[] onlyOnes = new int[matrix[0].length];
        int MAX_AREA = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    onlyOnes[j] = 0; // Important, a building can't fly, hence if  the entry is  0, anything above this row will not be considered for this column and bottom
                } else {
                    onlyOnes[j] += 1;
                }
            }
            // Now for this row let's call histogram
            LogUtil.logIt("Invoking Histogram for " + getArrayAsString(onlyOnes));
            MAX_AREA = Math.max(MAX_AREA, MaximumAreaOfHistogram.findMaxArea(onlyOnes));
        }
        return MAX_AREA;
    }
}
