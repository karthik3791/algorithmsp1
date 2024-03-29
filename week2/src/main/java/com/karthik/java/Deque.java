package com.karthik.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node<Item> first;
	private Node<Item> last;

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;

	}

	private static class DequeIterator<Item> implements Iterator<Item> {
		private Node<Item> current;
		private int a = 1;

		public DequeIterator(Deque<Item> d) {
			current = d.first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (current == null) {
				throw new java.util.NoSuchElementException("Iterator has been exhausted.");
			}
			Item i = current.item;
			current = current.next;
			return i;
		}

	}

	private void itemNonNullCheck(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Cannot call with parameter null");
	}

	private void emptyDequeCheck() {
		if (isEmpty())
			throw new NoSuchElementException("Empty Deque");
	}

	public Deque() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator(this);
	}

	public void addFirst(Item item) {
		itemNonNullCheck(item);
		Node<Item> newNode = new Node<>();
		newNode.item = item;
		if (isEmpty()) {
			newNode.next = null;
			newNode.prev = null;
			first = newNode;
			last = newNode;
		} else {
			newNode.next = first;
			newNode.prev = null;
			first.prev = newNode;
			first = newNode;
		}
		size++;
	}

	public void addLast(Item item) {
		itemNonNullCheck(item);
		Node<Item> newNode = new Node<>();
		newNode.item = item;
		if (isEmpty()) {
			newNode.next = null;
			newNode.prev = null;
			first = newNode;
			last = newNode;
		} else {
			newNode.next = null;
			newNode.prev = last;
			last.next = newNode;
			last = newNode;
		}
		size++;
	}

	public Item removeFirst() {
		emptyDequeCheck();
		Item removedItem = first.item;
		if (first == last && size == 1) {
			first = null;
			last = null;
		} else {
			first.next.prev = null;
			first = first.next;
		}
		size--;
		return removedItem;
	}

	public Item removeLast() {
		emptyDequeCheck();
		Item removedItem = last.item;
		if (first == last && size == 1) {
			first = null;
			last = null;
		} else {
			last.prev.next = null;
			last = last.prev;
		}
		size--;
		return removedItem;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return first == null && last == null && size == 0;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Printing Elements in Deque...\n");
		for (Item i : this) {
			System.out.println("Current Element : " + i);
			str.append(i.toString() + " --> ");
		}
		return str.toString();
	}

	public static void main(String[] args) {
		Deque<Integer> dequeOfInts = new Deque<>();
		System.out.println("Deque Empty Check : " + dequeOfInts.isEmpty());
		dequeOfInts.addFirst(1);
		System.out.println("No. of Elements in Deque : " + dequeOfInts.size() + " Is Empty : " + dequeOfInts.isEmpty());
		System.out.println(dequeOfInts);
		dequeOfInts.addFirst(2);
		System.out.println(dequeOfInts);
		dequeOfInts.addLast(3);
		System.out.println("No. of Elements in Deque : " + dequeOfInts.size() + " Is Empty : " + dequeOfInts.isEmpty());
		System.out.println(dequeOfInts);
		dequeOfInts.removeFirst();
		System.out.println(dequeOfInts);
		dequeOfInts.removeLast();
		System.out.println(dequeOfInts);
		dequeOfInts.removeLast();
		System.out.println(dequeOfInts);
	}

}
