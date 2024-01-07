package com.karthik.java.dp;

public class StringsInterleaving {

    public boolean isInterleave(String s1, String s2, String s3) {
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        //Base case
        dp[s1.length()][s2.length()] = true;
        if (s1.length() + s2.length() != s3.length()) return false;
        for (int i = s1.length(); i >= 0; i--) {
            for (int j = s2.length(); j >= 0; j--) {
                if (i == s1.length() && j == s2.length()) continue;
                boolean res = false;
                if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j))
                    res = dp[i + 1][j];
                if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j))
                    res |= dp[i][j + 1];
                dp[i][j] = res;
            }
        }
        return dp[0][0];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        boolean[][][] dp = new boolean[s1.length() + 1][s2.length() + 1][s3.length() + 1];
        //Base case
        dp[s1.length()][s2.length()][s3.length()] = true;
        //s3.length - k = s1.length()-i + s2.length()-j
        // k = i+j

        for (int k = s3.length() - 1; k >= 0; k--) {
            for (int j = s2.length(); j >= 0; j--) {
                for (int i = s1.length(); i >= 0; i--) {
                    //Are the sum of substrings adding up ?
                    if (s1.substring(i).length() + s2.substring(j).length() == s3.substring(k).length()) {
                        boolean res = false;
                        if (i < s1.length() && s1.charAt(i) == s3.charAt(k))
                            res = dp[i + 1][j][k + 1];
                        if (j < s2.length() && s2.charAt(j) == s3.charAt(k))
                            res |= dp[i][j + 1][k + 1];
                        dp[i][j][k] = res;
                    }
                }
            }
        }
        return dp[0][0][0];
    }

    public boolean isInterleave3(String s1, String s2, String s3) {
        //3 State Variables -> index on s1, s2 and s3
        /*
         dp[i][j][k] = {
            boolean res = false;
            int i1=i;
            int k1=k;
            int j1=j;
            while(i1 < s1.length() && k1 < s3.length() && s1.charAt(i1) == s3.charAt(k1)){
               res |= dp[i1+1][j][k1+1];
               i1++;
               k1++;
            }
            k1=k;
            while(j1<s2.length() && k1< s3.length() && s2.charAt(j1) == s3.charAt(k1)){
               res|= dp[i][j1+1][k1+1];
               j1++;
               k1++;
            }
            return res
         }
         */
        boolean[][][] dp = new boolean[s1.length() + 1][s2.length() + 1][s3.length() + 1];
        //Base case
        dp[s1.length()][s2.length()][s3.length()] = true;

        for (int k = s3.length() - 1; k >= 0; k--) {
            for (int j = s2.length(); j >= 0; j--) {
                for (int i = s1.length(); i >= 0; i--) {
                    //Are the sum of substrings adding up ?
                    if (s1.substring(i).length() + s2.substring(j).length() == s3.substring(k).length()) {
                        boolean res = false;
                        int i1 = i;
                        int k1 = k;
                        while (i1 < s1.length() && k1 < s3.length() && s1.charAt(i1) == s3.charAt(k1)) {
                            res |= dp[i1 + 1][j][k1 + 1];
                            i1++;
                            k1++;
                        }
                        int j1 = j;
                        k1 = k;
                        while (j1 < s2.length() && k1 < s3.length() && s2.charAt(j1) == s3.charAt(k1)) {
                            res |= dp[i][j1 + 1][k1 + 1];
                            j1++;
                            k1++;
                        }
                        dp[i][j][k] = res;
                    }
                }
            }
        }
        return dp[0][0][0];
    }

    public static void main(String[] args) {
        StringsInterleaving s = new StringsInterleaving();
        String s1 = "c", s2 = "a", s3 = "ac";
        System.out.println(s.isInterleave3(s1, s2, s3));
    }
}
