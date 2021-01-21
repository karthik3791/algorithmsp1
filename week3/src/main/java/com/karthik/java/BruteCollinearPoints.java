package com.karthik.java;

import java.util.Arrays;

public class BruteCollinearPoints {

	private LineSegment[] detectedLineSegments;
	private int noOfSegments = 0;

	public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
		if (points == null) {
			throw new IllegalArgumentException("Constructor Argument is empty.");
		}
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			if (p == null) {
				throw new IllegalArgumentException("One of the points is null.");
			}
			for (int j = i + 1; j < points.length; j++) {
				if (p.equals(points[j])) {
					throw new IllegalArgumentException("Repeated Point");
				}
			}
		}
		detectedLineSegments = new LineSegment[points.length];
		Point[] inputArray = Arrays.copyOf(points, points.length);
		Arrays.sort(inputArray); // Sort By Natural Order

		for (int i = 0; i < inputArray.length; i++) {
			for (int j = i + 1; j < inputArray.length; j++) {
				for (int k = j + 1; k < inputArray.length; k++) {
					for (int l = k + 1; l < inputArray.length; l++) {
						Point p = inputArray[i];
						Point q = inputArray[j];
						Point r = inputArray[k];
						Point s = inputArray[l];
						double pqSlope = p.slopeTo(q);
						double prSlope = p.slopeTo(r);
						double psSlope = p.slopeTo(s);
						if (p.slopeTo(r) == pqSlope && p.slopeTo(s) == pqSlope) {
							detectedLineSegments[noOfSegments++] = new LineSegment(p, s);
						}
					}
				}
			}
		}

	}

	public int numberOfSegments() { // the number of line segments
		return noOfSegments;
	}

	public LineSegment[] segments() { // the line segments
		return Arrays.copyOf(detectedLineSegments, noOfSegments);
	}

	public static void main(String[] args) {// TODO Auto-generated method stub

		Point[] input = { new Point(1, 1), new Point(2, 1), new Point(3, 1), new Point(4, 1), new Point(9, 3) };
		BruteCollinearPoints bcp = new BruteCollinearPoints(input);
		System.out.println("No. of Segments : " + bcp.noOfSegments);
		System.out.println("LineSegment Array : " + Arrays.asList(bcp.detectedLineSegments));

	}

}
