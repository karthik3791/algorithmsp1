package com.karthik.java.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        Set<String> wordSet = new HashSet<>(wordDict);
        for (int i = s.length() - 1; i >= 0; i--)
            for (int j = i; j < s.length(); j++) {
                boolean res = wordSet.contains(s.substring(i, j + 1)) && dp[j + 1];
                dp[i] = res;
                if (res) break;// Remember to immediately break once we have found
            }
        return dp[0];
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        wb.wordBreak("leetcode", Arrays.asList("leet", "code"));
    }
}
