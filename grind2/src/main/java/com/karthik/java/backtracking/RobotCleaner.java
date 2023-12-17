package com.karthik.java.backtracking;

import java.util.HashSet;
import java.util.Set;

interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
}

class Pair {
    int a;
    int b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

}


public class RobotCleaner {

    private Robot robot;
    Set<Pair> visited = new HashSet<>();
    //Super important for direction to be specified clockwise.
    private int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private void revertBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }


    private void backtrack(int r, int c, int dirIndex) {
        visited.add(new Pair(r, c));
        robot.clean();
        for (int i = 0; i < 4; i++) {
            int newDirIndex = (dirIndex + i) % 4;
            int[] turn = direction[newDirIndex];
            int newR = r + turn[0];
            int newC = c + turn[1];
            if (!visited.contains(new Pair(newR, newC)) && robot.move()) {
                backtrack(newR, newC, newDirIndex);
                revertBack();
            }
            robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }
}
