package com.leetcode.year_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 24/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LRUCache {

    private Map<Integer, DoublyLinkedListNode> cache;
    private Integer capacity;
    DoublyLinkedListNode dummyHead;
    DoublyLinkedListNode dummyTail;

    static class DoublyLinkedListNode {
        public DoublyLinkedListNode prev;
        public DoublyLinkedListNode next;
        public int key;
        public int val;

        public DoublyLinkedListNode() {
        }

        /**
         * This will be used only by {@link FirstUniqueNumber}
         */
        public DoublyLinkedListNode(int val) {
            this.val = val;
        }

        public DoublyLinkedListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        dummyHead = new DoublyLinkedListNode();
        dummyTail = new DoublyLinkedListNode();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;

    }

    public int getV1(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        DoublyLinkedListNode node = cache.get(key);
        moveInFront(node);
        return node.val;
    }

    public void putV1(int key, int val) {
        if (cache.size() <= capacity || cache.containsKey(key)) {
            if (cache.containsKey(key)) {
                cache.get(key).val = val;
                getV1(key); // So this will move the node to front
            } else {
                insertAtFront(key, val);
                if (cache.size() > capacity) {
                    evictLastNode();
                }
            }
        } else {
            // We are sure to evict the key
            evictLastNode();
            insertAtFront(key, val);
        }
    }

    private void insertAtFront(int key, int value) {
        DoublyLinkedListNode nextOfHead = dummyHead.next;
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(key, value);
        newNode.next = nextOfHead;
        dummyHead.next = newNode;
        nextOfHead.prev = newNode;
        newNode.prev = dummyHead;
        cache.put(key, newNode);
    }

    private void evictLastNode() {
        DoublyLinkedListNode prevOfLast = dummyTail.prev;
        if (prevOfLast != dummyHead) {
            // There is a node to be removed
            dummyTail.prev = prevOfLast.prev;
            prevOfLast.prev.next = dummyTail;
            prevOfLast.next = null;
            prevOfLast.prev = null;
            cache.remove(prevOfLast.key);
        }
    }

    private void moveInFront(DoublyLinkedListNode node) {
        DoublyLinkedListNode nextOfNode = node.next;
        nextOfNode.prev = node.prev;
        node.prev.next = nextOfNode;

        //
        DoublyLinkedListNode nextOfHead = dummyHead.next;
        dummyHead.next = node;
        node.prev = dummyHead;
        node.next = nextOfHead;
        nextOfHead.prev = node;
    }


    public int get(int key) {
        if (cache.containsKey(key)) {
            DoublyLinkedListNode existingEntry = cache.get(key);
            existingEntry.prev.next = existingEntry.next;
            existingEntry.next.prev = existingEntry.prev;

            DoublyLinkedListNode nextOfDummyHead = dummyHead.next;
            dummyHead.next = existingEntry;
            existingEntry.prev = dummyHead;
            existingEntry.next = nextOfDummyHead;
            nextOfDummyHead.prev = existingEntry;
            return cache.get(key).val;
        } else {
            return -1;
        }
    }

    // Move the New/Existing item to the head of Doubly Linked List.
    public void put(int key, int value) {
        DoublyLinkedListNode newEntry = new DoublyLinkedListNode(key, value);
        if (cache.size() < capacity || cache.containsKey(key)) {
            if (cache.containsKey(key)) {
                DoublyLinkedListNode existingEntry = cache.get(key);
                existingEntry.val = value;
                existingEntry.prev.next = existingEntry.next;
                existingEntry.next.prev = existingEntry.prev;

                DoublyLinkedListNode nextOfDummyHead = dummyHead.next;
                dummyHead.next = existingEntry;
                existingEntry.prev = dummyHead;
                existingEntry.next = nextOfDummyHead;
                nextOfDummyHead.prev = existingEntry;
                cache.put(key, existingEntry);
            } else {
                DoublyLinkedListNode nextOfDummyHead = dummyHead.next;
                dummyHead.next = newEntry;
                newEntry.prev = dummyHead;
                newEntry.next = nextOfDummyHead;
                nextOfDummyHead.prev = newEntry;
                cache.put(key, newEntry);
            }
        } else { // Cache is full
            // Remove the last node.
            cache.remove(dummyTail.prev.key);

            dummyTail.prev = dummyTail.prev.prev;
            dummyTail.prev.next = dummyTail;

            // Add the new node to the head.
            DoublyLinkedListNode nextOfDummyHead = dummyHead.next;
            dummyHead.next = newEntry;
            newEntry.prev = dummyHead;
            newEntry.next = nextOfDummyHead;
            nextOfDummyHead.prev = newEntry;
            cache.put(key, newEntry);
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.putV1(1, 1);
        cache.putV1(2, 2);
        System.out.println(cache.getV1(1));       // returns 1
        cache.putV1(3, 3);    // evicts key 2
        System.out.println(cache.getV1(2));       // returns -1 (not found)
        cache.putV1(4, 4);    // evicts key 1
        System.out.println(cache.getV1(1));       // returns -1 (not found)
        System.out.println(cache.getV1(3));       // returns 3
        System.out.println(cache.getV1(4));       // returns 4

        System.out.println("=========================");
        cache = new LRUCache(1);
        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));

        System.out.println("==========================");
        cache = new LRUCache(2);
//        ["LRUCache","put","put","get","put","put","get"]
//        [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));

        System.out.println("==============");
        //["LRUCache","get","put","get","put","put","get","get"]
        //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
