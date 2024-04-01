package com.karthik.java.greedy;

import java.util.HashSet;
import java.util.Set;

public class WalkingRobotSimulation {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles)
            obstacleSet.add(obstacle[0] + "-" + obstacle[1]);
        int maxRes = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int d = 1; // start off facing north
        int[] cur = {0, 0};
        int i = 0;
        while (i < commands.length) {
            int c = commands[i];
            if (c == -1)
                d = (d + 1) % 4;
            else if (c == -2)
                d = ((d - 1) + 4) % 4;
            else {
                while (c > 0) {
                    int[] newCur = {cur[0] + dir[d][0], cur[1] + dir[d][1]};
                    if (!obstacleSet.contains(newCur[0] + "-" + newCur[1])) {
                        cur = newCur;
                        c--;
                    } else
                        c = 0;
                }
            }
            maxRes = Math.max(maxRes, (cur[0] * cur[0]) + (cur[1] * cur[1]));
            i++;
        }
        return maxRes;
    }
}
