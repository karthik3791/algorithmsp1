package com.karthik.java.graph;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegreeMap = new HashMap<>();
        /*
        We must construct InDegreeMap ONLY for characters that are provided in words array.
        We cannot set that for all characters in english alphabet as we do not know if these letters exist in the alien alphabet.
         */
        for (String word : words)
            for (Character c : word.toCharArray())
                inDegreeMap.put(c, 0);

        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i];
            String b = words[i + 1];
            /*
            The input can contain words followed by their prefix, for example, abcd and then ab.
            These cases will never result in a valid alphabet (because in a valid alphabet, prefixes are always first).
             */
            if (b.length() < a.length() && a.startsWith(b)) return "";
            for (int k = 0; k < Math.min(a.length(), b.length()); k++) {
                Character u = a.charAt(k);
                Character v = b.charAt(k);
                if (!u.equals(v)) {
                    graph.computeIfAbsent(u, c -> new ArrayList<>()).add(v);
                    //Every Character is already covered, so can use get instead of getOrDefault
                    inDegreeMap.put(v, inDegreeMap.get(v) + 1);
                    break;
                }
            }
        }
        //Run Kahn's Algorithm
        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character, Integer> e : inDegreeMap.entrySet())
            if (e.getValue() == 0)
                q.offer(e.getKey());

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Character u = q.poll();
            sb.append(u);
            for (Character v : graph.getOrDefault(u, new ArrayList<>())) {
                inDegreeMap.put(v, inDegreeMap.get(v) - 1);
                if (inDegreeMap.get(v) == 0)
                    q.offer(v);
            }
        }
        return sb.length() != inDegreeMap.size() ? "" : sb.toString();
    }

    public static void main(String[] args) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        AlienDictionary a = new AlienDictionary();
        a.alienOrder(words);
    }
}
