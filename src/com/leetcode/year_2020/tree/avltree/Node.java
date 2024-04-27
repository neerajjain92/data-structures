package com.leetcode.year_2020.tree.avltree;

import lombok.Data;
import lombok.NonNull;

@Data
public class Node<T extends Comparable<T>>  {
    @NonNull
    private T data;
    private int height = 1; // Initially every node is a leaf node and should be given height=1
    private Node<T> leftChild;
    private Node<T> rightChild;
}
