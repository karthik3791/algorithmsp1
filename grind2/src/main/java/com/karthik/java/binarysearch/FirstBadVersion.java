package com.karthik.java.binarysearch;

interface VersionControl {
    boolean isBadVersion(int version);
}

public class FirstBadVersion implements VersionControl {
        /*
    This is using Template 1 and then finally using left
    It appears that the 2 solutions are equivalent
     */

    public int firstBadVersion2(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid))
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }


    /*
    This is using Template 2 where left < right and right = mid.
    By avoiding left<=right, it means we avoid the final iteration where left right and mid are the same
    Think of a 3 sized array where left mid right
    If the mid is not bad version, then we set left = mid+1 (same as right).
    We will now exit the loop
    Suppose mid is the bad version ,right = mid. We will now have a 2 sized array - left right
    Now mid will be same as left and suppose mid is the badversion, right will turn to mid else left = mid+1 where it will become right.
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }

    @Override
    public boolean isBadVersion(int version) {
        return false;
    }
}
