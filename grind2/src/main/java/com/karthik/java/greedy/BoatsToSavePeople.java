package com.karthik.java.greedy;

import java.util.Arrays;

public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        int boatCount = 0;
        while (i <= j) {
            boatCount++;
            int weight = people[i] + people[j];
            if (weight <= limit)
                i++;
            j--;
        }
        return boatCount;
    }
}
