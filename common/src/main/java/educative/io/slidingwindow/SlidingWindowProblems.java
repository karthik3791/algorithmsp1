package educative.io.slidingwindow;

import edu.princeton.cs.algs4.In;

import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
/*
public class SlidingWindowProblems {

    public static double[] averageOfSubArraySizeK(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1];
        double sum = 0;
        int windowStart, windowEnd;
        windowStart = 0;
        for (windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            sum += arr[windowEnd];
            if (windowEnd >= K - 1) {
                result[windowStart] = sum / K;
                sum -= arr[windowStart];
                windowStart++;
            }
        }
        return result;
    }

    public static int smallestSubArrayWithSum(int S, int[] arr) {
        int windowstart, windowend, windowSum, smallestWindowSize;
        windowstart = windowend = windowSum = 0;
        smallestWindowSize = Integer.MAX_VALUE;
        for (; windowstart < arr.length; windowstart++) {
            windowSum = 0;
            windowend = windowstart;
            boolean found = false;
            while (windowend < arr.length) {
                windowSum += arr[windowend];
                if (windowSum >= S) {
                    found = true;
                    break;
                }
                windowend++;
            }
            smallestWindowSize = Math.min(smallestWindowSize, (found == true ? windowend - windowstart + 1 : Integer.MAX_VALUE));
        }
        return smallestWindowSize == Integer.MAX_VALUE ? 0 : smallestWindowSize;
    }

    private static int countPathsRecursive(TreeNode currentNode, int S, List<Integer> currentPath) {
        if (currentNode == null)
            return 0;

        // add the current node to the path
        //currentPath.add(currentNode);
        int pathCount = 0, pathSum = 0;
        // find the sums of all sub-paths in the current path list
        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        while (pathIterator.hasPrevious()) {
            pathSum += pathIterator.previous();
            // if the sum of any sub-path is equal to 'S' we increment our path count.
            if (pathSum == S) {
                pathCount++;
            }
        }

        // traverse the left sub-tree
        pathCount += countPathsRecursive(currentNode.left, S, currentPath);
        // traverse the right sub-tree
        pathCount += countPathsRecursive(currentNode.right, S, currentPath);

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);

        return pathCount;
    }

    public static void main(String[] args) {
        // double[] result = SlidingWindowProblems.averageOfSubArraySizeK(5, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2});
        //System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
        System.out.println("Smallest Sub Array Sum : " + smallestSubArrayWithSum(7, new int[]{2, 1, 5, 2, 3, 2}));
        System.out.println("Smallest Sub Array Sum : " + smallestSubArrayWithSum(7, new int[]{2, 1, 5, 2, 8}));
        System.out.println("Smallest Sub Array Sum : " + smallestSubArrayWithSum(8, new int[]{3, 4, 1, 1, 6}));
    }
}
*/