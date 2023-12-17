package com.karthik.java;

public class BulbToggle {

    private void toggle(StringBuilder sb, int index) {
        sb.setCharAt(index, (sb.charAt(index) == 'X' ? 'O' : 'X'));
    }

    public String toggleBulbs(String s, int k) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        for (int i = 0; i < k; i++) {
            toggle(sb, 0);
            int prev = 0;
            for (int j = 1; j < s.length() && sb.charAt(prev) == 'X'; j++) {
                toggle(sb, j);
                prev = j;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new BulbToggle().toggleBulbs("OOXOX", 2));
    }

}
