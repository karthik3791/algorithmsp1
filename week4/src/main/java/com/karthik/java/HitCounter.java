package com.karthik.java;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Deque;

public class HitCounter {

    Deque<AbstractMap.SimpleEntry<Integer, Integer>> st;

    public HitCounter() {
        st = new ArrayDeque<>();
    }

    public void hit(int timestamp) {
        if (!st.isEmpty() && st.peek().getKey() == timestamp)
            st.peek().setValue(st.peek().getValue() + 1);
        else
            st.push(new AbstractMap.SimpleEntry<Integer, Integer>(timestamp, 1));
    }

    public int getHits(int timestamp) {
        int res = 0;
        for (AbstractMap.SimpleEntry<Integer, Integer> t : st) {
            if (t.getKey() < timestamp - 300)
                break;
            res += t.getValue();
        }
        return res;
    }

}
