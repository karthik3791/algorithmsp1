package com.karthik.java.backtracking;

public class WordSearch {

    private char[][] board;
    private String word;

    private boolean dfs(int r, int c, int i) {
        if (i == word.length())
            return true;
        if (r >= board.length || r < 0 || c >= board[0].length || c < 0)
            return false;
        if (board[r][c] == word.charAt(i)) {
            char tmp = board[r][c];
            board[r][c] = '\\';
            boolean res = dfs(r - 1, c, i + 1) ||
                    dfs(r + 1, c, i + 1) ||
                    dfs(r, c - 1, i + 1) ||
                    dfs(r, c + 1, i + 1);
            board[r][c] = tmp;
            return res;
        }
        return false;
    }


    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        boolean res = false;
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                res |= dfs(r, c, 0);
        return res;
    }
}
