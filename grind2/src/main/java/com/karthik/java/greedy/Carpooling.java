package com.karthik.java.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Carpooling {
    public boolean carPooling2(int[][] trips, int capacity) {
        Arrays.sort(trips, (trip1, trip2) -> trip1[1] - trip2[1]);
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((trip1, trip2) -> trip1[2] - trip2[2]);
        for (int i = 0; i < trips.length; i++) {
            int[] trip = trips[i];
            while (!minPQ.isEmpty() && minPQ.peek()[2] <= trip[1]) {
                capacity += minPQ.poll()[0];
            }
            if (capacity < trip[0])
                return false;
            capacity -= trip[0];
            minPQ.offer(trip);
        }
        return true;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        //Because only 1000 trips, use bucket sort
        int[] capacityDelta = new int[1001];
        for (int[] trip : trips) {
            capacityDelta[trip[1]] -= trip[0];
            capacityDelta[trip[2]] += trip[0];
        }
        for (int i = 0; i < 1001; i++) {
            capacity += capacityDelta[i];
            if (capacity < 0) return false;
        }
        return true;
    }
}
