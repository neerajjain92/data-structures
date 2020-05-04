package com.leetcode.year_2020;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 28/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KLargestElementsInAnImmutableMaxHeap {

    static int[] immutableHeap = null;

    public static void main(String[] args) {
        System.out.println(kLargestInImmutableHeap(new int[]{17, 7, 16, 2, 3, 15, 14},
                2));
    }

    public static List<Integer> kLargestInImmutableHeap(int[] immHeap, int k) {
        immutableHeap = immHeap;
        List<Integer> elements = new ArrayList<>();
        /**
         * Since heap is immutable we have to walk down the heap to
         * find out kLargest elements.
         * We will have to use some builder methods.
         */
        int index = 0;
        int rightChild = 0;
        int leftChild = 0;
        while (k-- > 0) {
            elements.add(immutableHeap[index]);
            if (hasLeftChild(index)) {
                leftChild = getLeftChild(index);
                if (hasRightChild(index)) {
                    rightChild = getRightChild(index);
                }
                index = leftChild < rightChild ? getRightChildIndex(index) : getLeftChildIndex(index);
            }
        }
        return elements;
    }

    public static int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    public static int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    public static boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < immutableHeap.length;
    }

    public static boolean hasRightChild(int index) {
        return getRightChildIndex(index) < immutableHeap.length;
    }

    public static int getLeftChild(int index) {
        return immutableHeap[getLeftChildIndex(index)];
    }

    public static int getRightChild(int index) {
        return immutableHeap[getRightChildIndex(index)];
    }
}
