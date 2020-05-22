package com.leetcode.year_2020.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/queens-that-can-attack-the-king/
 * @author neeraj on 22/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class QueensThatCanAttacktheKing {

    public static void main(String[] args) {
        System.out.println(queensAttacktheKing(new int[][]{
                {0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}
        }, new int[]{0, 0}));
    }

    public static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> queensWhichCanAttack = new ArrayList<>();
        /**
         * First Place all the queens
         */
        boolean[][] board = new boolean[8][8];
        for (int[] queen : queens) {
            board[queen[0]][queen[1]] = true;
        }

        // Now we just have to move in 8 directions
        int[] directions = new int[]{-1, 0, 1};

        // We can use the same 3 directions to actually generate 8 combinations
        for (int dx : directions) {
            for (int dy : directions) {
                if (dx == 0 && dy == 0) continue; // Since it's the cell we are sitting on.

                int king_x = king[0];
                int king_y = king[1];

                while (king_x + dx >= 0 && king_x + dx < 8 &&
                        king_y + dy >= 0 && king_y + dy < 8) {
                    king_x += dx;
                    king_y += dy;

                    if (board[king_x][king_y] == true) { // means queen is here.
                        queensWhichCanAttack.add(Arrays.asList(king_x, king_y));
                        break; // Because once we hit a queen we don't want to proceed with that direction
                        // as other queen in same direction cannot over-cross this queen.
                    }
                }
            }
        }
        return queensWhichCanAttack;
    }
}
