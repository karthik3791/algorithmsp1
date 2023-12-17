package com.karthik.java.backtracking;

import java.util.HashSet;
import java.util.Set;

public class MaxUniqueSubstringSplit {

    private int dfs(int start, String s, Set<String> usedSubstrings) {
        if (start == s.length() - 1)
            return usedSubstrings.size();
        int max = 1;
        for (int i = start + 1; i <= s.length(); i++) {
            String substr = s.substring(start, i);
            if (usedSubstrings.add(substr)) {
                max = Math.max(max, dfs(i, s, usedSubstrings));
                usedSubstrings.remove(substr);
            }
        }
        return max;
    }


    public int maxUniqueSplit(String s) {
        return dfs(0, s, new HashSet<>());
    }
}
