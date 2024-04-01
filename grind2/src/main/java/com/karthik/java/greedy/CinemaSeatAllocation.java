package com.karthik.java.greedy;

import java.util.PriorityQueue;

public class CinemaSeatAllocation {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((r1, r2) -> r1[0] == r2[0] ? r1[1] - r2[1] : r1[0] - r2[0]);
        for (int[] r : reservedSeats)
            minPQ.offer(r);
        if (minPQ.isEmpty())
            return n * 2;
        int total = ((minPQ.peek()[0]) - 1) * 2;
        int curRow = minPQ.peek()[0];
        boolean[] booked = new boolean[3];
        while (!minPQ.isEmpty()) {
            while (!minPQ.isEmpty() && minPQ.peek()[0] == curRow) {
                int[] reservation = minPQ.poll();
                if (reservation[1] == 2 || reservation[1] == 3)
                    booked[0] = true;
                else if (reservation[1] == 4 || reservation[1] == 5)
                    booked[0] = booked[1] = true;
                else if (reservation[1] == 6 || reservation[1] == 7)
                    booked[1] = booked[2] = true;
                else if (reservation[1] == 8 || reservation[1] == 9)
                    booked[2] = true;
            }
            int curRowCount = 0;
            if (!booked[0]) {
                curRowCount++;
                booked[1] = true;
            }
            if (!booked[2]) {
                curRowCount++;
                booked[1] = true;
            }
            if (!booked[1]) {
                curRowCount++;
            }
            total += curRowCount;
            booked = new boolean[3];
            curRow++;
        }
        total += (n - curRow + 1) * 2;
        return total;
    }

    public static void main(String[] args) {
        CinemaSeatAllocation c = new CinemaSeatAllocation();
        //[[2,1],[1,8],[2,6]]
        int[][] reservations = {{2, 1}, {1, 8}, {2, 6}};
        System.out.println(c.maxNumberOfFamilies(2, reservations));
    }


}
