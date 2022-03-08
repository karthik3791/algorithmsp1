package com.karthik.java;

import java.util.*;

class Permutations {

    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());
        for (int currentNumber : nums) {
            // we will take all existing permutations and add the current number to create new permutations
            int n = permutations.size();
            for (int i = 0; i < n; i++) {
                List<Integer> oldPermutation = permutations.poll();
                // create a new permutation by adding the current number at every position
                for (int j = 0; j <= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);
                    newPermutation.add(j, currentNumber);
                    if (newPermutation.size() == nums.length)
                        result.add(newPermutation);
                    else
                        permutations.add(newPermutation);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //List<List<Integer>> result = Permutations.findPermutations(new int[]{1, 3, 5});
        //System.out.print("Here are all the permutations: " + result);

        PermutationsBackTrack pbt = new PermutationsBackTrack();
        pbt.permute(new int[]{1, 2, 3});
    }

}

class PermutationsBackTrack {

    private List<List<Integer>> res;

    private void backTrack(Set<Integer> curPath, int[] nums, int curIndex) {
        if (curIndex == nums.length) {
            res.add(new ArrayList<>(curPath));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!curPath.contains(nums[i])) {
                curPath.add(nums[i]);
                backTrack(curPath, nums, curIndex + 1);
                curPath.remove(nums[i]);
            }
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        this.res = new ArrayList<>();
        backTrack(new HashSet<>(), nums, 0);
        return this.res;
    }
}