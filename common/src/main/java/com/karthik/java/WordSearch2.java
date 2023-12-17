package com.karthik.java;

import java.util.*;

public class WordSearch2 {

    private class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }

        public void setWord() {
            isWord = true;
        }
    }

    private TrieNode root;
    private Set<String> res;
    private boolean[][] visited;

    public void addWord(String word) {
        TrieNode cur = root;
        for (Character c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null)
                cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.setWord();
    }


    private void dfs(TrieNode root, StringBuilder sb, char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
            return;
        char ch = board[r][c];
        if (root.children[ch - 'a'] != null && !visited[r][c]) {
            TrieNode newroot = root.children[ch - 'a'];
            sb.append(ch);
            visited[r][c] = true;
            if (newroot.isWord)
                res.add(sb.toString());
            dfs(newroot, sb, board, r + 1, c);
            dfs(newroot, sb, board, r - 1, c);
            dfs(newroot, sb, board, r, c + 1);
            dfs(newroot, sb, board, r, c - 1);
            sb.deleteCharAt(sb.length() - 1);
            visited[r][c] = false;
        }
    }


    public List<String> findWords(char[][] board, String[] words) {
        this.root = new TrieNode();
        this.res = new HashSet<>();
        this.visited = new boolean[board.length][board[0].length];
        Arrays.stream(words).forEach(this::addWord);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children[board[i][j] - 'a'] != null)
                    dfs(root, sb, board, i, j);
            }
        }
        return new ArrayList<String>(res);
    }
}

