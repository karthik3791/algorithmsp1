package com.karthik.java.greedy;

public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int[] balanceBills = new int[4];
        for (int bill : bills) {
            if (bill > 5) {
                int toReturn = bill - 5;
                int prevToReturn = 0;
                while (toReturn > 0 && prevToReturn != toReturn) {
                    prevToReturn = toReturn;
                    for (int i = 0; i < 4; i++) {
                        if (balanceBills[i] == 0) continue;
                        int balance = (i + 1) * 5;
                        if (balance <= toReturn) {
                            balanceBills[i] -= 1;
                            toReturn -= balance;
                        }
                    }
                }
                if (toReturn > 0)
                    return false;
            }
            balanceBills[(bill / 5) - 1] += 1;
        }
        return true;
    }
}
