package com.karthik.java;

import java.util.Arrays;

public class MergeSortInterviewQuestions {

	private static void exch(Comparable[] a, int from, int to) {
		Comparable tmp = a[from];
		a[from] = a[to];
		a[to] = tmp;
	}

	public static void mergeSortedSubArrayWithSmallerAuxArray(Comparable[] a, Comparable[] aux) {
		int mid = (a.length) / 2;
		int i = 0;
		for (int k = 0; k < mid; k++) {
			if (a[i].compareTo(a[mid]) < 0) {
				aux[k] = a[i++];
			} else {
				aux[k] = a[mid];
				a[mid] = a[i];
				for (int h = mid + 1; h < a.length && a[h].compareTo(a[h - 1]) < 0; h++) {
					exch(a, h, h - 1);
				}
				i++;
			}
		}
		for (int k = 0; k < aux.length; k++) {
			a[k] = aux[k];
		}

	}

	public static void main(String[] args) {
		Integer[] input = { 10, 84, 103, 110, 120, 130, 1, 2, 3, 4, 5, 6 };
		System.out.println("Before : " + Arrays.asList(input));
		Integer[] aux = new Integer[input.length / 2];
		mergeSortedSubArrayWithSmallerAuxArray(input, aux);
		System.out.println("After : " + Arrays.asList(input));
	}

}
