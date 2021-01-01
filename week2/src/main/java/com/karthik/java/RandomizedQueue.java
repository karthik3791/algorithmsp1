package com.karthik.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private Item[] items;

	private static class RandomizedQueueIterator<Item> implements Iterator<Item> {
		private Item[] randomItemsToIterate;
		private int currentElementIndex = 0;

		public RandomizedQueueIterator(RandomizedQueue<Item> q) {
			randomItemsToIterate = (Item[]) new Object[q.size];
			int k = 0;
			Item[] originalItemList = (Item[]) new Object[q.size];
			for (int i = 0; i < q.size; i++) {
				originalItemList[i] = (Item) q.items[i];
			}
			while (!q.isEmpty()) {
				randomItemsToIterate[k++] = q.dequeue();
			}
			q.items = originalItemList;
			q.size = originalItemList.length;
		}

		@Override
		public boolean hasNext() {
			return currentElementIndex < randomItemsToIterate.length;
		}

		@Override
		public Item next() {
			if (currentElementIndex == randomItemsToIterate.length) {
				throw new java.util.NoSuchElementException("Iterator has been exhausted.");
			}
			return randomItemsToIterate[currentElementIndex++];
		}

	}

	private void itemNonNullCheck(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Cannot call with parameter null");
	}

	private void emptyQueueCheck() {
		if (isEmpty())
			throw new NoSuchElementException("Empty Deque");
	}

	private void resizeArray(int newLength) {
		Item[] newItemList = (Item[]) new Object[newLength];
		for (int i = 0; i < size; i++) {
			newItemList[i] = items[i];
		}
		items = newItemList;
	}

	public RandomizedQueue() {
		size = 0;
		items = (Item[]) new Object[1];
	}

	public void enqueue(Item item) {
		itemNonNullCheck(item);
		if (items.length == size) {
			resizeArray(items.length * 2);
		}
		items[size++] = item;
	}

	public Item dequeue() {
		emptyQueueCheck();
		if (size == items.length / 4) {
			resizeArray(items.length / 2);
		}
		int randomIndexToRemove = StdRandom.uniform(size);
		Item removedItem = items[randomIndexToRemove];
		items[randomIndexToRemove] = items[--size];
		items[size] = null;
		return removedItem;
	}

	public Item sample() {
		emptyQueueCheck();
		int randomIndex = StdRandom.uniform(size);
		Item randomItem = items[randomIndex];
		return randomItem;
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator<Item>(this);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Printing Elements in RandomizedQueue...\n");
		for (Item i : this) {
			str.append(i.toString() + " --> ");
		}
		return str.toString();
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> randomizedQueueOfInts = new RandomizedQueue<>();
		System.out.println("RandomizedQueue Empty Check : " + randomizedQueueOfInts.isEmpty());
		randomizedQueueOfInts.enqueue(1);
		System.out.println("No. of Elements in RandomizedQueue : " + randomizedQueueOfInts.size() + " Is Empty : "
				+ randomizedQueueOfInts.isEmpty());
		System.out.println(randomizedQueueOfInts);
		randomizedQueueOfInts.enqueue(2);
		System.out.println(randomizedQueueOfInts);
		randomizedQueueOfInts.enqueue(3);
		System.out.println("No. of Elements in RandomizedQueue : " + randomizedQueueOfInts.size() + " Is Empty : "
				+ randomizedQueueOfInts.isEmpty());
		System.out.println(randomizedQueueOfInts);
		randomizedQueueOfInts.dequeue();
		System.out.println(randomizedQueueOfInts);
		randomizedQueueOfInts.dequeue();
		System.out.println(randomizedQueueOfInts);
		randomizedQueueOfInts.dequeue();
		System.out.println(randomizedQueueOfInts);
	}

}
