package com.karthik.java;

import java.util.ArrayList;
import java.util.List;

public class AdditiveNumber {

    private boolean backtrack(String num, int startIndex, List<Long> currentPath) {
        System.out.println("Current Path " + currentPath + " Start Index " + startIndex);
        if (startIndex == num.length()) {
            return currentPath.size() > 2;
        }
        Long lastTwoSum = null;
        if (currentPath.size() >= 2) {
            lastTwoSum = currentPath.get(currentPath.size() - 1) + currentPath.get(currentPath.size() - 2);
        }
        int j = startIndex + 1;
        do {
            String candidateStr = num.substring(startIndex, j);
            if (candidateStr.length() > Long.valueOf(Long.MAX_VALUE).toString().length())
                break;
            Long currentCandidate = Long.valueOf(num.substring(startIndex, j));
            if ((lastTwoSum != null && !currentCandidate.equals(lastTwoSum))) {
                j++;
                continue;
            }
            currentPath.add(currentCandidate);
            if (backtrack(num, j, currentPath))
                return true;
            currentPath.remove(currentPath.size() - 1);
            j++;
        } while (num.charAt(startIndex) != '0' && j <= num.length());
        return false;
    }

    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) return false;
        return backtrack(num, 0, new ArrayList<>());
    }

    public static void main(String[] args) {
        System.out.println(new AdditiveNumber().isAdditiveNumber("199100199"));
        System.out.println(new AdditiveNumber().isAdditiveNumber("111"));
        System.out.println(new AdditiveNumber().isAdditiveNumber("101"));
        System.out.println(new AdditiveNumber().isAdditiveNumber("1203"));
        System.out.println(new AdditiveNumber().isAdditiveNumber("19910011992"));

    }
}