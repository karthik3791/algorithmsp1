package com.karthik.java;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private Integer noOfMoves = 0;
	private boolean solvable;
	private List<Board> solution;

	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private Integer noOfMoves;
		private Board prevBoard;
		private Integer priority;

		public SearchNode(Board board, Board prevBoard, Integer noOfMoves) {
			this.board = board;
			this.noOfMoves = noOfMoves;
			this.prevBoard = prevBoard;
			this.priority = board.manhattan() + noOfMoves;
		}

		@Override
		public int compareTo(SearchNode o) {
			return priority.compareTo(o.priority);
		}
	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null)
			throw new IllegalArgumentException();
		List<Board> solutionAttempt = solveTheDamnBoard(initial, initial.twin());
		// System.out.println("Solution Size : " + solutionAttempt.size());
		if (solutionAttempt.size() > 0) {
			solvable = true;
			solution = solutionAttempt;
			noOfMoves = solutionAttempt.size() - 1;
		} else {
			solvable = false;
			solution = null;
			noOfMoves = -1;
		}
	}

	private List<Board> solveTheDamnBoard(Board initialBoard, Board twinBoard) {
		List<Board> solutionSteps = new ArrayList<>();
		List<Board> twinSolutionSteps = new ArrayList<>();
		boolean solved = false;
		MinPQ<SearchNode> initialBoardPq = new MinPQ<>();
		MinPQ<SearchNode> twinBoardPq = new MinPQ<>();
		initialBoardPq.insert(new SearchNode(initialBoard, null, 0));
		twinBoardPq.insert(new SearchNode(twinBoard, null, 0));
		while (!initialBoardPq.isEmpty() && !twinBoardPq.isEmpty()) {
			SearchNode curNode = initialBoardPq.delMin();
			SearchNode curTwinNode = twinBoardPq.delMin();
			System.out.println("Current Move No. " + curNode.noOfMoves);
			System.out.println("Current Board " + curNode.board);

			System.out.println("Current Twin Move No. " + curTwinNode.noOfMoves);
			System.out.println("Current Twin Board " + curTwinNode.board);
			solutionSteps.add(curNode.board);
			twinSolutionSteps.add(curTwinNode.board);
			if (curNode.board.isGoal()) {
				solved = true;
				break;
			}
			if (curTwinNode.board.isGoal()) {
				break;
			}
			initialBoardPq = new MinPQ<>();
			twinBoardPq = new MinPQ<>();
			for (Board neighbour : curNode.board.neighbors()) {
				if (!solutionSteps.contains(neighbour)) {
					System.out.println("Adding Neighbour..");
					System.out.println(neighbour);
					initialBoardPq.insert(new SearchNode(neighbour, curNode.board, curNode.noOfMoves + 1));
				}
			}
			for (Board neighbour : curTwinNode.board.neighbors()) {
				if (!twinSolutionSteps.contains(neighbour)) {
					System.out.println("Adding Neighbour for Twin..");
					System.out.println(neighbour);
					twinBoardPq.insert(new SearchNode(neighbour, curTwinNode.board, curTwinNode.noOfMoves + 1));
				}
			}
		}
		return (solved == true) ? solutionSteps : new ArrayList<Board>();
	}

	// is the initial board solvable? (see below)
	public boolean isSolvable() {
		return solvable;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		if (!isSolvable())
			return -1;
		return noOfMoves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (!isSolvable())
			return null;
		return solution;
	}

	public static void main(String[] args) {

		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] tiles = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				tiles[i][j] = in.readInt();
		Board initial = new Board(tiles);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}