package com.karthik.java;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    int capacity;
    Map<Integer, Node> dataMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void insert(Node n) {
        dataMap.put(n.key, n);
        Node headNext = head.next;
        head.next = n;
        n.prev = head;
        n.next = headNext;
        headNext.prev = n;
    }

    private void remove(Node n) {
        dataMap.remove(n.key);
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    public int get(int key) {
        if (dataMap.containsKey(key)) {
            Node n = dataMap.get(key);
            remove(n);
            insert(n);
            return n.value;
        } else
            return -1;
    }

    public void put(int key, int value) {
        Node n = new Node(key, value);
        if (dataMap.containsKey(key))
            remove(dataMap.get(key));
        else if (dataMap.size() == capacity)
            remove(tail.prev);
        insert(n);
    }

    public static void main(){
        LRUCache lc = new LRUCache(2);

    }
}