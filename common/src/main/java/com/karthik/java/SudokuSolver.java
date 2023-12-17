package com.karthik.java;

import java.util.Arrays;

public class SudokuSolver {

    private boolean validEntry(char[][] board, int i, int j, char k) {
        for (int col = 0; col < 9; col++)
            if (board[i][col] == k)
                return false;

        for (int row = 0; row < board.length; row++)
            if (board[row][j] == k)
                return false;

        int startX = i % 3;
        int startY = j % 3;
        for (int l = 0; l < 3; l++)
            for (int m = 0; m < 3; m++)
                if (board[startX + l][startY + m] == k)
                    return false;

        return true;
    }

    private boolean recursiveSolve(char[][] board) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (validEntry(board, i, j, k)) {
                            System.out.println("Currently at row : " + i + " and col : " + j + ". Trying out value : " + k);
                            board[i][j] = k;
                            if (recursiveSolve(board))
                                return true;
                            board[i][j] = '.';
                        }
                    }
                }
        return false;
    }


    public void solveSudoku(char[][] board) {
        recursiveSolve(board);
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        new SudokuSolver().solveSudoku(board);
        for (int i = 0; i < 9; i++)
            System.out.println(Arrays.toString(board[i]));
    }
}
