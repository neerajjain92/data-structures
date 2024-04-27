package com.leetcode.year_2020.tree.avltree;

public interface Tree<T extends Comparable<T>> {
    Tree<T> insert(T data);

    void delete(T data);

    void traverse();

    T getMax();

    T getMin();

    boolean isEmpty();
}
