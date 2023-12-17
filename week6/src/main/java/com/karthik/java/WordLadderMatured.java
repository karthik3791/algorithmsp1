package com.karthik.java;

import java.util.*;

public class WordLadderMatured {


    private boolean differsByOneChar(String s1, String s2) {
        int misMatch = 0;
        for (int i = 0; i < s1.length(); i++)
            if (s1.charAt(i) != s2.charAt(i))
                misMatch++;
        return misMatch == 1;
    }

    private Map<String, List<String>> constructWordGraph(List<String> wordList) {
        Map<String, List<String>> wordGraph = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            String s = wordList.get(i);
            if (!wordGraph.containsKey(s))
                wordGraph.put(s, new ArrayList<>());
            for (int j = i + 1; j < wordList.size(); j++) {
                String w = wordList.get(j);
                if (differsByOneChar(s, w)) {
                    wordGraph.get(s).add(w);
                    if (!wordGraph.containsKey(w))
                        wordGraph.put(w, new ArrayList<>());
                    wordGraph.get(w).add(s);
                }
            }
        }
        return wordGraph;
    }


    private Map<String, Integer> bfs(String source, Map<String, List<String>> wordGraph) {
        Map<String, Integer> res = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> bfsQ = new LinkedList<>();
        bfsQ.offer(source);
        visited.add(source);
        int step = 0;
        while (!bfsQ.isEmpty()) {
            int size = bfsQ.size();
            for (int i = 0; i < size; i++) {
                String s = bfsQ.poll();
                res.put(s, step);
                for (String w : wordGraph.get(s)) {
                    if (!visited.contains(w)) {
                        visited.add(w);
                        bfsQ.offer(w);
                    }
                }
            }
            step++;
        }
        return res;
    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        Map<String, List<String>> wordGraph = constructWordGraph(wordList);
        System.out.println("Word Graph " + wordGraph);
        int minTransform = Integer.MAX_VALUE;
        for (String s : wordList) {
            if (beginWord.equals(s) || differsByOneChar(beginWord, s)) {
                Map<String, Integer> bfsRes = bfs(s, wordGraph);
                System.out.println("BFS Result from Source : " + s + " is : " + bfsRes);
                if (bfsRes.containsKey(endWord))
                    minTransform = Math.min(minTransform, (beginWord.equals(s) ? 0 : 1) + bfsRes.get(endWord));
            }
        }
        return minTransform == Integer.MAX_VALUE ? 0 : 1 + minTransform;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        WordLadderMatured wl = new WordLadderMatured();
        wl.ladderLength(beginWord, endWord, wordList);
    }

}
