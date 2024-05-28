package com.leetcode.year_2020.graph.topologicalSort;

import com.util.LogUtil;

import java.util.*;

/**
 * Shortest Path in Directed Acyclic Graph
 * https://www.youtube.com/watch?v=ZUFQfFaU-8U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=27
 * <p>
 * Input: n = 6, m= 7
 * edges =[[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
 */
public class ShortestPathInDirectedAcyclicGraph {

    public static void main(String[] args) {
        LogUtil.printArray(shortestPathInDAG(6, 7,
                new int[][]{{0, 1, 2}, {0, 4, 1}, {4, 5, 4}, {4, 2, 2}, {1, 2, 3}, {2, 3, 6}, {5, 3, 1}},
                0));

        LogUtil.printArray(shortestPathInDAG(7, 8,
                new int[][]{{0, 4, 2}, {0, 5, 3}, {5, 4, 1}, {4, 6, 3}, {4, 2, 1}, {6, 1, 2}, {2, 3, 3}, {1, 3, 1}},
                0));
    }

    static class Pair {
        int vertex;
        int weight;

        public Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private static int[] shortestPathInDAG(int n, int m, int[][] edgesWithWeight, int source) {
        /**
         * Do TOPOLOGICAL SORT
         * with that we will know that the dependent nodes connecting the other node
         *
         *       1 -----[3]------->2
         *     /              /\   \\
         *   [2]             //     [6]
         *   /             //         \\
         *  /             //          \/
         * 0             [2]          3
         * \            //             /\
         *  \          //              //
         *  [1]       //              [1]
         *    \     //              //
         *    \/  //                //
         *    4-----[4]--------5
         *
         * Cost denoted as [cost]:
         *
         * From the above graph, if we do a topo sort, so we get
         * 0 --> 1 --> 4 --> 2 --> 5 --> 3
         *
         * Based on directed edges above, we can confirm that topo sort it correct or not.
         * 5 ---> 3 is an edge and similarly we can validate in topo sort, 5 is coming before 3
         *
         * Now our job is simply, pick items from TOPO Sort, and perform cost comparision
         * on adjacency list
         */

        List<Pair>[] adjacencyListArr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyListArr[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];
        for (int[] edge : edgesWithWeight) {
            indegree[edge[1]] += 1;
            adjacencyListArr[edge[0]].add(new Pair(edge[1], edge[2]));
        }

        // Now perform topo sort
        List<Integer> topoSort = findTopoSort(indegree, adjacencyListArr);

        // Now the game is just performing
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[topoSort.get(0)] = 0;
        for (int i : topoSort) {
            for (Pair adjacent : adjacencyListArr[i]) {
                distance[adjacent.vertex] =
                        Math.min(distance[adjacent.vertex],
                                distance[i] + adjacent.weight);
            }
        }
        return distance;
    }

    private static List<Integer> findTopoSort(int[] indegree, List<Pair>[] adjacencyListArr) {
        List<Integer> topoSort = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer polledVertex = queue.poll();
            topoSort.add(polledVertex);
            for (Pair pair : adjacencyListArr[polledVertex]) {
                indegree[pair.vertex] -= 1;
                if (indegree[pair.vertex] == 0) {
                    queue.add(pair.vertex);
                }
            }
        }
        return topoSort;
    }
}
