package com.karthik.java.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        Deque<Character> st = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            Character c = num.charAt(i);
            while (k > 0 && !st.isEmpty() && st.peek() > c) {
                st.pop();
                k--;
            }
            st.push(c);
        }
        while (k > 0) {
            st.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        int size = st.size();
        for (int i = 0; i < size; i++) {
            while (i == 0 && !st.isEmpty() && st.peekLast() == '0') {
                st.removeLast();
            }
            if (!st.isEmpty())
                sb.append(st.removeLast());
        }
        String res = sb.toString();
        return res.isEmpty() ? "0" : res;
    }

    public static void main(String[] args) {
        String num1 = "1432219";
        int k1 = 3;
        String num2 = "112";
        int k2 = 1;
        RemoveKDigits r = new RemoveKDigits();
        System.out.println(r.removeKdigits(num1, k1));
        System.out.println(r.removeKdigits(num2, k2));
    }
}
