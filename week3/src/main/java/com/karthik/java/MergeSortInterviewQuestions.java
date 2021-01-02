package com.karthik.java;

import java.util.Arrays;

public class MergeSortInterviewQuestions {

	private static <T> void exch(Comparable<T>[] a, int from, int to) {
		Comparable<T> tmp = a[from];
		a[from] = a[to];
		a[to] = tmp;
	}

	public static <T> void mergeSortedSubArrayWithSmallerAuxArray(Comparable<T>[] a, Comparable<T>[] aux) {
		int mid = (a.length) / 2;
		int i = 0;
		for (int k = 0; k < mid; k++) {
			if (a[i].compareTo((T) a[mid]) < 0) {
				aux[k] = a[i++];
			} else {
				aux[k] = a[mid];
				a[mid] = a[i];
				for (int h = mid + 1; h < a.length && a[h].compareTo((T) a[h - 1]) < 0; h++) {
					exch(a, h, h - 1);
				}
				i++;
			}
		}
		for (int k = 0; k < aux.length; k++) {
			a[k] = aux[k];
		}

	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static void mergeWithSmaller(Comparable[] a, Comparable[] aux) {
		int N = aux.length;
		assert a.length == 2 * N;

		for (int i = 0; i < N; i++) {
			aux[i] = a[i];
		}
		System.out.println("Aux : " + Arrays.asList(aux));

		int l = 0;
		int r = N;

		int i = 0;
		for (; i < N; i++) {
			if (less(aux[l], a[r]))
				a[i] = aux[l++];
			else
				a[i] = a[r++];
		}

		while (l < N) {
			if (r >= 2 * N || less(aux[l], a[r]))
				a[i++] = aux[l++];
			else
				a[i++] = a[r++];
		}
	}

	public static void main(String[] args) {
		// Integer[] input = { 10, 84, 103, 110, 120, 130, 1, 2, 3, 4, 5, 6 };
		Integer[] input = { 1, 4, 5, 8, 2, 3, 6, 7 };
		System.out.println("Before : " + Arrays.asList(input));
		Integer[] aux = new Integer[input.length / 2];
		mergeSortedSubArrayWithSmallerAuxArray(input, aux);
		System.out.println("After : " + Arrays.asList(input));
	}

}
