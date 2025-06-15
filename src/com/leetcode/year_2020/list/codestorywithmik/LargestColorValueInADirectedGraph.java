package com.leetcode.year_2020.list.codestorywithmik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestColorValueInADirectedGraph {

    List<List<Integer>> adjacencyList;
    String colors;

    public static void main(String[] args) {
        LargestColorValueInADirectedGraph obj = new LargestColorValueInADirectedGraph();
        System.out.println(obj.largestPathValue("abaca", new int[][]{{0, 1}, {0, 2}, {2, 3}, {3, 4}}));
        System.out.println(obj.largestPathValue("abc", new int[][]{{0, 1}, {0, 2}, {2, 3}, {3, 4}}));
    }

    public int largestPathValue(String colors, int[][] edges) {
        int V = colors.length();
        this.colors = colors;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
        }

        // Now do DFS from all
        int maxColor = Integer.MIN_VALUE;
        for (int i = 0; i < V; i++) {
            boolean[] visited = new boolean[V];
            boolean[] inRecursion = new boolean[V];
            Map<Character, Integer> colorCollected = new HashMap<>();
            if (hasCycle(i, visited, inRecursion, colorCollected)) {
                return -1;
            } else {
                for (Integer colorFreq : colorCollected.values()) {
                    maxColor = Math.max(maxColor, colorFreq);
                }
            }
        }
        return maxColor;
    }

    private boolean hasCycle(int src, boolean[] visited, boolean[] inRecursion, Map<Character, Integer> colorCollected) {
        visited[src] = true;
        inRecursion[src] = true;

        colorCollected.put(colors.charAt(src), colorCollected.getOrDefault(colors.charAt(src), 0) + 1);

        for (Integer neighbour : adjacencyList.get(src)) {
            if (visited[neighbour] && inRecursion[neighbour]) return true;
            if (hasCycle(neighbour, visited, inRecursion, colorCollected)) {
                return true;
            }
        }

        inRecursion[src] = false;
        return false;
    }
}
