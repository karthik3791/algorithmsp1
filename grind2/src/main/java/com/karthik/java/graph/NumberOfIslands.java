package com.karthik.java.graph;

public class NumberOfIslands {

    char[][] grid;

    private void dfs(int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1')
            return;
        grid[r][c] = '2';
        for (int i = -1; i <= 1; i += 2) {
            dfs(r + i, c);
            dfs(r, c + i);
        }
    }

    public int numIslands(char[][] grid) {
        this.grid = grid;
        int res = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    res++;
                }
            }
        return res;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        NumberOfIslands ni = new NumberOfIslands();
        ni.numIslands(grid);
    }
}
