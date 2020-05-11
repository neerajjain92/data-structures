package com.leetcode.year_2020.MayChallenge.week2;

import com.util.LogUtil;

/**
 * @author neeraj on 11/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FloodFill {

    public static void main(String[] args) {
        LogUtil.printMultiDimensionArray(floodFill(new int[][]{
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        }, 1, 1, 2));

        LogUtil.printMultiDimensionArray(floodFill(new int[][]{
                {0, 0, 0},
                {0, 1, 1},
        }, 1, 1, 1));
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        floodTheImage(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private static void floodTheImage(int[][] image, int row, int col, int existingColor, int newColor) {
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != existingColor)
            return;

        image[row][col] = newColor;

        // We'll visit in all 4 directions.
        // Down, Top, Right, left.
        floodTheImage(image, row + 1, col, existingColor, newColor);
        floodTheImage(image, row - 1, col, existingColor, newColor);
        floodTheImage(image, row, col + 1, existingColor, newColor);
        floodTheImage(image, row, col - 1, existingColor, newColor);
    }


}
