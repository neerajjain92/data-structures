package com.geeksforgeeks.heap;

import java.util.Arrays;

@SuppressWarnings({"Duplicates", "SpellCheckingInspection"})
public class MinHeap {

    private int capacity = 10;
    private int size = 0;

    int[] items = new int[capacity];


    public int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    public int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    public int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    public boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    public boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    public int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    public int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    public int parent(int index) {
        return items[getParentIndex(index)];
    }

    public void swap(int indexOne, int indexTwo) {
        int temp;
        temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    public void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }


    /**
     * This method will get the root, In case of Min Heap it's the minimum element in heap.
     *
     * @return
     */
    public int peek() {
        if (size == 0)
            throw new IllegalStateException();
        return items[0];
    }

    /**
     * This method will extract the minimum element that is the root,
     * and also re-heapify the heap
     *
     * @return
     */
    public int extractMin() {
        if (size == 0)
            throw new IllegalStateException();

        int item = items[0];
        items[0] = items[size - 1];
        size--;

        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    public void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && parent(index) > items[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && rightChild(index) < items[smallerChildIndex]) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] > items[smallerChildIndex]) {
                swap(smallerChildIndex, index);
                index = smallerChildIndex;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        minHeap.add(10);
        minHeap.add(15);
        minHeap.add(20);
        minHeap.add(17);
        minHeap.add(25);

        System.out.println("Extracting the Minimum Element ::--> " + minHeap.extractMin());
        System.out.println("Now Who is the minimum element ::--> " + minHeap.peek());

        minHeap.add(1);
        System.out.println("Now the minimum is ::-->"+ minHeap.peek());
        minHeap.extractMin();
        System.out.println("Now the minimum is ::-->"+minHeap.peek());
    }

}
