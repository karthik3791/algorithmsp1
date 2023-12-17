package com.karthik.java;

class BackspaceCompare {

    private static int getNextIndex(String str, int startIndex) {
        int count = 0;
        while (startIndex >= 0) {
            if (str.charAt(startIndex) == '#')
                count++;
            else if (count > 0)
                count--;
            else
                break;
            startIndex--;
        }
        return startIndex;
    }

    public static boolean backspaceCompare(String s, String t) {
        int left = s.length() - 1;
        int right = t.length() - 1;
        while (left >= 0 || right >= 0) {
            int leftIndex = getNextIndex(s, left);
            int rightIndex = getNextIndex(t, right);
            if (leftIndex < 0 && rightIndex < 0)
                return true;
            if (leftIndex < 0 || rightIndex < 0)
                return false;
            Character c1 = s.charAt(leftIndex);
            Character c2 = t.charAt(rightIndex);
            if (c1 != c2)
                return false;

            left = leftIndex - 1;
            right = rightIndex - 1;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(BackspaceCompare.backspaceCompare("ab##", "c#d#"));
    }
}
