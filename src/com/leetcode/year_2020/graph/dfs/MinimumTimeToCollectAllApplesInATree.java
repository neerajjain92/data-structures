package com.leetcode.year_2020.graph.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
 * https://www.youtube.com/watch?v=qSBvKlUq0xo&list=PLpIkg8OmuX-I_49pdy1XFY6OcATnxUrrO&index=9&ab_channel=codestorywithMIK
 */
public class MinimumTimeToCollectAllApplesInATree {

    public static void main(String[] args) {

    }

    /*
     * Good Problem: 3 Cases for Intuition
     *
     *                  NODE --> Apple in left of child
     *                 /    --> So we spend 2 seconds to go to child and get back from child
     *                /
     *               /
     *              NODE    ---> same here child has apple
     *             /        ---> So we spend 2 seconds again to go and comeback
     *            /
     *          Apple
     *
     *  So in total we have spend 4 seconds.
     *
     *   Other case
     *
     *                  NODE
     *                 /
     *                /
     *               /
     *              Apple   ----> Child itself has the Apple
     *             /
     *            /
     *          Node
     *
     * Since child has the apple so we check how much time child requires to collect
     * any apple if it has in it's child, + the 2 seconds which parent will spend to come and
     * go back
     *
     * last case
     *
     *                 NODE
     *                 /
     *                /
     *               /
     *              NODE
     *             /
     *            /
     *          Node
     *
     * Important case: No Apple found so don't explore this edge
     */
    List<List<Integer>> adjacencyList;
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        adjacencyList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        return timeToCollectApple(0, -1, hasApple);
    }

    private int timeToCollectApple(int node, int parent, List<Boolean> hasApple) {
        int time = 0;
        for(int child : adjacencyList.get(node)) {
            if (child == parent) continue;
            int timeToCollectApple = timeToCollectApple(child, node, hasApple);
            if (timeToCollectApple > 0 || hasApple.get(child)) {
                time += timeToCollectApple + 2;
            }
        }
        return time;
    }
}
