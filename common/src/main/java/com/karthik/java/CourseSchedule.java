package com.karthik.java;

import java.util.*;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            adjMap.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        for (int[] prerequisite : prerequisites) {
            int b = prerequisite[1];
            int a = prerequisite[0];
            List<Integer> adj = adjMap.get(b);
            adj.add(a);
            adjMap.put(b, adj);
            inDegree.put(a, inDegree.get(a) + 1);
        }

        //Kahns Algo

        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int k : inDegree.keySet()) {
            if (inDegree.get(k) == 0)
                q.offer(k);
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            res.add(v);
            for (int w : adjMap.get(v)) {
                inDegree.put(w, inDegree.get(w) - 1);
                if (inDegree.get(w) == 0)
                    q.offer(w);
            }
        }
        return res.size() == numCourses;

    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (dq.peek() != null && i - dq.peek() >= k) {
                dq.remove();
            }
            while (dq.peekLast() != null && nums[dq.peekLast()] <= nums[i]) {
                dq.removeLast();
            }
            dq.add(i);
            if (i >= k - 1) {
                ans[i - k + 1] = nums[dq.peek()];
            }
        }
        return ans;
    }
}
