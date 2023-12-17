package com.karthik.java;

import java.util.*;
import java.util.stream.Collectors;

public class SmallestStringsWithSwaps {

    private int find(int x, int[] root) {
        if (x == root[x])
            return x;
        else
            return root[x] = find(root[x], root);
    }

    private void union(int x, int y, int[] root, int[] rank) {
        int rootx = find(x, root);
        int rooty = find(y, root);
        if (rootx == rooty) return;
        if (rank[rootx] > rank[rooty])
            root[rooty] = rootx;
        else if (rank[rootx] < rank[rooty])
            root[rootx] = rooty;
        else {
            root[rooty] = rootx;
            rank[rootx]++;
        }
    }


    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] root = new int[n];
        int[] rank = new int[n];
        Arrays.fill(rank, 1);
        for (int i = 0; i < n; i++)
            root[i] = i;
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1), root, rank);
        }
        Map<Integer, List<Integer>> charMap = new HashMap<>();
        for (int i = 0; i < n; i++)
            charMap.compute(find(i, root), (k, v) -> (v == null) ? new ArrayList<>() : v).add(i);
        StringBuilder sb = new StringBuilder(s);
        for (Map.Entry<Integer, List<Integer>> e : charMap.entrySet()) {
            int rootId = e.getKey();
            List<Character> charsInGroup = e.getValue().stream().map(s::charAt).sorted().collect(Collectors.toList());
            for (int index : e.getValue()) {
                sb.setCharAt(index, charsInGroup.remove(0));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /*
        "dcab"
[[0,3],[1,2]]
"dcab"
[[0,3],[1,2],[0,2]]
"cba"
[[0,1],[1,2]]

"zbxxxdgmbz"
[[1,0],[7,1],[9,1],[3,0],[7,1],[0,4],[6,5],[2,6],[6,4],[6,0]]
         */
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 0));
        input.add(Arrays.asList(7, 1));
        input.add(Arrays.asList(9, 1));
        input.add(Arrays.asList(3, 0));
        input.add(Arrays.asList(7, 1));
        input.add(Arrays.asList(0, 4));
        input.add(Arrays.asList(6, 5));
        input.add(Arrays.asList(2, 6));
        input.add(Arrays.asList(6, 4));
        input.add(Arrays.asList(6, 0));
        SmallestStringsWithSwaps sol = new SmallestStringsWithSwaps();
        System.out.println(sol.smallestStringWithSwaps("zbxxxdgmbz", input));
    }
}
