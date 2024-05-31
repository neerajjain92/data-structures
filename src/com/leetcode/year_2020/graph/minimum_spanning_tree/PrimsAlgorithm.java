package com.leetcode.year_2020.graph.minimum_spanning_tree;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
 * <p>
 * Minimum Spanning Tree
 */
public class PrimsAlgorithm {

    public static void main(String[] args) {
        System.out.println("Weight of Minimum Spanning Tree " + spanningTree(5, 6, List.of(
                List.of(new int[]{1, 2}, new int[]{2, 1}),
                List.of(new int[]{0, 2}, new int[]{2, 1}),
                List.of(new int[]{0, 1}, new int[]{1, 1}, new int[]{4, 2}),
                List.of(new int[]{2, 2}, new int[]{4, 1}),
                List.of(new int[]{2, 2}, new int[]{3, 1}))));
    }

    static class Pair {
        int weight;
        int vertex;
        int parent;

        public Pair(int weight, int vertex, int parent) {
            this.weight = weight;
            this.vertex = vertex;
            this.parent = parent;
        }
    }

    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        boolean[] visited = new boolean[V];
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        minHeap.add(new Pair(0, 0, -1));
        int weight = 0;
        List<int[]> edges = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Pair polled = minHeap.poll();
            if (!visited[polled.vertex]) {
                weight += polled.weight;
                visited[polled.vertex] = true;
                for (int[] neighbour : adj.get(polled.vertex)) {
                    if (!visited[neighbour[0]]) {
                        minHeap.add(new Pair(neighbour[1], neighbour[0], polled.vertex));
                    }
                }
                if (polled.parent != -1) {
                    edges.add(new int[]{polled.parent, polled.vertex});
                }
            }
        }
//        for (int[] edge : edges) {
//            LogUtil.printArray(edge);
//        }
        return weight;
    }
}
