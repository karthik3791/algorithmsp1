package com.karthik.java.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ReorganizeString {
    public String reorganizeString(String s) {
        int[] freqMap = new int[26];
        for (char c : s.toCharArray()) {
            freqMap[c - 'a'] += 1;
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((c1, c2) -> freqMap[c2 - 'a'] == freqMap[c1 - 'a'] ? c1 - c2 : freqMap[c2 - 'a'] - freqMap[c1 - 'a']);
        for (int i = 0; i < 26; i++)
            if (freqMap[i] > 0)
                pq.offer((char) ('a' + i));

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int k = 2;
            List<Character> tempList = new ArrayList<>();
            while (k > 0 && !pq.isEmpty()) {
                Character c = pq.poll();
                freqMap[c - 'a'] -= 1;
                if (freqMap[c - 'a'] > 0)
                    tempList.add(c);
                sb.append(c);
                k--;
            }
            pq.addAll(tempList);
            if (pq.isEmpty())
                break;
            if (k > 0)
                return "";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "aab";
        ReorganizeString r = new ReorganizeString();
        System.out.println(r.reorganizeString(s));
    }
}
