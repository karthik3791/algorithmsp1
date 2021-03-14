package com.karthik.java;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private int n;
	private int[][] board;
	private int[][] goalBoard;

	// create a board from an n-by-n array of tiles,
	// where tiles[row][col] = tile at (row, col)
	public Board(int[][] tiles) {
		assert tiles.length > 0;
		assert tiles[0].length == tiles.length;
		n = tiles.length;
		board = new int[n][n];
		goalBoard = new int[n][n];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				board[i][j] = tiles[i][j];
				if (i == tiles.length - 1 && j == tiles.length - 1)
					goalBoard[i][j] = 0;
				else
					goalBoard[i][j] = (i * n) + (j + 1);
			}
		}
	}

	// string representation of this board
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(n).append("\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private String goalBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append(n).append("\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(goalBoard[i][j]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// board dimension n
	public int dimension() {
		return n;
	}

	// number of tiles out of place
	public int hamming() {
		int hammingCount = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] != goalBoard[i][j] && board[i][j] != 0)
					hammingCount++;
			}
		}
		return hammingCount;
	}

	// sum of Manhattan distances between tiles and goal
	public int manhattan() {
		int manhattanCount = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] != goalBoard[i][j] && board[i][j] != 0) {
					int expectedI = (board[i][j] % n == 0) ? (board[i][j] / n) - 1 : (board[i][j] / n);
					int expectedJ = (board[i][j] % n == 0) ? n - 1 : (board[i][j] % n) - 1;
					manhattanCount += Math.abs(expectedI - i) + Math.abs(expectedJ - j);
				}
			}
		}
		return manhattanCount;
	}

	// is this board the goal board?
	public boolean isGoal() {
		boolean goal = true;
		for (int i = 0; i < n && goal; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] != goalBoard[i][j]) {
					goal = false;
					break;
				}
			}
		}
		return goal;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		boolean res = y != null && (y.getClass() == this.getClass());
		if (res) {
			Board otherBoard = (Board) y;
			res = res && this.dimension() == otherBoard.dimension();
			for (int i = 0; i < n && res; i++) {
				for (int j = 0; j < n; j++) {
					if (board[i][j] != otherBoard.board[i][j]) {
						res = false;
						break;
					}
				}
			}
		}
		return res;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> neighbourList = new ArrayList<>();
		boolean searching = true;
		int zeroi = 0, zeroj = 0;
		for (int i = 0; i < n && searching; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					zeroi = i;
					zeroj = j;
					searching = false;
					break;
				}
			}
		}
		for (int i = zeroi - 1; i <= zeroi + 1; i += 2) {
			if (i >= 0 && i <= n - 1) {
				Board neighbourBoard = new Board(board);
				int t = neighbourBoard.board[i][zeroj];
				neighbourBoard.board[i][zeroj] = 0;
				neighbourBoard.board[zeroi][zeroj] = t;
				neighbourList.add(neighbourBoard);
			}
		}
		for (int j = zeroj - 1; j <= zeroj + 1; j += 2) {
			if (j >= 0 && j <= n - 1) {
				Board neighbourBoard = new Board(board);
				int t = neighbourBoard.board[zeroi][j];
				neighbourBoard.board[zeroi][j] = 0;
				neighbourBoard.board[zeroi][zeroj] = t;
				neighbourList.add(neighbourBoard);
			}
		}
		return neighbourList;
	}

	// a board that is obtained by exchanging any pair of tiles
	public Board twin() {
		Board twinboard = new Board(board);
		int j1 = 0;
		int i1 = 0;
		while (j1 < n && (twinboard.board[i1][j1] == 0 || twinboard.board[i1 + 1][j1] == 0)) {
			j1++;
		}
		int t = twinboard.board[i1][j1];
		twinboard.board[i1][j1] = twinboard.board[i1 + 1][j1];
		twinboard.board[i1 + 1][j1] = t;
		return twinboard;
	}

	// unit testing (not graded)
	private static void printStuff(Board b) {
		System.out.println("Board : \n" + b);
		System.out.println("Goal Board : \n" + b.goalBoard());
		System.out.println("Twin Board : \n" + b.twin());
		System.out.println("Board Hamming : " + b.hamming());
		System.out.println("Board Manhattan : " + b.manhattan());
		for (Board neighbourBoard : b.neighbors()) {
			System.out.println("Neighbour Board : \n" + neighbourBoard);
		}
		System.out.println("Is Goal : " + b.isGoal());
		System.out.println("Board After All Operations : \n" + b);
		System.out.println("================");
	}

	public static void main(String[] args) {
		int[][] board1 = { { 1, 0, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
		int[][] board2 = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
		int[][] board3 = { { 0, 3 }, { 2, 1 } };
		Board b1 = new Board(board1);
		Board b2 = new Board(board2);
		Board b3 = new Board(board3);
		printStuff(b1);
		printStuff(b2);
		System.out.println("Equality Check : " + b1.equals(b1));
		// System.out.println("B3 Twin : " + b3.twin());
	}

}
