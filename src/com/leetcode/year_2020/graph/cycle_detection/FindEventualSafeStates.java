package com.leetcode.year_2020.graph.cycle_detection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-eventual-safe-states/
 *
 * @author neeraj on 31/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindEventualSafeStates {

    public static void main(String[] args) {
        System.out.println(eventualSafeNodes(new int[][]{
                {1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}
        }));

        System.out.println(eventualSafeNodesViaDirectedGraphHasCycle(new int[][]{
                {1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}
        }));
    }

    /**
     * https://www.youtube.com/watch?v=uRbJ1OF9aYM&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=20
     */
    public static List<Integer> eventualSafeNodesViaDirectedGraphHasCycle(int[][] graph) {
        List<Integer> eventualSafeNodes = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        boolean[] pathVisited = new boolean[graph.length];
        boolean[] check = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) { // if already visited, we will not visit again,
                // but that means we might loose other vertex
                // Such as 1 --> 2 --> 3 --> 4[terminalNode]
                // So as soon as we do a DFS(we all the nodes not falling under cycle as checked and to be used as eventualNodes]
                // Otherwise it will be too small since we will be doing DFS from all the nodes
                hasCycle(graph, i, visited, pathVisited, check);
            }
        }

        for (int i = 0; i < check.length; i++) {
            if (check[i]) {
                eventualSafeNodes.add(i);
            }
        }
        return eventualSafeNodes;
    }

    private static boolean hasCycle(int[][] graph, int source, boolean[] visited, boolean[] pathVisited, boolean[] check) {
        visited[source] = true;
        pathVisited[source] = true;
        check[source] = false;

        for (int adjacent : graph[source]) {
            if (pathVisited[adjacent]) {
                return true; // found cycle
            }
            if (!visited[adjacent] && hasCycle(graph, adjacent, visited, pathVisited, check)) {
                return true;
            }
        }
        pathVisited[source] = false; // Explored the path, lets reset
        check[source] = true;
        return false;
    }


    enum Color {
        WHITE, BLACK, GREY
    }

    public static List<Integer> eventualSafeNodes(int[][] input) {
        /**
         * So essentially this question is saying
         * "so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps."
         * which basically means we should not get stuck in a cycle.
         */
        List<Integer> eventualSafeNodes = new ArrayList<>();
        if (input == null || input.length == 0) return eventualSafeNodes;

        Color[] vertexColors = new Color[input.length];
        Arrays.fill(vertexColors, Color.WHITE);

        for (int i = 0; i < vertexColors.length; i++) {
            if (!dfsHasCycle(i, input, vertexColors)) {
                eventualSafeNodes.add(i);
            }
        }
        return eventualSafeNodes;
    }

    private static boolean dfsHasCycle(int vertex, int[][] input, Color[] vertexColors) {
        vertexColors[vertex] = Color.GREY;

        for (int adjacent : input[vertex]) {
            if (vertexColors[adjacent] == Color.GREY) return true; // It's a cycle.
            if (vertexColors[adjacent] == Color.WHITE && dfsHasCycle(adjacent, input, vertexColors)) return true;
        }

        vertexColors[vertex] = Color.BLACK;
        return false;
    }
}
