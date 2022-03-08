package com.karthik.java;

import java.util.ArrayList;
import java.util.List;

class Combinations {

    private List<List<Integer>> res;
    private int k;
    private int n;

    private void backTrack(List<Integer> currentPath, int curIndex, int startValue) {
        if (curIndex == k) {
            res.add(new ArrayList<>(currentPath));
            return;
        }
        for (int i = startValue; i <= n; i++) {
            currentPath.add(i);
            backTrack(currentPath, curIndex + 1, i + 1);
            currentPath.remove(currentPath.size() - 1);
        }
    }


    public List<List<Integer>> combine(int n, int k) {
        this.res = new ArrayList<>();
        this.k = k;
        this.n = n;
        backTrack(new ArrayList<>(), 0, 1);
        return this.res;
    }

    public static void main(String[] args) {
        Combinations c = new Combinations();
        System.out.println(c.combine(4, 2));
    }
}