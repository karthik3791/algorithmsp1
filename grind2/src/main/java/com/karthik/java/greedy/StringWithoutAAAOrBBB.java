package com.karthik.java.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StringWithoutAAAOrBBB {

    public String strWithout3a3b(int a, int b) {
        Map<Character, Integer> freqMap = new HashMap<>();
        freqMap.put('a', a);
        freqMap.put('b', b);
        PriorityQueue<Character> maxPQ = new PriorityQueue<>((c1, c2) -> freqMap.get(c2) - freqMap.get(c1));
        maxPQ.addAll(freqMap.keySet());
        StringBuilder sb = new StringBuilder();
        char temp = ' ';
        while (!maxPQ.isEmpty()) {
            Character nextCh = maxPQ.poll();
            if (sb.length() < 2 || !(sb.charAt(sb.length() - 1) == nextCh && sb.charAt(sb.length() - 1) == sb.charAt(sb.length() - 2))) {
                sb.append(nextCh);
                freqMap.put(nextCh, freqMap.get(nextCh) - 1);
                if (freqMap.get(nextCh) > 0)
                    maxPQ.offer(nextCh);
                if (freqMap.getOrDefault(temp, 0) > 0) {
                    maxPQ.offer(temp);
                    temp = ' ';
                }
            } else
                temp = nextCh;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int a = 2, b = 5;
        StringWithoutAAAOrBBB st = new StringWithoutAAAOrBBB();
        System.out.println(st.strWithout3a3b(a, b));
    }
}
