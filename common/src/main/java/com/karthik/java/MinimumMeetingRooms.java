package com.karthik.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class MinimumMeetingRooms {

    private class Interval {
        int start;
        int end;

        public Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }


    public int minMeetingRooms2(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1]));
        for (int[] i : intervals) {
            if (!minPQ.isEmpty() && minPQ.peek()[1] <= i[0])
                minPQ.poll();
            minPQ.offer(i);
        }
        return minPQ.size();
    }

    public int minMeetingRooms(int[][] intervals) {
        List<Interval> intervalList = Arrays.stream(intervals).map(ia -> new Interval(ia[0], ia[1])).collect(Collectors.toList());
        Collections.sort(intervalList, (i1, i2) -> Integer.compare(i1.start, i2.start));
        PriorityQueue<Interval> minPQ = new PriorityQueue<>((i1, i2) -> Integer.compare(i1.end, i2.end));
        int minMeetingRooms = 0;
        for (Interval i : intervalList) {
            while (!minPQ.isEmpty() && minPQ.peek().end <= i.start)
                minPQ.poll();
            minPQ.offer(i);
            minMeetingRooms = Math.max(minMeetingRooms, minPQ.size());
        }
        return minMeetingRooms;
    }

    public static void main(String[] args) {
        MinimumMeetingRooms mr = new MinimumMeetingRooms();
        int[][] input = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        System.out.println(mr.minMeetingRooms(input));
    }

}
