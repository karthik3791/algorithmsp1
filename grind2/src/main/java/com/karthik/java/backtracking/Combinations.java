package com.karthik.java.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    private void dfs(int start, int k, int n, List<Integer> curPath, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(curPath));
            return;
        }
        for (int i = start; i <= n; i++) {
            curPath.add(i);
            dfs(i + 1, k - 1, n, curPath, res);
            curPath.remove(curPath.size() - 1);
        }
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, k, n, new ArrayList<>(), res);
        return res;
    }
}
