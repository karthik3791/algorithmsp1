package com.karthik.java.greedy;

public class GasStation {

    public int canCompleteCircuitOld(int[] gas, int[] cost) {
        for (int startIndex = 0; startIndex < gas.length; startIndex++) {
            if (gas[startIndex] < cost[startIndex]) continue;
            int capacity = gas[startIndex] - cost[startIndex];
            int endIndex = (startIndex + 1) % gas.length;
            for (; endIndex != startIndex; endIndex = (endIndex + 1) % gas.length) {
                if (capacity + gas[endIndex] < cost[endIndex]) break;
                capacity = capacity + gas[endIndex] - cost[endIndex];
            }
            if (endIndex == startIndex) return startIndex;
        }
        return -1;
    }


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int start = 0;
        int capacity = 0;
        int totalCapacity = 0;
        int i = 0;
        while (i < n) {
            capacity += gas[i] - cost[i];
            totalCapacity += gas[i] - cost[i];
            if (capacity < 0) {
                start = i + 1;
                capacity = 0;
            }
            i++;
        }
        return totalCapacity > 0 ? start : -1;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};
        GasStation g = new GasStation();
        System.out.println(g.canCompleteCircuit(gas, cost));
    }
}
