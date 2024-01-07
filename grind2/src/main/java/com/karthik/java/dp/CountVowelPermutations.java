package com.karthik.java.dp;

import java.util.Arrays;

public class CountVowelPermutations {
    public int countVowelPermutation(int n) {
        int[] nextRow = new int[5];
        Arrays.fill(nextRow, 1);
        for (int i = n - 2; i >= 0; i--) {
            int[] tmp = new int[5];
            for (int j = 0; j < 5; j++) {
                int noOfWays = 0;
                if (j == 0) noOfWays = (noOfWays + nextRow[1]) % 1000000007;
                else if (j == 1) {
                    noOfWays = (noOfWays + nextRow[0]) % 1000000007;
                    noOfWays = (noOfWays + nextRow[2]) % 1000000007;
                } else if (j == 2) {
                    noOfWays = (noOfWays + nextRow[0]) % 1000000007;
                    noOfWays = (noOfWays + nextRow[1]) % 1000000007;
                    noOfWays = (noOfWays + nextRow[3]) % 1000000007;
                    noOfWays = (noOfWays + nextRow[4]) % 1000000007;
                } else if (j == 3) {
                    noOfWays = (noOfWays + nextRow[2]) % 1000000007;
                    noOfWays = (noOfWays + nextRow[4]) % 1000000007;
                } else noOfWays = (noOfWays + nextRow[0]) % 1000000007;
                tmp[j] = noOfWays;
            }
            nextRow = tmp;
        }
        int finalRes = 0;
        for (int i = 0; i < 5; i++)
            finalRes = (finalRes + nextRow[i]) % 1000000007;
        return finalRes;
    }
}
