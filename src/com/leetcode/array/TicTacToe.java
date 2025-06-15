package com.leetcode.array;

public class TicTacToe {

    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame(3);
        System.out.println(ticTacToeGame.move(0, 0, 1)); // Player 1 moves, should return 0
        System.out.println(ticTacToeGame.move(0, 2, 2)); // Player 2 moves, should return 0
        System.out.println(ticTacToeGame.move(2, 2, 1)); // Player 1 moves, should return 0
        System.out.println(ticTacToeGame.move(1, 1, 2)); // Player 2 moves, should return 0
        System.out.println(ticTacToeGame.move(2, 0, 1)); // Player 1 moves, should return 0
        System.out.println(ticTacToeGame.move(1, 0, 2)); // Player 2 moves, should return 0
        System.out.println(ticTacToeGame.move(2, 1, 1)); // Player 1 moves, should return 1 (Player 1 wins)
    }

    static class TicTacToeGameOptimal {
        int n;
        int[] rows;
        int[] cols;
        int leftDiagonal;
        int antiDiagonal;

        public TicTacToeGameOptimal(int n) {
            this.n = n;
            this.rows = new int[n];
            this.cols = new int[n];
            this.leftDiagonal = 0;
            this.antiDiagonal = 0;
        }

        public int move(int row, int col, int player) {
            int moveToMake = player == 1 ? 1 : -1;

            rows[row] += moveToMake;
            cols[col] += moveToMake;
            if (row == col) leftDiagonal += moveToMake;
            if (row + col == n - 1) antiDiagonal += moveToMake;

            if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(leftDiagonal) == n || Math.abs(antiDiagonal) == n) {
                return player;
            }
            return 0;
        }

        public boolean isWin(int[][] board, int row, int col, int player) {
            int rows = 0, cols = 0, diagonal = 0, antiDiagonal = 0;

            board[row][col] = player; // Make the move

            for (int i = 0; i < n; i++) { // We will try moving from top to bottom, since it's n*n
                // so that will help use using the single loop to update all our variable

                // Checking row: by using i as col mover, and fixing row
                if (board[row][i] == player) rows++;

                // Checking col; by using i as row mover, fixing col
                if (board[i][col] == player) cols++;

                // Checking diagonal
                if (board[i][i] == player) diagonal++;

                // Checking anti-diagonal
                if (board[i][n - 1 - i] == player) antiDiagonal++;

            }
            return rows == n || cols == n || diagonal == n || antiDiagonal == n;
        }
    }

    static class TicTacToeGame {
        int[][] board;

        public TicTacToeGame(int n) {
            board = new int[n][n];
        }

        private static boolean isWinningCol(int[][] board, int col, int player) {
            for (int[] row : board) {
                if (row[col] != player) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isWinningRow(int[][] board, int row, int player) {
            for (int i = 0; i < board[0].length; i++) {
                // Move cols
                if (board[row][i] != player) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isWinningRightDiagonal(int[][] board, int player) {
            int m = 0, n = board[0].length - 1;
            while (m < board.length && n >= 0) {
                if (board[m][n] != player) {
                    return false;
                }
                m++;
                n--;
            }
            return true;
        }

        private static boolean isWinningLeftDiagonal(int[][] board, int player) {
            int m = 0, n = 0;
            while (m < board.length && n < board[0].length) {
                if (board[m][n] != player) {
                    return false;
                }
                m++;
                n++;
            }
            return true;
        }

        public int move(int row, int col, int player) {
            board[row][col] = player;
            if (isWinning(board, row, col, player)) {
                return player;
            }
            return 0;
        }

        private boolean isWinning(int[][] board, int row, int col, int player) {
            if (isWinningRow(board, row, player)) return true;
            if (isWinningCol(board, col, player)) return true;
            if (row == col && isWinningLeftDiagonal(board, player)) return true;
            return row + col == board.length - 1 && isWinningRightDiagonal(board, player);
        }
    }
}
