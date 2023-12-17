package com.karthik.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParantheses {
    private Set<String> res;
    private String str;

    private void recursive(String s, StringBuilder sb, int curIndex, int leftCount, int rightCount, int leftRemove, int rightRemove) {
        if (curIndex == s.length()) {
            if (leftRemove == 0 && rightRemove == 0)
                res.add(sb.toString());
            return;
        }
        char c = s.charAt(curIndex);
        if ((c == '(' && leftRemove > 0) || (c == ')' && rightRemove > 0))
            recursive(s, sb, curIndex + 1, leftCount, rightCount, c == '(' ? leftRemove - 1 : leftRemove, c == ')' ? rightRemove - 1 : rightRemove);
        sb.append(c);
        if (c != '(' && c != '(')
            recursive(s, sb, curIndex + 1, leftCount, rightCount, leftRemove, rightRemove);
        else if (c == '(')
            recursive(s, sb, curIndex + 1, leftCount + 1, rightCount, leftRemove, rightRemove);
        else if (rightCount < leftCount)
            recursive(s, sb, curIndex + 1, leftCount, rightCount + 1, leftRemove, rightRemove);
        sb.deleteCharAt(sb.length() - 1);
    }

    private void backtrack(StringBuilder expr, int index, int leftCount, int rightCount, int leftRem, int rightRem) {
        if (index == str.length()) {
            if (leftRem == 0 && rightRem == 0)
                res.add(expr.toString());
            return;
        }
        char c = str.charAt(index);
        int length = expr.length();
        if ((c == '(' && leftRem > 0) || (c == ')' && rightRem > 0))
            backtrack(expr, index + 1, leftCount, rightCount, c == '(' ? leftRem - 1 : leftRem, c == ')' ? rightRem - 1 : rightRem);
        expr.append(c);
        if (c != '(' && c != ')')
            backtrack(expr, index + 1, leftCount, rightCount, leftRem, rightRem);
        else if (c == '(')
            backtrack(expr, index + 1, leftCount + 1, rightCount, leftRem, rightRem);
        else if (rightCount < leftCount)
            backtrack(expr, index + 1, leftCount, rightCount + 1, leftRem, rightRem);
        expr.deleteCharAt(length);
    }


    public List<String> removeInvalidParentheses(String s) {
        str = s;
        res = new HashSet<>();
        int left = 0;
        int right = 0;
        for (char c : s.toCharArray()) {
            if (c != '(' && c != ')')
                continue;
            if (c == '(')
                left++;
            else if (left > 0)
                left--;
            else
                right++;
        }
        recursive(s, new StringBuilder(), 0, 0, 0, left, right);
        //backtrack(new StringBuilder(),0,0,0,left,right);
        return new ArrayList<>(res);
    }

    /*
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(strs).mapToInt(String::length).min().getAsInt();


    }
     */
}
