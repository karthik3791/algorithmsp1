package com.karthik.java;

public class IsSubsequence {

    public static boolean isSubsequence(String s, String t) {
        int a = 0, b = 0;
        if (s.length() == 0) return true;
        while (a < t.length()) {
            if (t.charAt(a) == s.charAt(b)) {
                b++;
                if (b == s.length())
                    return true;
            }
            a++;
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "";
        String t = "ahbgdc";

        IsSubsequence.isSubsequence(s, t);

    }
}
