package com.leetcode.year_2020.graph;

/**
 * @author neeraj on 26/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DetectACycleHasAGraph {

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

        graph = new Graph(3);
        graph.addEdge(1, 0, true);
        graph.addEdge(0, 2, true);
        graph.addEdge(2, 1, true);
        System.out.println(graph.ifGraphHasCycle(graph));
    }

}
