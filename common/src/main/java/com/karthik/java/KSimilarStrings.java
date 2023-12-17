package com.karthik.java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class KSimilarStrings {

    private void swap(StringBuilder sb, int src, int dest) {
        char c = sb.charAt(dest);
        sb.setCharAt(dest, sb.charAt(src));
        sb.setCharAt(src, c);
    }

    public int kSimilarity(String s1, String s2) {
        Queue<String> bfsQ = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        bfsQ.offer(s1);
        visited.add(s1);
        int k = 0;
        while (!bfsQ.isEmpty()) {
            int size = bfsQ.size();
            for (int l = 0; l < size; l++) {
                StringBuilder sb = new StringBuilder(bfsQ.poll());
                if (sb.toString().equals(s2))
                    return k;
                int i = 0;
                for (; i < sb.length(); i++)
                    if (sb.charAt(i) != s2.charAt(i))
                        break;
                for (int j = i + 1; j < sb.length(); j++) {
                    if (sb.charAt(j) == s2.charAt(i)) {
                        swap(sb, i, j);
                        String candidate = sb.toString();
                        if (!visited.contains(candidate)) {
                            visited.add(candidate);
                            bfsQ.offer(candidate);
                        }
                        swap(sb, j, i);
                    }
                }
            }
            k++;
        }
        return -1;
    }
}
