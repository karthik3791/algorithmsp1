package com.karthik.java.greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class RemoveDuplicates {

    public String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> lastVisited = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastVisited.put(s.charAt(i), i);
        }
        Set<Character> seen = new HashSet<>();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!st.isEmpty() && st.peek() > c && lastVisited.get(st.peek()) > i) {
                    seen.remove(st.pop());
                }
                st.push(c);
                seen.add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
            sb.append(st.pop());

        return sb.reverse().toString();
    }
}
