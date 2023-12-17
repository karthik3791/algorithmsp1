package com.karthik.java;

import java.util.ArrayList;
import java.util.List;

public class PalindromePairs {


    public class TrieNode {
        int wordIndex;
        private List<Integer> palindromeSuffixIndexList;
        TrieNode[] children;

        public TrieNode() {
            wordIndex = -1;
            children = new TrieNode[26];
            palindromeSuffixIndexList = new ArrayList<>();
        }
    }

    TrieNode root;

    private void addWord(String word, int index) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            // Handles edge case where the string itself is a palindrome by adding its index to the root.
            if (isPalindrome(word, i))
                cur.palindromeSuffixIndexList.add(index);
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null)
                cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.wordIndex = index;
    }

    private boolean isPalindrome(String s, int from) {
        int left = from;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            String reverseWord = new StringBuilder(words[i]).reverse().toString();
            addWord(reverseWord, i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            TrieNode cur = root;
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                cur = cur.children[c - 'a'];
                if (cur == null) break;
                if (cur.wordIndex != -1 && isPalindrome(word, j + 1) && cur.wordIndex != i)
                    res.add(List.of(i, cur.wordIndex));
            }
            if (cur == null) continue;
            for (int idx : cur.palindromeSuffixIndexList) {
                if (idx != i)
                    res.add(List.of(i, idx));
            }
        }
        return res;
    }


    public static void main(String[] args) {
        PalindromePairs p = new PalindromePairs();
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println(p.palindromePairs(words));
    }
}
