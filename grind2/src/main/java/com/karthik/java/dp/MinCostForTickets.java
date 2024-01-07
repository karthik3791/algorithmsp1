package com.karthik.java.dp;

public class MinCostForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] nextRow = new int[lastDay + 1];
        for (int d = days.length - 1; d >= 0; d--) {
            int[] tmp = new int[lastDay + 1];
            for (int until = lastDay; until >= 0; until--) {
                if (days[d] <= until)
                    tmp[until] = nextRow[until];
                else {
                    int minCost = costs[0] + nextRow[days[d]];
                    minCost = Math.min(minCost, costs[1] + nextRow[Math.min(days[d] + 6, lastDay)]);
                    minCost = Math.min(minCost, costs[2] + nextRow[Math.min(days[d] + 29, lastDay)]);
                    tmp[until] = minCost;
                }
            }
            nextRow = tmp;
        }
        return nextRow[0];
    }

    public static void main(String[] args) {
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};
        //expected = 11
        /*
        tmp[20] = 0
        tmp[19] = 2
        tmp[18] = 2
        ..
        tmp[0] = 2
        ..
        nextRow  = tmp
        ---
        tmp[20] = 2
        ..
        tmp[8] = 2
        tmp[7] =  min(4, 9, 15)
        tmp[6] = min(4,9,15)
        tmp[5] = min(4,9,15)
        tmp[4] = min(4,9,17)

        */
        MinCostForTickets m = new MinCostForTickets();
        System.out.println(m.mincostTickets(days, costs));
    }
}
