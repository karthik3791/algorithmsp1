package com.karthik.java;

import java.util.*;

public class WordSearch2_1 {
    private class TrieNode {
        private String word;
        private TrieNode[] children;

        public TrieNode() {
            word = "";
            children = new TrieNode[26];
        }
    }

    private TrieNode root;
    private boolean[][] visited;

    private void addWord(String w) {
        TrieNode cur = root;
        for (char c : w.toCharArray()) {
            if (cur.children[c - 'a'] == null)
                cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.word = w;
    }

    private Set<String> search(char[][] board, int row, int col, TrieNode cur, Set<String> res) {
        if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || cur.children[board[row][col] - 'a'] == null || visited[row][col])
            return res;
        visited[row][col] = true;
        cur = cur.children[board[row][col] - 'a'];
        if (!cur.word.equals(""))
            res.add(cur.word);
        search(board, row - 1, col, cur, res);
        search(board, row + 1, col, cur, res);
        search(board, row, col - 1, cur, res);
        search(board, row, col + 1, cur, res);
        visited[row][col] = false;
        return res;
    }


    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        visited = new boolean[board.length][board[0].length];
        Arrays.stream(words).forEach(this::addWord);
        Set<String> foundWords = new HashSet<>();

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (root.children[board[i][j] - 'a'] != null)
                    search(board, i, j, root, foundWords);

        return new ArrayList<>(foundWords);
    }
}
