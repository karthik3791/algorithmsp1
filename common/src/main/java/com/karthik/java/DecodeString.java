package com.karthik.java;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        Deque<Character> st = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                StringBuilder temp = new StringBuilder();
                while (!st.isEmpty() && st.peek() != '[')
                    temp.append(st.pop());
                st.pop();
                int k = 0;
                int digit = 0;
                while (!st.isEmpty() && Character.isDigit(st.peek()))
                    k += Character.getNumericValue(st.pop()) * Math.pow(10, digit++);
                String tempStr = temp.reverse().toString();
                while (k > 0) {
                    for (char ch : tempStr.toCharArray())
                        st.push(ch);
                    k--;
                }
            } else
                st.push(c);
        }
        while (!st.isEmpty())
            sb.append(st.removeLast());

        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeString d = new DecodeString();
        System.out.println(d.decodeString("3[a]2[bc]"));

    }

}
