package com.karthik.java;

public class NQueens {
    private int[][] board;
    private int N;
    private int count;

    private boolean isUnderAttack(int row, int col) {
        return board[row][col] > 0;
    }

    private void placeQueen(int row, int col) {
        board[row][col]++;
        for (int i = 0; i < N; i++) {
            if (i != col)
                board[row][i]++;
            if (i != row)
                board[i][col]++;
        }
        for (int r = 0; r < N; r++) {
            int c1 = row + col - r;
            if (c1 >= 0 && c1 < N && r != row && c1 != col)
                board[r][c1]++;
            int c2 = r - row + col;
            if (c2 >= 0 && c2 < N && r != row && c2 != col)
                board[r][c2]++;
        }
    }

    private void removeQueen(int row, int col) {
        board[row][col]--;
        for (int i = 0; i < N; i++) {
            if (i != col)
                board[row][i]--;
            if (i != row)
                board[i][col]--;
        }
        for (int r = 0; r < N; r++) {
            int c1 = row + col - r;
            if (c1 >= 0 && c1 < N && r != row && c1 != col)
                board[r][c1]--;
            int c2 = r - row + col;
            if (c2 >= 0 && c2 < N && r != row && c2 != col)
                board[r][c2]--;
        }
    }

    private void recursiveSolve(int row) {
        for (int col = 0; col < N; col++) {
            if (!isUnderAttack(row, col)) {
                placeQueen(row, col);
                if (row + 1 == N)
                    this.count++;
                else
                    recursiveSolve(row + 1);
                removeQueen(row, col);
            }
        }
    }

    public int totalNQueens(int n) {
        board = new int[n][n];
        this.N = n;
        this.count = 0;
        recursiveSolve(0);
        return this.count;
    }


    public static void main(String[] args) {
        NQueens nq = new NQueens();
        nq.totalNQueens(4);
        System.out.println(nq.count);
    }
}