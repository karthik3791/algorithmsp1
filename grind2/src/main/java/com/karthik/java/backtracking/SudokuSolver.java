package com.karthik.java.backtracking;

public class SudokuSolver {

    private char[][] board;

    private boolean validInput(int r, int c, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == ch)
                return false;
            if (board[i][c] == ch)
                return false;
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + i % 3] == ch)
                return false;
        }
        return true;
    }


    private boolean dfs() {
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                if (board[r][c] == '.') {
                    for (int num = 1; num <= 9; num++) {
                        if (validInput(r, c, (char) ('0' + num))) {
                            board[r][c] = (char) ('0' + num);
                            if (dfs())
                                return true;
                            board[r][c] = '.';
                        }
                    }
                    return false;
                }
        return true;
    }


    public void solveSudoku(char[][] board) {
        this.board = board;
        dfs();
    }
}
