package com.karthik.java.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {

    private int[] candidates;

    private void dfs(int i, int target, List<Integer> curPath, Set<List<Integer>> res) {
        if (target == 0)
            res.add(new ArrayList<>(curPath));
        if (i >= candidates.length)
            return;
        if (candidates[i] <= target) {
            curPath.add(candidates[i]);
            dfs(i, target - candidates[i], curPath, res);
            curPath.remove(curPath.size() - 1);
        }
        dfs(i + 1, target, curPath, res);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        Set<List<Integer>> res = new HashSet<>();
        dfs(0, target, new ArrayList<>(), res);
        return new ArrayList<>(res);
    }
}

class CombinationSumStyle2 {

    private int[] candidates;

    private void dfs(int start, int target, List<Integer> curPath, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curPath));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                curPath.add(candidates[i]);
                dfs(i, target - candidates[i], curPath, res);
                curPath.remove(curPath.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, target, new ArrayList<>(), res);
        return res;
    }
}

