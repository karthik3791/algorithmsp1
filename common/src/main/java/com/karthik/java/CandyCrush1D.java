package com.karthik.java;

import java.util.Stack;

public class CandyCrush1D {

    private class CharCount {
        Character c;
        Integer count;

        public CharCount(Character c, Integer count) {
            this.c = c;
            this.count = count;
        }
    }

    public String crush(String input, int k) {
        Stack<CharCount> st = new Stack<>();
        for (int i = 0; i < input.length(); ) {
            if (st.isEmpty() || !st.peek().c.equals(input.charAt(i))) {
                if (!st.isEmpty() && st.peek().count >= k)
                    st.pop();
                else {
                    st.push(new CharCount(input.charAt(i), 1));
                    i++;
                }
            } else {
                CharCount prev = st.pop();
                st.push(new CharCount(input.charAt(i), prev.count + 1));
                i++;
            }
        }

        if (!st.isEmpty() && st.peek().count >= k)
            st.pop();

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            CharCount charCount = st.pop();
            for (int i = 0; i < charCount.count; i++)
                sb.append(charCount.c);
        }
        return sb.reverse().toString();
    }

    public String recursiveCrush(String input, int index, int k, Stack<CharCount> st) {
        if (index == input.length()) {
            if (!st.isEmpty() && st.peek().count >= k)
                st.pop();

            StringBuilder sb = new StringBuilder();
            while (!st.isEmpty()) {
                CharCount charCount = st.pop();
                for (int i = 0; i < charCount.count; i++)
                    sb.append(charCount.c);
            }
            return sb.reverse().toString();
        }
        if (st.isEmpty() || !st.peek().c.equals(input.charAt(index))) {
            if (!st.isEmpty() && st.peek().count >= k) {
                Stack<CharCount> stClone = new Stack<>();
                stClone.addAll(st);
                stClone.pop();
                st.push(new CharCount(input.charAt(index), 1));
                String a = recursiveCrush(input, index + 1, k, st);
                String b = recursiveCrush(input, index, k, stClone);
                if (a.length() < b.length())
                    return a;
                else
                    return b;
            } else {
                st.push(new CharCount(input.charAt(index), 1));
                return recursiveCrush(input, index + 1, k, st);
            }
        } else {
            CharCount prev = st.pop();
            st.push(new CharCount(prev.c, prev.count + 1));
            return recursiveCrush(input, index + 1, k, st);
        }
    }

    public static void main(String[] args) {
        /*
        System.out.println(new CandyCrush1D().crush("aaabbbc", 3));
        System.out.println(new CandyCrush1D().crush("aabbbacd", 3));
        System.out.println(new CandyCrush1D().crush("aabbccddeeedcba", 3));
        System.out.println(new CandyCrush1D().crush("aaabbbacd", 3));

        System.out.println(new CandyCrush1D().recursiveCrush("aaabbbc", 0, 3, new Stack<>()));
        System.out.println(new CandyCrush1D().recursiveCrush("aabbbacd", 0, 3, new Stack<>()));
        System.out.println(new CandyCrush1D().recursiveCrush("aabbccddeeedcba", 0, 3, new Stack<>()));
        System.out.println(new CandyCrush1D().recursiveCrush("aaabbbacd", 0, 3, new Stack<>()));
*/

        System.out.println(new CandyCrush1D().recursiveCrush("cccaaabbbacd", 0, 3, new Stack<>()));
        System.out.println(new CandyCrush1D().recursiveCrush("aaabbbacd", 0, 3, new Stack<>()));
        System.out.println(new CandyCrush1D().recursiveCrush("aaaaabbba", 0, 3, new Stack<>()));
        System.out.println(new CandyCrush1D().recursiveCrush("aabbaaabbba", 0, 3, new Stack<>()));
    }

}
