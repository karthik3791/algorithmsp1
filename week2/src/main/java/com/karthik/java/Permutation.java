package com.karthik.java;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		System.out.println("Input k : " + k);
		RandomizedQueue<String> rqueueOfStrings = new RandomizedQueue<String>();
		while (true) {
			try {
				rqueueOfStrings.enqueue(StdIn.readString());
			} catch (NoSuchElementException nse) {
				System.out.println("End of StdIn breaking out..");
				break;
			}
		}

		while (k > 0) {
			System.out.println(rqueueOfStrings.dequeue());
			k--;
		}

	}

}
