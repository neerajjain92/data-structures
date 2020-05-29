package com.leetcode.year_2020.graph;

import com.util.LogUtil;

import java.util.*;


/**
 * @author neeraj on 26/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Graph {

    int totalVertices;
    List<GraphVertex>[] adjacencyListArr;

    public Graph(int totalVertices) {
        this.totalVertices = totalVertices;
        adjacencyListArr = new LinkedList[totalVertices];

        // Initialize all those adjacent list to empty.
        for (int i = 0; i < adjacencyListArr.length; i++) {
            adjacencyListArr[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, boolean isDirected) {
        GraphVertex destinationGraphVertex = new GraphVertex(destination, GraphVertex.Color.WHITE);
        GraphVertex sourceGraphVertex = new GraphVertex(source, GraphVertex.Color.WHITE);

        this.adjacencyListArr[source].add(destinationGraphVertex);

        if (!isDirected) { // For Undirected graph.... edge goes both way
            // A----B || B----A
            this.adjacencyListArr[destination].add(sourceGraphVertex);
        }
    }

    /**
     * White -> Unvisited (we can visit this node, it is not on a pending path & hasn't been processed)
     * Gray -> Visiting (currently on the path being traversed)
     * Black -> Visited (do not traverse again)
     **/
    public static class GraphVertex {
        enum Color {WHITE, GRAY, BLACK}

        Color color;
        int value;

        public GraphVertex(int value, Color color) {
            this.color = color;
            this.value = value;
        }
    }

    public void bfs(Graph graph, int source) {
        LogUtil.logIt("Breadth First Search of Graph is ", true);
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        seen.add(source);

        while (!queue.isEmpty()) {
            int vertexWeAreCurrentlyOn = queue.poll();
            System.out.print(vertexWeAreCurrentlyOn + " ---> ");
            List<GraphVertex> adjacentVertexes = graph.adjacencyListArr[vertexWeAreCurrentlyOn];

            for (GraphVertex vertex : adjacentVertexes) {
                if (!seen.contains(vertex.value)) {
                    queue.add(vertex.value);
                    seen.add(vertex.value);
                }
            }
        }
        LogUtil.newLine();
    }

    public void dfsUsingStack(Graph graph, int source) {
        LogUtil.logIt("Depth First Search of Graph is ", true);
        Set<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        seen.add(source);

        while (!stack.isEmpty()) {
            int poppedVertex = stack.pop();
            System.out.print(poppedVertex + " --> ");
            List<GraphVertex> adjacentVertexes = graph.adjacencyListArr[poppedVertex];
            for (GraphVertex vertex : adjacentVertexes) {
                if (!seen.contains(vertex.value)) {
                    stack.push(vertex.value);
                    seen.add(vertex.value);
                }
            }
        }
    }

    public void dfs(Graph graph, int source) {
        LogUtil.logIt("Depth First Search of Graph is ", true);
        Set<Integer> seen = new HashSet<>();
        doDFS(graph.adjacencyListArr, source, seen);
        LogUtil.newLine();
    }

    private void doDFS(List<GraphVertex>[] verticesEdges, int source, Set<Integer> seen) {
        System.out.print(source + " --> ");
        seen.add(source);

        List<GraphVertex> adjacentToSource = verticesEdges[source];
        for (GraphVertex vertex : adjacentToSource) {
            if (!seen.contains(vertex.value)) {
                doDFS(verticesEdges, vertex.value, seen);
            }
        }
    }


    public boolean ifGraphHasCycle(Graph graph) {
        for (int i = 1; i < graph.totalVertices; i++) {
            List<GraphVertex> vertexes = graph.adjacencyListArr[i];
            for (GraphVertex vertex : vertexes) {
                if (vertex.color == GraphVertex.Color.WHITE && hasCycleFromVertex(vertex, graph.adjacencyListArr)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleFromVertex(GraphVertex vertex, List<GraphVertex>[] adjacencyListArr) {
        if (vertex.color == GraphVertex.Color.GRAY) {
            // We re-visited a node which was in process to find this vertex.
            // hence there is a cycle
            return true;
        }

        // Let's mark this GRAY as an indication
        // that we are currently processing this vertex
        vertex.color = GraphVertex.Color.GRAY;

        // Now let's run through all the adjacent of this vertex
        List<GraphVertex> adjacent = adjacencyListArr[vertex.value];
        for (GraphVertex adjacentVertex : adjacent) {
            if (adjacentVertex.color != GraphVertex.Color.BLACK) {
                if (hasCycleFromVertex(adjacentVertex, adjacencyListArr)) {
                    return true;
                }
            }
        }

        // Now we have traversed through this complete path started from vertex.
        // so let's put this into set of black or visited nodes
        vertex.color = GraphVertex.Color.BLACK;
        return false;
    }
}