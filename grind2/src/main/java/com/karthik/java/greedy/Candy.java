package com.karthik.java.greedy;

import java.util.Arrays;

public class Candy {

    private int sum(int n) {
        return n * (n + 1) / 2;
    }

    public int candy(int[] ratings) {
        int candies = 0;
        int oldSlope = 0;
        int up = 0;
        int down = 0;
        for (int i = 1; i < ratings.length; i++) {
            int newSlope = (ratings[i] > ratings[i - 1]) ? 1 : (ratings[i] < ratings[i - 1]) ? -1 : 0;
            if (oldSlope == 1 && newSlope == 0 || oldSlope == -1 && newSlope >= 0) {
                candies += sum(up) + sum(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }
            if (newSlope == 1)
                up++;
            else if (newSlope == -1)
                down++;
            else
                candies++;
            oldSlope = newSlope;
        }
        return candies;
    }


    public int candyOld(int[] ratings) {
        int[] count = new int[ratings.length];
        Arrays.fill(count, 1);
        int i = 0;
        while (i < ratings.length - 1) {
            if (ratings[i + 1] > ratings[i] && count[i + 1] <= count[i])
                count[i + 1] = count[i] + 1;
            i++;
        }
        int j = ratings.length - 1;
        while (j > 0) {
            if (ratings[j - 1] > ratings[j] && count[j - 1] <= count[j])
                count[j - 1] = count[j] + 1;
            j--;
        }
        return Arrays.stream(count).sum();
    }

    public static void main(String[] args) {
        System.out.println(new Candy().candy(new int[]{1, 0, 2}));
        System.out.println(new Candy().candy(new int[]{1, 2, 2}));
        System.out.println(new Candy().candy(new int[]{1, 3, 2, 2, 1}));
        System.out.println(new Candy().candy(new int[]{1, 2, 87, 87, 87, 2, 1}));
        System.out.println(new Candy().candy(new int[]{1, 3, 4, 5, 2}));
        //1,2,3,1,2,2,1
    }
}
