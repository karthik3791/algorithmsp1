package com.karthik.java;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

public class SeamCarver {
	private Picture p;
	private int W;
	private int H;
	private double[][] energyGrid;

	private void validateCoordinate(int x, int y) {
		if (x < 0 || x > W - 1 || y < 0 || y > H - 1)
			throw new IllegalArgumentException("Parameter is not in prescribed range.");
	}

	private double computeEnergy(int x, int y) {
		validateCoordinate(x, y);
		if (x == 0 || x == W - 1 || y == 0 || y == H - 1)
			return 1000;
		else {
			Color right = p.get(x + 1, y);
			Color left = p.get(x - 1, y);
			Color up = p.get(x, y - 1);
			Color down = p.get(x, y + 1);
			double RxSquare = Math.pow(right.getRed() - left.getRed(), 2);
			double GxSquare = Math.pow(right.getGreen() - left.getGreen(), 2);
			double BxSquare = Math.pow(right.getBlue() - left.getBlue(), 2);
			double RySquare = Math.pow(down.getRed() - up.getRed(), 2);
			double GySquare = Math.pow(down.getGreen() - up.getGreen(), 2);
			double BySquare = Math.pow(down.getBlue() - up.getBlue(), 2);
			return Math.sqrt(RxSquare + GxSquare + BxSquare + RySquare + GySquare + BySquare);
		}
	}

	private void computeEnergyGrid() {
		energyGrid = new double[W][H];
		for (int i = 0; i < W; i++)
			for (int j = 0; j < H; j++)
				energyGrid[i][j] = computeEnergy(i, j);
	}

	private void initialize(Picture picture) {
		this.p = new Picture(picture);
		this.W = picture.width();
		this.H = picture.height();
		computeEnergyGrid();
	}

	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		if (picture == null)
			throw new IllegalArgumentException("Null parameter");
		initialize(picture);
	}

	// current picture
	public Picture picture() {
		return new Picture(p);
	}

	// width of current picture
	public int width() {
		return W;
	}

	// height of current picture
	public int height() {
		return H;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		validateCoordinate(x, y);
		return energyGrid[x][y];
	}

	private static class Coordinate {
		private int x;
		private int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "X : " + x + " Y : " + y;
		}
	}

	private List<Coordinate> energyAdj(Coordinate c) {
		Coordinate top = new Coordinate(c.x, c.y - 1);
		Coordinate bottom = new Coordinate(c.x, c.y + 1);
		Coordinate left = new Coordinate(c.x - 1, c.y);
		Coordinate right = new Coordinate(c.x + 1, c.y);
		List<Coordinate> adj = Arrays.asList(left, top, c, bottom, right);
		return adj.stream()
				.filter(coordinate -> coordinate.x >= 0 && coordinate.x < W && coordinate.y >= 0 && coordinate.y < H)
				.collect(Collectors.toList());
	}

	private List<Coordinate> verticalAdj(Coordinate c) {
		List<Coordinate> adj = new ArrayList<>();
		for (int x = c.x - 1; x <= c.x + 1; x++) {
			int y = c.y + 1;
			if (x >= 0 && x < W && y >= 0 && y < H) {
				adj.add(new Coordinate(x, y));
			}
		}
		return adj;
	}

	private List<Coordinate> horizontalAdj(Coordinate c) {
		List<Coordinate> adj = new ArrayList<>();
		for (int y = c.y - 1; y <= c.y + 1; y++) {
			int x = c.x + 1;
			if (x >= 0 && x < W && y >= 0 && y < H) {
				adj.add(new Coordinate(x, y));
			}
		}
		return adj;
	}

	private int getArrayIndex(Coordinate c) {
		return c.y * W + c.x;
	}

	private class DepthFirstOrder {
		private boolean[] marked;
		private Stack<Coordinate> reversePost;
		private Coordinate s;

		public DepthFirstOrder(Coordinate s, Function<Coordinate, List<Coordinate>> adj) {
			this.s = s;
			marked = new boolean[W * H];
			reversePost = new Stack<Coordinate>();
			dfs(s, adj);
		}

		private void dfs(Coordinate s, Function<Coordinate, List<Coordinate>> adj) {
			marked[getArrayIndex(s)] = true;
			for (Coordinate w : adj.apply(s)) {
				if (!marked[getArrayIndex(w)])
					dfs(w, adj);
			}
			reversePost.push(s);
		}

		public Iterable<Coordinate> reversePostOrder() {
			return reversePost;
		}
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		int[] finalVerticalSeam = new int[H];
		double finalMinEnergy = Double.POSITIVE_INFINITY;
		for (int x = 0; x < W; x++) {
			Coordinate startCoordinate = new Coordinate(x, 0);
			int[] verticalSeam = new int[H];
			double[] distTo = new double[this.W * this.H];
			int[] edgeTo = new int[this.W * this.H];
			for (int i = 0; i < W; i++) {
				for (int j = 0; j < H; j++) {
					distTo[getArrayIndex(new Coordinate(i, j))] = Double.POSITIVE_INFINITY;
				}
			}
			distTo[getArrayIndex(startCoordinate)] = 0;
			for (Coordinate v : new DepthFirstOrder(startCoordinate, this::verticalAdj).reversePostOrder()) {
				for (Coordinate w : this.verticalAdj(v)) {
					if (distTo[getArrayIndex(w)] > distTo[getArrayIndex(v)] + energy(w.x, w.y)) {
						distTo[getArrayIndex(w)] = distTo[getArrayIndex(v)] + energy(w.x, w.y);
						edgeTo[getArrayIndex(w)] = getArrayIndex(v);
					}
				}
			}
			Coordinate endCoordinate = null;
			double minEnergy = Double.POSITIVE_INFINITY;
			for (int k = 0; k < W; k++) {
				Coordinate c = new Coordinate(k, H - 1);
				double energyDistance = distTo[getArrayIndex(c)];
				if (energyDistance < Double.POSITIVE_INFINITY) {
					if (energyDistance < minEnergy) {
						minEnergy = energyDistance;
						endCoordinate = c;
					}
				}
			}
			if (minEnergy < finalMinEnergy) {
				int i = H - 1;
				for (int k = getArrayIndex(endCoordinate); i >= 0; k = edgeTo[k]) {
					verticalSeam[i--] = k % this.W;
				}
				finalMinEnergy = minEnergy;
				finalVerticalSeam = verticalSeam;
			}
		}
		return finalVerticalSeam;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		int[] finalHorizonalSeam = new int[H];
		double finalMinEnergy = Double.POSITIVE_INFINITY;
		for (int y = 0; y < H; y++) {
			Coordinate startCoordinate = new Coordinate(0, y);
			int[] horizontalSeam = new int[W];

			double[] distTo = new double[this.W * this.H];
			int[] edgeTo = new int[this.W * this.H];
			for (int i = 0; i < W; i++) {
				for (int j = 0; j < H; j++) {
					distTo[getArrayIndex(new Coordinate(i, j))] = Double.POSITIVE_INFINITY;
				}
			}
			distTo[getArrayIndex(startCoordinate)] = 0;
			for (Coordinate v : new DepthFirstOrder(startCoordinate, this::horizontalAdj).reversePostOrder()) {
				for (Coordinate w : this.horizontalAdj(v)) {
					if (distTo[getArrayIndex(w)] > distTo[getArrayIndex(v)] + energy(w.x, w.y)) {
						distTo[getArrayIndex(w)] = distTo[getArrayIndex(v)] + energy(w.x, w.y);
						edgeTo[getArrayIndex(w)] = getArrayIndex(v);
					}
				}
			}
			Coordinate endCoordinate = null;
			double minEnergy = Double.POSITIVE_INFINITY;
			for (int k = 0; k < H; k++) {
				Coordinate c = new Coordinate(W - 1, k);
				double energyDistance = distTo[getArrayIndex(c)];
				if (energyDistance < Double.POSITIVE_INFINITY) {
					if (energyDistance < minEnergy) {
						minEnergy = energyDistance;
						endCoordinate = c;
					}
				}
			}

			if (minEnergy < finalMinEnergy) {
				int i = W - 1;
				for (int k = getArrayIndex(endCoordinate); i >= 0; k = edgeTo[k]) {
					horizontalSeam[i--] = k / this.W;
				}
				finalMinEnergy = minEnergy;
				finalHorizonalSeam = horizontalSeam;
			}
		}

		return finalHorizonalSeam;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
		if (seam == null || seam.length != W || H <= 1)
			throw new IllegalArgumentException("Invalid Seam Argument.");
		for (int i = 0; i < seam.length; i++)
			this.validateCoordinate(i, seam[i]);
		for (int i = 0; i < seam.length - 1; i++)
			if (Math.abs(seam[i + 1] - seam[i]) > 1)
				throw new IllegalArgumentException("Invalid Seam Argument");

		double[][] newEnergyGrid = new double[W][H - 1];
		Picture newPicture = new Picture(W, H - 1);
		for (int x = 0; x < seam.length; x++) {
			int y = seam[x];
			System.arraycopy(energyGrid[x], 0, newEnergyGrid[x], 0, y);
			if (y < H - 1)
				System.arraycopy(energyGrid[x], y + 1, newEnergyGrid[x], y, H - y - 2);
			for (int ny = 0; ny <= y - 1; ny++) {
				newPicture.setRGB(x, ny, this.p.getRGB(x, ny));
			}
			for (int ny = y + 1; ny <= H - 1; ny++) {
				newPicture.setRGB(x, ny - 1, this.p.getRGB(x, ny));
			}
		}
		energyGrid = newEnergyGrid;
		p = newPicture;
		H--;
		for (int x = 0; x < seam.length; x++) {
			int y = seam[x];
			for (Coordinate c : this.energyAdj(new Coordinate(x, y))) {
				energyGrid[c.x][c.y] = this.computeEnergy(c.x, c.y);
			}
		}

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
		if (seam == null || seam.length != H || W <= 1)
			throw new IllegalArgumentException("Invalid Seam Argument.");
		for (int i = 0; i < seam.length; i++)
			this.validateCoordinate(seam[i], i);
		for (int i = 0; i < seam.length - 1; i++)
			if (Math.abs(seam[i + 1] - seam[i]) > 1)
				throw new IllegalArgumentException("Invalid Seam Argument");
		double[][] newEnergyGrid = new double[W - 1][H];
		Picture newPicture = new Picture(W - 1, H);
		for (int y = 0; y < seam.length; y++) {
			int x = seam[y];
			for (int i = 0; i < x; i++) {
				newEnergyGrid[i][y] = energyGrid[i][y];
			}
			if (x < W - 1) {
				for (int i = x + 1; i < W; i++) {
					newEnergyGrid[i - 1][y] = energyGrid[i][y];
				}
			}
			for (int nx = 0; nx <= x - 1; nx++) {
				newPicture.setRGB(nx, y, this.p.getRGB(nx, y));
			}
			for (int nx = x + 1; nx <= W - 1; nx++) {
				newPicture.setRGB(nx - 1, y, this.p.getRGB(nx, y));
			}
		}
		energyGrid = newEnergyGrid;
		p = newPicture;
		W--;
		for (int y = 0; y < seam.length; y++) {
			int x = seam[y];
			for (Coordinate c : this.energyAdj(new Coordinate(x, y))) {
				energyGrid[c.x][c.y] = this.computeEnergy(c.x, c.y);
			}
		}

	}

	// unit testing (optional)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
