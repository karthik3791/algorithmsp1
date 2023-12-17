package com.karthik.java.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    private int[] Q;

    private List<String> convertToOutput() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < Q.length; i++) {
            if (Q[i] == -1)
                throw new IllegalArgumentException("Queen position must never be -1");
            char[] output = new char[Q.length];
            for (int j = 0; j < Q[i]; j++)
                output[j] = '.';
            output[Q[i]] = 'Q';
            for (int j = Q[i] + 1; j < Q.length; j++)
                output[j] = '.';
            res.add(String.valueOf(output));
        }
        return res;
    }

    private boolean isValidQueenPosition(int r, int c) {
        for (int r1 = 0; r1 < r; r1++) {
            if (Q[r1] == c || (r - c == r1 - Q[r1]) || (r + c == r1 + Q[r1])) {
                System.out.println("Outcome for row: " + r + " and column : " + c + " is false");
                return false;
            }
        }
        System.out.println("Outcome for row: " + r + " and column : " + c + " is true");
        return true;
    }


    private void dfs(int r, List<List<String>> res) {
        if (r >= Q.length) {
            res.add(convertToOutput());
            return;
        }
        for (int c = 0; c < Q.length; c++) {
            if (isValidQueenPosition(r, c)) {
                System.out.println("Placing queen for row :" + r + " at column : " + c);
                Q[r] = c;
                dfs(r + 1, res);
                Q[r] = -1;
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.Q = new int[n];
        System.out.println("Q.length =" + Q.length);
        Arrays.fill(Q, -1);
        List<List<String>> res = new ArrayList<>();
        dfs(0, res);
        System.out.println("Final output : " + res);
        return res;
    }

    public static void main(String[] args) {
        int n = 4;
        new NQueens().solveNQueens(n);
    }
}
