package com.karthik.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathSum3 {

	private Integer recursivePathSum(TreeNode root, List<Integer> li, Integer sum) {
		if (root == null)
			return 0;
		li.add(root.val);
		List<Integer> leftQ = new ArrayList<>();
		List<Integer> rightQ = new ArrayList<>();
		Integer leftSum = recursivePathSum(root.left, leftQ, sum);
		Integer rightSum = recursivePathSum(root.right, rightQ, sum);
		Integer currentSum = 0;
		if (root.val == sum)
			currentSum++;
		List<Integer> leftBranch = leftQ.stream().map(v -> root.val + v).collect(Collectors.toList());
		List<Integer> rightBranch = rightQ.stream().map(v -> root.val + v).collect(Collectors.toList());
		currentSum += new Long(leftBranch.stream().filter(v -> v.equals(sum)).count()).intValue();
		currentSum += new Long(rightBranch.stream().filter(v -> v.equals(sum)).count()).intValue();
		li.addAll(leftBranch);
		li.addAll(rightBranch);
		// System.out.println("=====START ====");
		// System.out.println("Current Node " + root.val);
		// System.out.printf("Returning : " + li + " Sum: " + currentSum + " Total Sum :
		// %d \n",
		// (leftSum + rightSum + currentSum));
		// System.out.println("=====END ====");
		return leftSum + rightSum + currentSum;
	}

	public int pathSum(TreeNode root, int sum) {
		List<Integer> a = new ArrayList<>();
		Integer res = recursivePathSum(root, a, sum);
		System.out.println("List now : " + a);
		System.out.println("Result : " + res);
		return res;
	}

	public static void main(String[] args) {
		Integer[] input = { -240, -500, -476, 87, -783, -960, 964, 779, -317, 532, 190, -140, 763, 606, 591, -202, -863,
				null, 317, 697, -943, 811, -301, null, null, 439, -56, -516, -586, 670, -62, null, 760, null, 595, null,
				-929, 808, null, null, 862, -590, 222, 993, 240, -895, -882, 956, -301, 909, null, 742, 537, null, null,
				273, 228, null, null, -512, -357, null, null, -324, -797, -143, -784, 324, null, null, -487, null, null,
				null, null, 996, 367, 604, 96, 528, -127, -885, 932, -51, 464, null, null, 608, -801, 569, null, 562,
				-412, -385, -451, 579, 368, null, null, null, null, -657, 56, 54, -506, null, 425, null, null, 933, 76,
				-957, null, 751, 916, -988, 182, 247, 817, -631, 62, 89, -297, 937, 32, null, null, null, null, null,
				888, null, null, -350, null, 489, null, 423, null, null, 855, 191, null, 344, 209, null, -521, -235,
				-148, -207, null, null, null, null, null, null, null, 929, 592, null, 99, null, 978, 836, 381, -664,
				-119, null, -84, null, null, null, null, null, -266, null, -603, 303, -698, null, null, -94, null, null,
				null, null, null, null, null, null, null, null, -870, null, null, 856, 138, null, null, null, null,
				-456, null, null, null, null, null, null, 4, null, null, null, null, null, 884, -606, null, -139, 905,
				null, 174, null, 302, -489, null, null, -208, -347, 219, -457, null, null, null, -692, null, 565, null,
				null, 260, 908, null, null, null, null, null, 304, null, null, null, null, null, -237, 529, -217, 485,
				null, null, null, null, null, 942, null, -248, null, null, -703, -872, 818, -71, null, null, null, null,
				null, null, null, null, null, null, null, -160, null, null, null, null, null, null, -119, null, null,
				null, null, null, null, -762, null, 249, null, -983, null, null, null, null, null, null, -842, null,
				567, null, null, null, null, null, null, 493 };
		TreeNode root = TreeUtils.convertLevelOrderedInputToTree(input);
		System.out.println("Path Sum 3 : " + new PathSum3().pathSum(root, -275));

	}

}
