package com.karthik.java;

import java.util.Arrays;

public class FastCollinearPoints {

	private LineSegment[] detectedLineSegments;
	private int noOfSegments = 0;

	public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
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
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			Arrays.sort(inputArray, p.slopeOrder());
			int noOfPointsInLineSegment;
			int j = 1;
			while (j < inputArray.length) {
				noOfPointsInLineSegment = 2;
				double currentSlope = p.slopeTo(inputArray[j]);
				Point[] pointsInaLineSegment = new Point[inputArray.length];
				pointsInaLineSegment[0] = p;
				pointsInaLineSegment[1] = inputArray[j];
				for (int k = j + 1; k < inputArray.length && p.slopeTo(inputArray[k]) == currentSlope; k++) {
					pointsInaLineSegment[noOfPointsInLineSegment++] = inputArray[k];
				}
				if (noOfPointsInLineSegment >= 4) {
					Arrays.sort(pointsInaLineSegment, 0, noOfPointsInLineSegment); // Sort By Natural Order
					// System.out.println("Line Segment Detected With Points " +
					// Arrays.asList(pointsInaLineSegment)
					// + "No. of Points With same Slope : " + noOfPointsInLineSegment);
					LineSegment l = new LineSegment(pointsInaLineSegment[0],pointsInaLineSegment[noOfPointsInLineSegment - 1]);
					if (findLineSegmentEntry(l) == false)
						detectedLineSegments[noOfSegments++] = l;					
				} 
				j += noOfPointsInLineSegment - 1;
			}
		}
	}

	private boolean findLineSegmentEntry(LineSegment l) {
		boolean found = false;
		for (int i = 0; i < noOfSegments; i++) {
			if (detectedLineSegments[i].toString().equals(l.toString())) {
				found = true;
				break;
			}
		}
		return found;
	}

	public int numberOfSegments() { // the number of line segments
		return noOfSegments;
	}

	public LineSegment[] segments() { // the line segments
		return Arrays.copyOf(detectedLineSegments, noOfSegments);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point[] input = { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5),
				new Point(1, 5), new Point(2, 4), new Point(4, 2), new Point(5, 1), new Point(3, 5), new Point(3, 4),
				new Point(3, 2), new Point(3, 1), new Point(2, 5) };
		FastCollinearPoints fcp = new FastCollinearPoints(input);
		System.out.println("No. of Segments : " + fcp.noOfSegments);
		System.out.println("LineSegment Array : " + Arrays.asList(fcp.detectedLineSegments));

	}

}
