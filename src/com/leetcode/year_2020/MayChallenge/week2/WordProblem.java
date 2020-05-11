package com.leetcode.year_2020.MayChallenge.week2;

/**
 * @author neeraj on 11/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WordProblem {

    public static void main(String[] args) {
        System.out.println(exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCB"));
    }

    public static boolean exist(char[][] board, String word) {
        boolean wordExist = false;
        boolean[][] visited;
        int charIndex = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(charIndex)) { // We are just interested in start of item.
                    visited = new boolean[board.length][board[0].length];
                    if (dfs(board, i, j, 1, word, visited)) {
                        wordExist = true;
                        break;
                    }
                    charIndex = 0;
                }
            }
        }
        return wordExist;
    }

    static int[] rows = new int[]{-1, 0, 1, 0};
    static int[] cols = new int[]{0, 1, 0, -1};

    private static boolean dfs(char[][] board, int row, int col, int charIndex, String word, boolean[][] visited) {
        if (charIndex == word.length()) {
            return true;
        } else {
            visited[row][col] = true;
        }
        // travel in 4 directions.
        for (int i = 0; i < 4; i++) {
            if (isSafe(board, row + rows[i], col + cols[i], charIndex, word, visited)) {
                if (dfs(board, row + rows[i], col + cols[i], charIndex + 1, word, visited)) {
                    return true;
                }
            }
        }
        visited[row][col] = false;
        return false;
    }

    private static boolean isSafe(char[][] board, int row, int col, int charIndex, String word, boolean[][] visited) {
        return row >= 0 && col >= 0 && row < board.length && col < board[0].length
                && board[row][col] == word.charAt(charIndex) && visited[row][col] == false;
    }
}
