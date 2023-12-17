package com.karthik.java;

import java.util.Arrays;

public class Maze1 {
    private boolean[][] visited;

    private boolean backTrack(int[][] maze, int[] start, int[] destination) {
        if (start[0] < 0 || start[0] >= maze.length) return false;
        if (Arrays.equals(start, destination)) return true;
        int toprow = start[0];
        for (; toprow > 0 && maze[toprow - 1][start[1]] == 0; toprow--) ;
        int bottomrow = start[0];
        for (; bottomrow < maze.length - 1 && maze[bottomrow + 1][start[1]] == 0; bottomrow++) ;
        int leftCol = start[1];
        for (; leftCol > 0 && maze[start[0]][leftCol - 1] == 0; leftCol--) ;
        int rightCol = start[1];
        for (; rightCol < maze[0].length - 1 && maze[start[0]][rightCol + 1] == 0; rightCol++) ;

        if (!visited[toprow][start[1]]) {
            visited[toprow][start[1]] = true;
            int[] newstart = {toprow, start[1]};
            if (backTrack(maze, newstart, destination))
                return true;
        }

        if (!visited[bottomrow][start[1]]) {
            visited[bottomrow][start[1]] = true;
            int[] newstart = {bottomrow, start[1]};
            if (backTrack(maze, newstart, destination))
                return true;
        }

        if (!visited[start[0]][leftCol]) {
            visited[start[0]][leftCol] = true;
            int[] newstart = {start[0], leftCol};
            if (backTrack(maze, newstart, destination))
                return true;
        }

        if (!visited[start[0]][rightCol]) {
            visited[start[0]][rightCol] = true;
            int[] newstart = {start[0], rightCol};
            if (backTrack(maze, newstart, destination))
                return true;
        }
        return false;
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        return backTrack(maze, start, destination);
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};
        int[] end = {3, 2};
        System.out.println("Maze Traversal Result : " + new Maze1().hasPath(maze, start, end));
    }
}
