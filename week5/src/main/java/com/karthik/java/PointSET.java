package com.karthik.java;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {

	private TreeSet<Point2D> bst;

	// construct an empty set of points
	public PointSET() {
		bst = new TreeSet<>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return bst.isEmpty();
	}

	// number of points in the set
	public int size() {
		return bst.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException("Argument cannot be null");
		bst.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException("Argument cannot be null");
		return bst.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		
		for (Point2D point : bst) {
			point.draw();
		}
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null)
			throw new IllegalArgumentException("Argument cannot be null");
		List<Point2D> pointsInside = new ArrayList<>();
		for (Point2D point : bst) {
			if (rect.contains(point))
				pointsInside.add(point);
		}
		return pointsInside;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		Point2D nearestPoint = null;
		Double minDistance = Double.MAX_VALUE;
		if (p == null)
			throw new IllegalArgumentException("Argument cannot be null");
		for (Point2D point : bst) {
			if (point.distanceSquaredTo(p) < minDistance) {
				minDistance = point.distanceSquaredTo(p);
				nearestPoint = point;
			}
		}
		return nearestPoint;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PointSET ps = new PointSET();
		ps.insert(new Point2D(0, 0));
		ps.insert(new Point2D(0.1, 0.4));
		ps.insert(new Point2D(0.6, 0.5));
		assert (ps.size() == 3);
		assert (ps.isEmpty() == false);

		RectHV rect = new RectHV(0.4, 0.3, 0.8, 0.6);
		StdDraw.setPenRadius(0.010);
		ps.draw();
		StdDraw.setPenRadius(0.002);
		rect.draw();
		System.out.println("Points in Range of Rectangle : " + ps.range(rect));
		System.out.println("Nearest Neighbour : " + ps.nearest(new Point2D(0.1, 0.2)));
	}

}
