package com.karthik.java.greedy;

import java.util.Arrays;

public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        int content = 0;
        while (i < g.length && j < s.length) {
            while (j < s.length && s[j] < g[i]) {
                j++;
            }
            if (j < s.length)
                content++;
            i++;
            j++;
        }
        return content;
    }
}
