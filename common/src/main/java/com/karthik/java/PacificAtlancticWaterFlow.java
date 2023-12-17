package com.karthik.java;

import java.util.*;

public class PacificAtlancticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] flowsIntoPacific = new boolean[heights.length][heights[0].length];
        boolean[][] flowsIntoAtlanctic = new boolean[heights.length][heights[0].length];

        Queue<int[]> pbfsQ = new LinkedList<>();
        Queue<int[]> abfsQ = new LinkedList<>();

        for (int i = 0; i < heights.length; i++) {
            flowsIntoPacific[i][0] = true;
            pbfsQ.offer(new int[]{i, 0});
            flowsIntoAtlanctic[i][heights[0].length - 1] = true;
            abfsQ.offer(new int[]{i, heights[0].length - 1});
        }

        for (int i = 0; i < heights[0].length; i++) {
            flowsIntoPacific[0][i] = true;
            if (i > 0)
                pbfsQ.offer(new int[]{0, i});
            flowsIntoAtlanctic[heights.length - 1][i] = true;
            if (i < heights[0].length - 1)
                abfsQ.offer(new int[]{heights.length - 1, i});
        }

        while (!pbfsQ.isEmpty()) {
            int[] curCell = pbfsQ.poll();
            int curRow = curCell[0];
            int curCol = curCell[1];
            int curHeight = heights[curRow][curCol];
            for (int i = curRow - 1; i <= curRow + 1; i++)
                if (i >= 0 && i < heights.length && heights[i][curCol] >= curHeight && !flowsIntoPacific[i][curCol]) {
                    flowsIntoPacific[i][curCol] = true;
                    pbfsQ.offer(new int[]{i, curCol});
                }
            for (int i = curCol - 1; i <= curCol + 1; i++)
                if (i >= 0 && i < heights[0].length && heights[curRow][i] >= curHeight && !flowsIntoPacific[curRow][i]) {
                    flowsIntoPacific[curRow][i] = true;
                    pbfsQ.offer(new int[]{curRow, i});
                }
        }

        while (!abfsQ.isEmpty()) {
            int[] curCell = abfsQ.poll();
            int curRow = curCell[0];
            int curCol = curCell[1];
            int curHeight = heights[curRow][curCol];
            for (int i = curRow - 1; i <= curRow + 1; i++)
                if (i >= 0 && i < heights.length && heights[i][curCol] >= curHeight && !flowsIntoAtlanctic[i][curCol]) {
                    flowsIntoAtlanctic[i][curCol] = true;
                    abfsQ.offer(new int[]{i, curCol});
                }
            for (int i = curCol - 1; i <= curCol + 1; i++)
                if (i >= 0 && i < heights[0].length && heights[curRow][i] >= curHeight && !flowsIntoAtlanctic[curRow][i]) {
                    flowsIntoAtlanctic[curRow][i] = true;
                    abfsQ.offer(new int[]{curRow, i});
                }
        }

        for (int i = 0; i < heights.length; i++)
            for (int j = 0; j < heights[0].length; j++)
                if (flowsIntoPacific[i][j] && flowsIntoAtlanctic[i][j])
                    res.add(List.of(i, j));

        return res;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Map<Integer, Integer> inDegreeMap = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            adjMap.put(i, new ArrayList<>());
            inDegreeMap.put(i, 0);
        }

        for (int[] ve : prerequisites) {
            adjMap.get(ve[1]).add(ve[0]);
            inDegreeMap.put(ve[0], inDegreeMap.getOrDefault(ve[0], 0) + 1);
        }

        Queue<Integer> sourceQueue = new LinkedList<>();
        List<Integer> topoSortList = new ArrayList<>();

        for (Map.Entry<Integer, Integer> e : inDegreeMap.entrySet())
            if (e.getValue() == 0)
                sourceQueue.offer(e.getKey());

        while (!sourceQueue.isEmpty()) {
            Integer v = sourceQueue.poll();
            topoSortList.add(v);
            for (Integer w : adjMap.get(v)) {
                inDegreeMap.put(w, inDegreeMap.get(w) - 1);
                if (inDegreeMap.get(w) == 0)
                    sourceQueue.offer(w);
            }
        }

        return topoSortList.stream().mapToInt(x->x).toArray();

    }

    public static void main(String[] args) {
        int[][] heights = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        List<List<Integer>> res = new PacificAtlancticWaterFlow().pacificAtlantic(heights);

        System.out.println(res);
    }
}
