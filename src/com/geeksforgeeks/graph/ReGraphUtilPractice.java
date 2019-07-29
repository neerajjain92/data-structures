package com.geeksforgeeks.graph;

import com.util.LogUtil;

import java.util.*;

import static com.util.LogUtil.logIt;
import static com.util.LogUtil.newLine;

/**
 * @author neeraj on 2019-07-28
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("Duplicates")
public class ReGraphUtilPractice {

    static class Graph {
        int V;
        LinkedList<Integer>[] adjacentListArray;

        public Graph(int v) {
            this.V = v;

            // Initialized place holder for every node/vertex
            adjacentListArray = new LinkedList[V];

            for (int i = 0; i < adjacentListArray.length; i++) {
                adjacentListArray[i] = new LinkedList<>();
            }
        }
    }

    public static void addEdge(Graph graph, int source, int destination, boolean isDirectedGraph) {
        graph.adjacentListArray[source].add(destination);

        if (!isDirectedGraph) {
            graph.adjacentListArray[destination].add(source);
        }
    }

    public static void addEdge(Graph graph, int source, int destination) {
        addEdge(graph, source, destination, false);
    }

    public static void printGraph(Graph graph) {
        for (int i = 0; i < graph.adjacentListArray.length; i++) {
            LogUtil.logInSingleLine("Adjacent Vertices for " + i + " --> ");
            System.out.println(graph.adjacentListArray[i]);
        }
    }

    public static void BreadthFirstTraversal(Graph graph, int source) {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArray;
        Queue<Integer> queue = new LinkedList<>();

        // We will start traversing from source
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            int popped = queue.poll();
            System.out.print(popped + " ");

            for (int a : adjacentListArr[popped]) {
                if (!visited.contains(a)) {
                    queue.add(a);
                    visited.add(a);
                }
            }
        }
    }

    public static void DepthFirstTraversal(Graph graph, int source) {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArray;
        Stack<Integer> stack = new Stack<>();

        // We start from source, and push to stack but will not visit it until actually processed.
        // Also in Stack we just process the node and move to next note, unlike BFS where we actually
        // First sweep all neighbours and then proceed further.
        stack.push(source);

        while (!stack.isEmpty()) {
            int popped = stack.pop();
            visited.add(popped);
            System.out.print(popped + " ");

            for (int a : adjacentListArr[popped]) {
                if (!visited.contains(a)) {
                    stack.add(a);
                }
            }
        }
    }

    public static void recursiveDFS(Graph graph, int source, Set<Integer> visited) {
        System.out.print(source + " ");
        visited.add(source);

        for (int node : graph.adjacentListArray[source]) {
            if (!visited.contains(node)) {
                recursiveDFS(graph, node, visited);
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph(5);

        addEdge(graph, 0, 1);
        addEdge(graph, 0, 4);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);

        printGraph(graph);

        logIt("BREADTH FIRST TRAVERSAL OF GRAPH", true);
        BreadthFirstTraversal(graph, 2);

        newLine();
        logIt("BREADTH FIRST TRAVERSAL OF DIRECTED GRAPH", true);
        graph = new Graph(4);

        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 3, 3, true);
        BreadthFirstTraversal(graph, 2);

        newLine();
        logIt("Directed Graph looks like this ", true);
        printGraph(graph);

        logIt("Depth FIRST TRAVERSAL OF DIRECTED GRAPH", true);
        DepthFirstTraversal(graph, 2);

        newLine();
        logIt("Depth FIRST TRAVERSAL OF DIRECTED GRAPH", true);
        recursiveDFS(graph, 2, new HashSet<>());

    }
}
