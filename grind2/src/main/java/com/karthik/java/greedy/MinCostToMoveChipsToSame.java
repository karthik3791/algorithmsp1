package com.karthik.java.greedy;

public class MinCostToMoveChipsToSame {
    public int minCostToMoveChips(int[] position) {
        int[] oddEvenCount = new int[2];
        for (int pos : position)
            oddEvenCount[pos % 2] += 1;
        return Math.min(oddEvenCount[0], oddEvenCount[1]);
    }
}
