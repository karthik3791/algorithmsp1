package com.karthik.java.binarysearch;

public class Sqrt {
    public int mySqrt(int x) {
        if (x < 2) return x;
        int left = 2;
        int right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //Whenever we do any huge product with integers, must use long to avoid Integer Overflow
            long sq = (long) mid * mid;
            if (sq == x)
                return mid;
            else if (sq > x)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return right;
    }
}
