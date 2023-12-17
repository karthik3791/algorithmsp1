package com.karthik.java.dp;

public class DecodeWays {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            for (int j = i; j < i + 2 && j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                int val = Integer.parseInt(sub);
                if (val >= 1 && val <= 26)
                    dp[i] += dp[j + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.out.println(d.numDecodings("06"));
    }
}
