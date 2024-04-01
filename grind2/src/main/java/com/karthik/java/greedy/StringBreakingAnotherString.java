package com.karthik.java.greedy;

public class StringBreakingAnotherString {
    public boolean checkIfCanBreak(String s1, String s2) {
        int[] freqMap1 = new int[26];
        int[] freqMap2 = new int[26];
        for (char c : s1.toCharArray())
            freqMap1[c - 'a']++;
        for (char c : s2.toCharArray())
            freqMap2[c - 'a']++;
        int count1 = 0;
        int count2 = 0;
        boolean s1SmallerThans2 = false;
        boolean s2SmallerThans1 = false;
        for (int i = 0; i < 26; i++) {
            count1 += freqMap1[i];
            count2 += freqMap2[i];
            if (count1 > count2) {
                /*
                   Given s1 and s2 have same length, this means that s2 has more frequency of characters that are bigger than that of s1.
                   This means s2 can break s1
                 */
                //But if s1 can already break s2, then we have a contradiction
                if (s2SmallerThans1) return false;
                s1SmallerThans2 = true;
            } else if (count2 > count1) {
                /*
                   Given s1 and s2 have same length, this means that s1 has more frequency of characters that are bigger than that of s2.
                   This means s1 can break s2
                 */

                //But if s2 can already break s1, then we have a contradiction
                if (s1SmallerThans2) return false;
                s2SmallerThans1 = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StringBreakingAnotherString s = new StringBreakingAnotherString();
        System.out.println(s.checkIfCanBreak("abe", "acf"));
    }

}
