package com.karthik.java.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumAddParenthesesValid {
    public int minAddToMakeValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            Character c = s.charAt(i);
            if (c == ')') {
                if (!st.isEmpty()) {
                    st.pop();
                    i++;
                } else {
                    while (i < s.length() && s.charAt(i) == ')') {
                        i++;
                        res++;
                    }
                }
            } else {
                st.push(c);
                i++;
            }

        }
        return res + st.size();
    }

    public static void main(String[] args) {
        String s = "()))((";
    }
}
