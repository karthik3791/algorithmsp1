package com.karthik.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import java.awt.Color;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

	private class TreeNode<T> {
		T val;
		TreeNode<T> left;
		TreeNode<T> right;
		int noOfNodes;
		Comparator<T> comparator;

		TreeNode() {
		}

		TreeNode(T val, Comparator<T> comparator) {
			this.val = val;
			this.left = null;
			this.right = null;
			this.noOfNodes = 1;
			this.comparator = comparator;
		}

		TreeNode(T val, TreeNode<T> left, TreeNode<T> right, Comparator<T> comparator) {
			this.val = val;
			this.left = left;
			this.right = right;
			this.noOfNodes = 1;
			this.comparator = comparator;
		}

	}

	private TreeNode<Point2D> bst;
	private Point2D nearestPoint;
	private double minDistance;

	// construct an empty set of points
	public KdTree() {
	}

	// is the set empty?
	public boolean isEmpty() {
		return bst == null;
	}

	// number of points in the set
	public int size() {
		return (isEmpty() ? 0 : bst.noOfNodes);
	}

	private int count(TreeNode<Point2D> root) {
		if (root == null)
			return 0;
		else
			return root.noOfNodes;
	}

	private TreeNode<Point2D> insert(TreeNode<Point2D> root, Point2D p, Comparator<Point2D> comparator) {
		if (root == null) {
			String comparatorclass = (comparator.equals(Point2D.X_ORDER) ? "XOrder" : "YOrder");
			System.out.println("Inserting Point : " + p + " With comparator " + comparatorclass);
			root = new TreeNode<Point2D>(p, null, null, comparator);
		} else {
			System.out.println("Current Node : " + root.val);
			int compareResult = root.comparator.compare(p, root.val);
			Comparator<Point2D> newcomparator = root.comparator.equals(Point2D.X_ORDER) ? Point2D.Y_ORDER
					: Point2D.X_ORDER;
			if (compareResult == -1) {
				System.out.println("Going Left for Insert.");
				root.left = insert(root.left, p, newcomparator);
			} else if (!root.val.equals(p)) {
				System.out.println("Going Right for Insert.");
				root.right = insert(root.right, p, newcomparator);
			}

		}
		root.noOfNodes = 1 + count(root.left) + count(root.right);
		return root;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException("Argument cannot be null");
		bst = insert(bst, p, Point2D.X_ORDER);
	}

	private boolean contains(TreeNode<Point2D> root, Point2D p) {
		boolean containsresult = false;
		if (root != null) {
			int compareResult = root.comparator.compare(p, root.val);
			if (compareResult == -1)
				containsresult = contains(root.left, p);
			else if (!root.val.equals(p))
				containsresult = contains(root.right, p);
			else
				containsresult = true;
		}
		return containsresult;
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException("Argument cannot be null");
		return contains(bst, p);
	}

	private void draw(TreeNode<Point2D> node, double minX, double maxX, double minY, double maxY) {
		if (node != null) {
			System.out.printf("Currently Drawing point" + node.val + " with minX = %f, maxX=%f, minY =%f, maxY=%f",
					minX, maxX, minY, maxY);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.010);
			Point2D nodePoint = node.val;
			nodePoint.draw();
			StdDraw.setPenRadius(0.002);
			Color pencolor = node.comparator.equals(Point2D.X_ORDER) ? StdDraw.RED : StdDraw.BLUE;
			StdDraw.setPenColor(pencolor);

			double startX, startY, endX, endY;
			if (node.comparator.equals(Point2D.X_ORDER)) {
				startX = nodePoint.x();
				endX = nodePoint.x();
				startY = minY;
				endY = maxY;

			} else {
				startX = minX;
				endX = maxX;
				startY = nodePoint.y();
				endY = nodePoint.y();

			}
			System.out.println("Drawing Line of Color : " + pencolor + " from (" + startX + "," + startY + ") to ("
					+ endX + "," + endY + ")");
			StdDraw.line(startX, startY, endX, endY);

			if (node.comparator.equals(Point2D.X_ORDER)) {
				draw(node.left, minX, nodePoint.x(), minY, maxY);
				draw(node.right, nodePoint.x(), maxX, minY, maxY);
			} else {
				draw(node.left, minX, maxX, minY, nodePoint.y());
				draw(node.right, minX, maxX, nodePoint.y(), maxY);
			}
		}
	}

	// draw all points to standard draw
	public void draw() {
		draw(bst, 0.0, 1.0, 0.0, 1.0);
	}

	private void range(TreeNode<Point2D> root, RectHV rect, List<Point2D> solution) {
		if (root != null) {
			System.out.println("Checking if Rectangle " + rect + " contains " + root.val);
			if (rect.contains(root.val)) {
				System.out.println("Yes it contains.");
				solution.add(root.val);
			}
			if (root.comparator.compare(new Point2D(rect.xmin(), rect.ymin()), root.val) == -1) {
				System.out.println("Going Left");
				range(root.left, rect, solution);
			}
			// Reason we check > -1 is because when our comparison with xmax,ymax produces a
			// 0, we need to check right subtree as well in addition to left subtree.
			if (root.comparator.compare(new Point2D(rect.xmax(), rect.ymax()), root.val) > -1) {
				System.out.println("Going Right");
				range(root.right, rect, solution);
			}
		}
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null)
			throw new IllegalArgumentException("Argument cannot be null");
		List<Point2D> pointsInside = new ArrayList<>();
		range(bst, rect, pointsInside);
		return pointsInside;
	}

	private void calculateNearest2(TreeNode<Point2D> root, Point2D querypoint, double minX, double maxX, double minY,
			double maxY) {
		if (root == null)
			return;
		Point2D currentpoint = root.val;
		double currentDistance = currentpoint.distanceSquaredTo(querypoint);
		System.out.println("Currently checking point : " + currentpoint);

		if (currentDistance < this.minDistance) {
			System.out.println("Found new nearest point : " + currentpoint);
			this.minDistance = currentDistance;
			this.nearestPoint = currentpoint;
		}
		double evalX;
		double evalY;
		if (root.comparator.equals(Point2D.X_ORDER)) {
			evalX = currentpoint.x();
			if (querypoint.y() >= minY && querypoint.y() <= maxY)
				evalY = querypoint.y();
			else if (querypoint.y() < minY)
				evalY = minY;
			else
				evalY = maxY;
		} else {
			evalY = currentpoint.y();
			if (querypoint.x() >= minX && querypoint.x() <= maxX)
				evalX = querypoint.x();
			else if (querypoint.x() < minX)
				evalX = minX;
			else
				evalX = maxX;
		}
		System.out.println("Evaluation point : " + new Point2D(evalX, evalY));
		if (root.comparator.compare(querypoint, root.val) == -1) {
			System.out.println("Query point is to the left subtree. Going left");
			if (root.comparator.equals(Point2D.X_ORDER)) {
				calculateNearest2(root.left, querypoint, minX, currentpoint.x(), minY, maxY);
				if (new Point2D(evalX, evalY).distanceSquaredTo(querypoint) < minDistance) {
					System.out.println("Also checking right.");
					calculateNearest2(root.right, querypoint, currentpoint.x(), maxX, minY, maxY);
				}
			} else {
				calculateNearest2(root.left, querypoint, minX, maxX, minY, currentpoint.y());
				if (new Point2D(evalX, evalY).distanceSquaredTo(querypoint) < minDistance) {
					System.out.println("Also checking right.");
					calculateNearest2(root.right, querypoint, minX, maxX, currentpoint.y(), maxY);
				}
			}
		} else if (!root.val.equals(querypoint)) {
			System.out.println("Query point is to the right subtree. Going right");
			if (root.comparator.equals(Point2D.X_ORDER)) {
				calculateNearest2(root.right, querypoint, currentpoint.x(), maxX, minY, maxY);
				if (new Point2D(evalX, evalY).distanceSquaredTo(querypoint) < minDistance) {
					System.out.println("Also checking right.");
					calculateNearest2(root.left, querypoint, minX, currentpoint.x(), minY, maxY);
				}
			} else {
				calculateNearest2(root.right, querypoint, minX, maxX, currentpoint.y(), maxY);
				if (new Point2D(evalX, evalY).distanceSquaredTo(querypoint) < minDistance) {
					System.out.println("Also checking right.");
					calculateNearest2(root.left, querypoint, minX, maxX, minY, currentpoint.y());
				}
			}
		} else {
			System.out.println("Query point is in the BST.");
			this.minDistance = 0.0;
			this.nearestPoint = currentpoint;
		}

	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException("Argument cannot be null");
		this.nearestPoint = null;
		this.minDistance = Double.MAX_VALUE;
		calculateNearest2(bst, p, 0.0, 1.0, 0.0, 1.0);
		return this.nearestPoint;
	}

	private static void testFailure() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0.625, 0.5));
		kdTree.draw();
		kdTree.insert(new Point2D(0.75, 0.375));
		kdTree.draw();
		kdTree.insert(new Point2D(0.875, 0.25));
		kdTree.draw();
		kdTree.insert(new Point2D(0.375, 0.625));
		kdTree.draw();
		kdTree.insert(new Point2D(0.625, 0.125));
		kdTree.draw();
		kdTree.insert(new Point2D(0.5, 0.0));
		kdTree.draw();
		kdTree.insert(new Point2D(1.0, 1.0));
		kdTree.draw();
		kdTree.insert(new Point2D(0.5, 1.0));
		kdTree.draw();
		kdTree.insert(new Point2D(0.75, 0.5));
		kdTree.draw();
		kdTree.insert(new Point2D(0.5, 0.625));
		kdTree.draw();
		kdTree.insert(new Point2D(0.125, 0.375));
		kdTree.draw();
		kdTree.insert(new Point2D(0.125, 0.125));
		kdTree.draw();
		kdTree.insert(new Point2D(0.875, 0.875));
		kdTree.draw();
		kdTree.insert(new Point2D(0.25, 0.125));
		kdTree.draw();
		kdTree.insert(new Point2D(0.5, 0.375));
		kdTree.draw();
		kdTree.insert(new Point2D(0.0, 0.25));
		kdTree.draw();
		kdTree.insert(new Point2D(0.25, 0.5));
		kdTree.draw();
		kdTree.insert(new Point2D(0.875, 0.5));
		kdTree.draw();
		kdTree.insert(new Point2D(1.0, 0.625));
		kdTree.draw();
		kdTree.insert(new Point2D(0.875, 0.75));
		kdTree.draw();
		RectHV rect = new RectHV(0.625, 0.375, 0.875, 1.0);
		StdDraw.setPenColor(Color.GREEN);
		rect.draw();
		System.out.println("Points in Range of Rectangle : " + kdTree.range(rect));

	}

	private static void testFailure2() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0.7, 0.2));
		kdTree.draw();
		kdTree.insert(new Point2D(0.5, 0.4));
		kdTree.draw();
		kdTree.insert(new Point2D(0.2, 0.3));
		kdTree.draw();
		kdTree.insert(new Point2D(0.4, 0.7));
		kdTree.draw();
		kdTree.insert(new Point2D(0.9, 0.6));
		kdTree.draw();
		RectHV rect = new RectHV(0.04, 0.66, 0.21, 0.92);
		StdDraw.setPenColor(Color.GREEN);
		rect.draw();
		System.out.println("Points in Range of Rectangle : " + kdTree.range(rect));
		Point2D querypoint = new Point2D(0.042, 0.412);
		StdDraw.setPenRadius(0.006);
		querypoint.draw();
		System.out.println("Nearest Point : " + kdTree.nearest(querypoint));

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testFailure2();

		/*
		 * KdTree kdTree = new KdTree(); kdTree.insert(new Point2D(0.7, 0.2));
		 * kdTree.insert(new Point2D(0.5, 0.4)); kdTree.insert(new Point2D(0.2, 0.3));
		 * kdTree.insert(new Point2D(0.4, 0.7)); kdTree.insert(new Point2D(0.7, 0.6));
		 * assert (kdTree.size() == 5); assert (kdTree.isEmpty() == false);
		 * 
		 * kdTree.draw(); RectHV rect = new RectHV(0.4, 0.3, 0.8, 0.6);
		 * StdDraw.setPenColor(Color.GREEN); rect.draw();
		 * System.out.println("Points in Range of Rectangle : " + kdTree.range(rect));
		 * Point2D querypoint = new Point2D(0.8, 0.4); StdDraw.setPenRadius(0.008);
		 * querypoint.draw(); System.out.println("Nearest Neighbour : " +
		 * kdTree.nearest(querypoint));
		 */

	}
}
