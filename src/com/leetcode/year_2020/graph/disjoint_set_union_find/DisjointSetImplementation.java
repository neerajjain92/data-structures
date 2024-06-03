package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=aBxjDBC4M1U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=47
 */
public class DisjointSetImplementation {

    private List<Integer> ranks = new ArrayList<>();
    private List<Integer> size = new ArrayList<>();
    private List<Integer> parent = new ArrayList<>();

    public DisjointSetImplementation(int n) {
        for (int i = 0; i <= n; i++) {
            ranks.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findUltimateParent(int u) {
        if (u == parent.get(u)) {
            return u;
        }
        int ultimateParent = findUltimateParent(parent.get(u));
        parent.set(u, ultimateParent);
        return parent.get(u);
    }

    public void unionBySize(int u, int v) {
        int parent_u = findUltimateParent(u);
        int parent_v = findUltimateParent(v);
        if (parent_v == parent_u) return; // Already belonging to the same component

        int size_u = size.get(parent_u);
        int size_v = size.get(parent_v);

        if (size_u < size_v) {
            size.set(parent_v, size_v + size_u);
            parent.set(parent_u, parent_v);
        } else if (size_u > size_v) {
            size.set(parent_u, size_u + size_v);
            parent.set(parent_v, parent_u);
        } else {
            size.set(parent_u, size_v + size_u);
            parent.set(parent_v, parent_u);
        }
    }

    public int getComponentSize(int node) {
        int ultimateParent = findUltimateParent(node);
        return size.get(ultimateParent);
    }

    public void unionByRank(int u, int v) {
        int parent_u = findUltimateParent(u);
        int parent_v = findUltimateParent(v);
        if (parent_v == parent_u) return; // Already belonging to the same component

        int rank_u = ranks.get(parent_u);
        int rank_v = ranks.get(parent_v);

        if (rank_u < rank_v) {
            parent.set(parent_u, parent_v);
        } else if (rank_u > rank_v) {
            parent.set(parent_v, parent_u);
        } else {
            parent.set(parent_u, parent_v);
            ranks.set(parent_v, ranks.get(parent_v) + 1);
        }
    }

    public static void main(String[] args) {
        DisjointSetImplementation disjointSet = new DisjointSetImplementation(7);
        disjointSet.unionBySize(1, 2);
        disjointSet.unionBySize(2, 3);
        disjointSet.unionBySize(4, 5);
        disjointSet.unionBySize(6, 7);
        disjointSet.unionBySize(5, 6);

        // if 3 and 7 same or not
        if (disjointSet.findUltimateParent(3) == disjointSet.findUltimateParent(7)) {
            System.out.println("SAME");
        } else {
            System.out.println("NOT SAME"); // This should be the right answer
        }

        disjointSet.unionBySize(3, 7);
        if (disjointSet.findUltimateParent(3) == disjointSet.findUltimateParent(7)) {
            System.out.println("SAME"); // This should be true this time
        }
    }


}
