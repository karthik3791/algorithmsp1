package com.karthik.java.backtracking;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {


    private void dfs(int start, int target, List<Integer> curPath, List<List<Integer>> res) {
        for (int i = start; i <= Math.sqrt(target); i++) {
            if (target % i == 0) {
                curPath.add(i);
                List<Integer> curPathCopy = new ArrayList<>(curPath);
                curPathCopy.add(target / i);
                res.add(curPathCopy);
                dfs(i, target / i, curPath, res);
                curPath.remove(curPath.size() - 1);
            }
        }
    }


    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(2, n, new ArrayList<>(), res);
        return res;
    }
}
