package com.karthik.java.binarysearch;

abstract class GuessGame {
    int guess(int num) {
        return 0;
    }
}

public class GuessNumberHigherOrLower extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int check = guess(mid);
            if (check == 0)
                return mid;
            else if (check < 0)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }
}
