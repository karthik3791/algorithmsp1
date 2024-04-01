package com.karthik.java.greedy;

import java.util.Comparator;
import java.util.TreeMap;

public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> bst = new TreeMap<>(Comparator.comparingInt(k -> k));
        for (int i : A)
            bst.put(i, bst.getOrDefault(i, 0) + 1);
        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            Integer ceiling = bst.higherKey(B[i]);
            if (ceiling == null) {
                int smallestKey = bst.ceilingKey(0);
                bst.put(smallestKey, bst.get(smallestKey) - 1);
                if (bst.get(smallestKey) == 0)
                    bst.remove(smallestKey);
                res[i] = smallestKey;
            } else {
                res[i] = ceiling;
                bst.put(ceiling, bst.get(ceiling) - 1);
                if (bst.get(ceiling) == 0)
                    bst.remove(ceiling);
            }
        }
        return res;
    }
}
