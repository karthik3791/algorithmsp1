package com.karthik.java;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private boolean solvable;
	private SearchNode lastNode;

	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private Integer noOfMoves;
		private SearchNode prevSearchNode;
		private Integer priority;

		public SearchNode(Board board, SearchNode prevSearchNode, Integer noOfMoves) {
			this.board = board;
			this.noOfMoves = noOfMoves;
			this.prevSearchNode = prevSearchNode;
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
		solveTheDamnBoard(initial, initial.twin());
	}

	private void solveTheDamnBoard(Board initialBoard, Board twinBoard) {
		MinPQ<SearchNode> initialBoardPq = new MinPQ<>();
		initialBoardPq.insert(new SearchNode(initialBoard, null, 0));
		MinPQ<SearchNode> twinBoardPq = new MinPQ<>();
		twinBoardPq.insert(new SearchNode(twinBoard, null, 0));
		while (!initialBoardPq.isEmpty() && !twinBoardPq.isEmpty()) {
			SearchNode curNode = initialBoardPq.delMin();
			SearchNode curTwinNode = twinBoardPq.delMin();
			System.out.println("Current Node (No. of Moves) : " + curNode.noOfMoves);
			System.out.println(curNode.board);
			System.out.println("Current Twin Node (No. of Moves) : " + curTwinNode.noOfMoves);
			System.out.println(curTwinNode.board);
			if (curNode.board.isGoal()) {
				solvable = true;
				lastNode = curNode;
				break;
			}
			if (curTwinNode.board.isGoal()) {
				solvable = false;
				break;
			}
			for (Board neighbour : curNode.board.neighbors()) {
				if (curNode.prevSearchNode == null || !neighbour.equals(curNode.prevSearchNode.board)) {
					SearchNode neighbourNode = new SearchNode(neighbour, curNode, curNode.noOfMoves + 1);
					System.out.println("Adding Neighbour with Priority : " + neighbourNode.priority + " (Manhattan : "
							+ neighbour.manhattan() + ", No.of Moves : " + neighbourNode.noOfMoves + ")");
					System.out.println(neighbour);
					initialBoardPq.insert(neighbourNode);
				}
			}
			for (Board neighbour : curTwinNode.board.neighbors()) {
				if (curTwinNode.prevSearchNode == null || !neighbour.equals(curTwinNode.prevSearchNode.board)) {
					SearchNode neighbourNode = new SearchNode(neighbour, curTwinNode, curTwinNode.noOfMoves + 1);
					System.out.println(
							"Adding Neighbour of Twin with Priority : " + neighbourNode.priority + " (Manhattan : "
									+ neighbour.manhattan() + ", No.of Moves : " + neighbourNode.noOfMoves + ")");
					System.out.println(neighbour);
					twinBoardPq.insert(neighbourNode);
				}
			}
		}
	}

	// is the initial board solvable? (see below)
	public boolean isSolvable() {
		return solvable;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		if (!isSolvable())
			return -1;
		return lastNode.noOfMoves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (!isSolvable())
			return null;
		Stack<Board> boardStack = new Stack<>();
		SearchNode curNode = lastNode;
		while (curNode != null) {
			boardStack.push(curNode.board);
			curNode = curNode.prevSearchNode;
		}
		return boardStack;
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