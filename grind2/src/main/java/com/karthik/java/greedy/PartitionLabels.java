package com.karthik.java.greedy;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int[] lastseen = new int[26];
        for (int i = 0; i < s.length(); i++)
            lastseen[s.charAt(i) - 'a'] = i;
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            endIndex = Math.max(endIndex, lastseen[s.charAt(i) - 'a']);
            if (endIndex == i) {
                res.add(endIndex - startIndex + 1);
                startIndex = endIndex + 1;
            }
        }
        return res;
    }
}
