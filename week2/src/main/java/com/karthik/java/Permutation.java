package com.karthik.java;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		System.out.println("Input k : " + k);
		RandomizedQueue<String> rqueueOfStrings = new RandomizedQueue<String>();
		int i = 0;
		while (!StdIn.isEmpty()) {
			String str = StdIn.readString();
			System.out.println("Current String "+str);
			i++;
			if (i == k+1) {
				rqueueOfStrings.dequeue();
				i--;
			}
			rqueueOfStrings.enqueue(str);
			System.out.println("Current i : " + i + " RQueue size : " + rqueueOfStrings.size());
		}

		while (k > 0) {
			System.out.println(rqueueOfStrings.dequeue());
			k--;
		}

	}

}
