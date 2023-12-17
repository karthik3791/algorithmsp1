package com.karthik.java;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {

    private Deque<Object> st;

    private int evaluateExpression() {
        if (st.isEmpty() || !(st.peek() instanceof Integer)) {
            st.push(0);
        }
        int res = (Integer) st.pop();
        while (!st.isEmpty()) {
            if (st.peek() instanceof Character && (Character) st.peek() == ')') break;
            Character op = (Character) st.pop();
            int rhs = (Integer) st.pop();
            res = op == '+' ? res + rhs : res - rhs;
        }
        return res;
    }


    public int calculate(String s) {
        st = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder(s);
        if (s.charAt(0) == '-')
            sb.reverse().append('0').reverse();
        sb.append(')').reverse().append('(');
        int num = 0;
        int mod = 0;
        //We now have a reversed string with parentheses added at start and end.
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c))
                num += (c - '0') * Math.pow(10, mod++);
            else {
                if (num > 0)
                    st.push(num);
                num = 0;
                mod = 0;
                if (c != '(')
                    st.push(c);
                else {
                    int res = evaluateExpression();
                    st.pop();
                    st.push(res);
                }
            }
        }
        return (Integer) st.peek();
    }

    public static void main(String[] args) {
        String s = " (1+(4 +5+2)-3)+(6+8 ) ";
        new BasicCalculator().calculate(s);
        assert (new BasicCalculator().calculate(s) == 23);
        assert (new BasicCalculator().calculate(" 2-1 + 2 ") == 3);
        assert (new BasicCalculator().calculate(" 1 + 1 ") == 2);
        assert (new BasicCalculator().calculate("0") == 0);
    }
}
