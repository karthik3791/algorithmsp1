package com.karthik.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class VerticalOrderTraversal {

	public class PQEntry implements Comparable<PQEntry> {
		Integer row;
		Integer column;
		Integer value;

		public PQEntry(Integer r, Integer c, Integer v) {
			row = r;
			column = c;
			value = v;
		}

		@Override
		public int compareTo(PQEntry o) {
			int rowCompare = row.compareTo(o.row);
			int colCompare = column.compareTo(o.column);
			int valCompare = value.compareTo(o.value);
			return (colCompare == 0) ? (rowCompare == 0 ? valCompare : rowCompare) : colCompare;
		}
	}

	public class MinPriorityQueue<T> {
		private T[] pq; // store items at indices 1 to n
		private int n; // number of items on priority queue
		private Comparator<T> comparator; // optional comparator

		public MinPriorityQueue(int initCapacity) {
			pq = (T[]) new Object[initCapacity + 1];
			n = 0;
		}

		public MinPriorityQueue() {
			this(1);
		}

		public MinPriorityQueue(int initCapacity, Comparator<T> comparator) {
			this.comparator = comparator;
			pq = (T[]) new Object[initCapacity + 1];
			n = 0;
		}

		public MinPriorityQueue(Comparator<T> comparator) {
			this(1, comparator);
		}

		public boolean isEmpty() {
			return n == 0;
		}

		public int size() {
			return n;
		}

		private void resize(int capacity) {
			assert capacity > n;
			T[] temp = (T[]) new Object[capacity];
			for (int i = 1; i <= n; i++) {
				temp[i] = pq[i];
			}
			pq = temp;
		}

		private void swim(int k) {
			while (k > 1 && greater(k / 2, k)) {
				exch(k, k / 2);
				k = k / 2;
			}
		}

		private void sink(int k) {
			while (2 * k <= n) {
				int j = 2 * k;
				if (j < n && greater(j, j + 1))
					j++;
				if (!greater(k, j))
					break;
				exch(k, j);
				k = j;
			}
		}

		private boolean greater(int i, int j) {
			if (comparator == null) {
				return ((Comparable<T>) pq[i]).compareTo(pq[j]) > 0;
			} else {
				return comparator.compare(pq[i], pq[j]) > 0;
			}
		}

		private void exch(int i, int j) {
			T swap = pq[i];
			pq[i] = pq[j];
			pq[j] = swap;
		}

		public void insert(T x) {
			if (n == pq.length - 1)
				resize(2 * pq.length);

			pq[++n] = x;
			swim(n);
		}

		public T delMin() {
			if (isEmpty())
				throw new NoSuchElementException("Priority queue underflow");
			T min = pq[1];
			exch(1, n--);
			sink(1);
			pq[n + 1] = null; // to avoid loiterig and help with garbage collection
			if ((n > 0) && (n == (pq.length - 1) / 4))
				resize(pq.length / 2);
			return min;
		}

	}

	private MinPriorityQueue<PQEntry> buildVerticalOrderPriorityQueue(int rowIndex, int columnIndex, TreeNode root,
			MinPriorityQueue<PQEntry> pqueue) {
		if (root == null)
			return pqueue;
		buildVerticalOrderPriorityQueue(rowIndex + 1, columnIndex - 1, root.left, pqueue);
		buildVerticalOrderPriorityQueue(rowIndex + 1, columnIndex + 1, root.right, pqueue);
		pqueue.insert(new PQEntry(rowIndex, columnIndex, root.val));
		return pqueue;
	}

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		MinPriorityQueue<PQEntry> pqueue = buildVerticalOrderPriorityQueue(0, 0, root, new MinPriorityQueue<PQEntry>());
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> currentCol = new ArrayList<>();
		int currentColIndex = Integer.MIN_VALUE;
		while (!pqueue.isEmpty()) {
			PQEntry node = pqueue.delMin();
			if (currentColIndex != node.column) {
				currentCol = new ArrayList<>();
				res.add(currentCol);
				currentColIndex = node.column;
			}
			currentCol.add(node.value);
		}
		return res;
	}

	public static void main(String[] args) {
		Integer[] tree1 = { 3, 9, 20, null, null, 15, 7 };
		TreeNode root = TreeUtils.convertLevelOrderedInputToTree(tree1);
		System.out.println("Vertical Traversal of Tree1 : " + new VerticalOrderTraversal().verticalTraversal(root));

		Integer[] tree2 = { 1, 2, 3, 4, 5, 6, 7 };
		root = TreeUtils.convertLevelOrderedInputToTree(tree2);
		System.out.println("Vertical Traversal of Tree2 : " + new VerticalOrderTraversal().verticalTraversal(root));

		Integer[] tree3 = { 1, 2, 3, 4, 6, 5, 7 };
		root = TreeUtils.convertLevelOrderedInputToTree(tree3);
		System.out.println("Vertical Traversal of Tree3 : " + new VerticalOrderTraversal().verticalTraversal(root));
	}

}
