package com.leetcode.year_2020.graph.cycle_detection;

import com.leetcode.year_2020.graph.Graph;

/**
 * @author neeraj on 26/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DetectADirectedGraphHasACycle {

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(1, 2, true);
        graph.addEdge(1, 3, true);
        graph.addEdge(3, 4, true);
        graph.addEdge(3, 5, true);
        graph.addEdge(5, 6, true);
        graph.addEdge(6, 3, true);


        graph.addEdge(11, 10, true);
        graph.addEdge(10, 9, true);
        graph.addEdge(9, 12, true);
        graph.addEdge(9, 7, true);
        graph.addEdge(9, 8, true);
        graph.addEdge(12, 11, true);


        System.out.println(graph.ifGraphHasCycle(graph));

        graph = new Graph(6);
        graph.addEdge(1, 2, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(3, 4, true);
        graph.addEdge(4, 5, true);
        graph.addEdge(2, 5, true);
        System.out.println(graph.ifGraphHasCycle(graph));

        /**
         * Diagram:
         * 0 -> 1 -> 2
         * ^         |
         * |_________|
         */
        System.out.println(hasCycle(new int[][]{{1}, {2}, {0}})); // Expected output: true

        /**
         * Diagram:
         * 0 -> 1 -> 2
         */
        System.out.println(hasCycle(new int[][]{{1}, {2}, {}})); // Expected output: false

        /**
         * Diagram:
         * 0 -> 1 -> 2 -> 0
         *      |         |
         *      |_________|
         * 2 -> 3 -> 3
         */
        System.out.println(hasCycle(new int[][]{{1, 2}, {2}, {0, 3}, {3}})); // Expected output: true

        /**
         * Diagram:
         * 0 -> 0
         * 1 -> 2 -> 3
         */
        System.out.println(hasCycle(new int[][]{{0}, {2}, {3}, {}})); // Expected output: true

        /**
         * Diagram:
         * 0 -> 1 -> 2 -> 0
         * ^         |
         * |_________|
         * 3 -> 4 -> 5 -> 3
         *      ^         |
         *      |_________|
         */
        System.out.println(hasCycle(new int[][]{{1}, {2}, {0}, {4}, {5}, {3}})); // Expected output: true
    }

    public static boolean hasCycle(int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        boolean[] pathVisited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                if (hasCycle(graph, i, visited, pathVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasCycle(int[][] graph, int source, boolean[] visited, boolean[] pathVisited) {
        visited[source] = true;
        pathVisited[source] = true;

        for (int adjacent : graph[source]) {
            if (pathVisited[adjacent]) {
                return true; // found cycle
            }
            if (!visited[adjacent] && hasCycle(graph, adjacent, visited, pathVisited)) {
                return true;
            }
        }
        pathVisited[source] = false; // Explored the path, lets reset
        return false;
    }

}
