package com.leetcode.problems.medium;

import com.geeksforgeeks.array.Rotate2DMatrix;

/**
 * @author neeraj on 01/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FloodFill_733 {

    public static void main(String[] args) {
        floodFill(new int[][]{
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
        }, 1, 1, 2);

        floodFill_version1(new int[][]{
                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
        }, 1, 1, 2);

        floodFill(new int[][]{
                {0, 0, 0}, {0, 1, 1}
        }, 1, 1, 1);
    }

    public static int[][] floodFill_version1(int[][] image, int sr, int sc, int color) {
        fillColor(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    private static void fillColor(int[][] image, int row, int col, int color, int originalColor) {
        if (row < 0 || col < 0 || row >= image.length || col >= image[0].length || image[row][col] != originalColor)
            return;
        image[row][col] = color;

        // Top
        fillColor(image, row - 1, col, color, originalColor);
        // Right
        fillColor(image, row, col + 1, color, originalColor);
        // Down
        fillColor(image, row + 1, col, color, originalColor);
        // Left
        fillColor(image, row, col - 1, color, originalColor);
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int initialColor = image[sr][sc];
        boolean[][] visited = new boolean[image.length][image[0].length];
        dfs(image, visited, sr, sc, newColor, initialColor);
        Rotate2DMatrix.print2DArray(image);
        return image;
    }

    public static void dfs(int[][] image, boolean[][] visited, int sr, int sc, int newColor, int initialColor) {
        image[sr][sc] = newColor;
        visited[sr][sc] = true;

        if (isSafe(image, visited, sr - 1, sc, initialColor)) {
            dfs(image, visited, sr - 1, sc, newColor, initialColor);
        }
        if (isSafe(image, visited, sr, sc + 1, initialColor)) {
            dfs(image, visited, sr, sc + 1, newColor, initialColor);
        }
        if (isSafe(image, visited, sr + 1, sc, initialColor)) {
            dfs(image, visited, sr + 1, sc, newColor, initialColor);
        }
        if (isSafe(image, visited, sr, sc - 1, initialColor)) {
            dfs(image, visited, sr, sc - 1, newColor, initialColor);
        }
    }

    public static boolean isSafe(int[][] image, boolean[][] visited, int sr, int sc, int initialColor) {
        return sr >= 0 && sc >= 0 && sr < image.length && sc < image[0].length
                && image[sr][sc] == initialColor && visited[sr][sc] == false;
    }
}
