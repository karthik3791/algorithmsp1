package com.karthik.java.graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        /*
         Essentially it means we have a single node graph.
         In this case, the actual length of path is 0
         */
        if (graph.length < 2)
            return 0;
        /*
         We are going to commence a multi-source BFS.
         Usually for BFS, our seen/visited container holds the vertices that we visited.
         But in this case, we are allowed to visit a node more than once and re-use an edge more than once.
         Therefore, in this case, our seen can't just be for vertex or edge.
         It would rather be the set of vertices that we have seen so far + the vertex that we are currently on.
         We can encode the set of vertices that we have already visited so far using a Bitmask
         */
        int n = graph.length;
        int endingMask = (1 << n) - 1; //Left rotate 1 n times and then minus 1 to get N 1 bits
        boolean[][] seen = new boolean[graph.length][endingMask + 1]; // We will never use mask as 0 as we will be visiting atleast one node. So totally, our number can vary between 1 to endingMask. So must keep endingMask+1
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int vertex = i;
            int mask = 1 << i;
            q.offer(new int[]{vertex, mask});
            seen[vertex][mask] = true;
        }
        int steps = 0;
        while (!q.isEmpty()) {
            steps++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] vertexWithMask = q.poll();
                int u = vertexWithMask[0];
                int mask = vertexWithMask[1];
                for (int v : graph[u]) {
                    int newMask = mask | 1 << v;
                    if (newMask == endingMask)
                        return steps;
                    if (!seen[v][newMask]) {
                        seen[v][newMask] = true;
                        q.offer(new int[]{v, newMask});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //int[][] graph1 = {{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}};
        int[][] graph2 = {{1, 2, 3}, {0}, {0}, {0}};
        ShortestPathVisitingAllNodes s = new ShortestPathVisitingAllNodes();
        System.out.println(s.shortestPathLength(graph2));
    }

            /* Queue run for graph1

graph : [[1],[0,2,4],[1,3,4],[2],[1,2]]
queue: [(0,00001), (1,00010), (2,00100), (3,01000), (4,10000)]

step = 1
(0,00001)
queue: [(1,00010), (2,00100), (3,01000), (4,10000) :: (1,00011)]

(1,00010)
queue: [(2,00100), (3,01000), (4,10000) :: (1,00011),(0,00011),(2,00110),(4,10010)]

(2,00100)
queue: [(3,01000), (4,10000) :: (1,00011),(0,00011),(2,00110),(4,10010), (1,00110), (3,01100), (4,10100)]

(3,01000)
queue: [(4,10000) :: (1,00011),(0,00011),(2,00110),(4,10010), (1,00110), (3,01100), (4,10100), (2,01100)]

(4,10000)
queue: [(1,00011),(0,00011),(2,00110),(4,10010), (1,00110), (3,01100), (4,10100), (2,01100), (1,10010), (2,10100)]

step 2
(1,00011)
queue: [(0,00011),(2,00110),(4,10010), (1,00110), (3,01100), (4,10100), (2,01100), (1,10010), (2,10100) :: (2,00111), (4,10011)]

//Duplicate 0 to 1 and then back to 0

(0,00011)
queue: [(2,00110),(4,10010), (1,00110), (3,01100), (4,10100), (2,01100), (1,10010), (2,10100) :: (2,00111), (4,10011)]
//Duplicate 1 to 0 and then back to 1

(2,00110)
queue: [(4,10010), (1,00110), (3,01100), (4,10100), (2,01100), (1,10010), (2,10100) :: (2,00111), (4,10011), (3,01110), (4,10110) ]

//Duplicate 1 to 2 and then back to 1

(4,10010)
queue: [(1,00110), (3,01100), (4,10100), (2,01100), (1,10010), (2,10100) :: (2,00111), (4,10011), (3,01110), (4,10110), (2,10100) ]

//Duplicate 1 to 4 and then back to 1
//Duplicate 2 to 4 and then back to 2

(1,00110)
queue: [ (3,01100), (4,10100), (2,01100), (1,10010), (2,10100) :: (2,00111), (4,10011), (3,01110), (4,10110), (2,10100), (0,00111), ]

//Duplicate - 2 to 1 and then to 2
//2 to 1 and then 4 duplicat with 1 to 2 then 4


(3,01100)
queue: [ (4,10100), (2,01100), (1,10010), (2,10100) :: (2,00111), (4,10011), (3,01110), (4,10110), (2,10100), (0,00111), ]
//Duplicate - 2 to 3 then to 2

(4,10100)
queue: [ (2,01100), (1,10010), (2,10100) :: (2,00111), (4,10011), (3,01110), (4,10110), (2,10100), (0,00111), (1,10110) ]



         */
}
