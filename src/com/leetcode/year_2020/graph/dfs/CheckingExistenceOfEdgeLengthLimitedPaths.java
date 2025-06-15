package com.leetcode.year_2020.graph.dfs;

import java.util.*;

public class CheckingExistenceOfEdgeLengthLimitedPaths {

    List<List<int[]>> adjacencyList;
    /**
     * First sort queries based on Min MaxAllowedDistance
     * because any edge which contribute to the min MaxAllowedDistance will for sure
     * be allowed for the any bigger maxAllowedDistance
     * <p>
     * Then once we sort them we should also sort edgeList based on their distance
     * so for all the edges included in the first min MaxAllowedDistance will be
     * included in any other bigger MaxAllowedDistance and we need not recalculate
     * <p>
     * Lastly to check if you can reach from node 1 to node 2, simply use DisjointSet and union
     * to find if they belong to same group, because we sould have already added edge
     * by union if the edge
     */
    private int[] parents;

    public static void main(String[] args) {
        CheckingExistenceOfEdgeLengthLimitedPaths obj = new CheckingExistenceOfEdgeLengthLimitedPaths();
        boolean[] result = obj.distanceLimitedPathsExist(3, new int[][]{{0, 1, 2}, {1, 2, 4}, {2, 0, 8}, {1, 0, 16}},
                new int[][]{{0, 1, 2}, {0, 2, 5}});
        System.out.println(result);
    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        // Sort queries
        int[][] sortedQueriesWithIndex = sortQueries(queries);
        boolean[] result = new boolean[queries.length];

        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));

        // Now both are sorted
        int prevExploredEdges = -1;

        for (int[] query : sortedQueriesWithIndex) {
            for (int i = prevExploredEdges + 1; i < n; i++) {
                if (edgeList[i][2] < query[2]) {
                    union(edgeList[i][0], edgeList[i][1]);
                    prevExploredEdges = i;
                } else {
                    break;
                }
            }
            if (find(query[0]) == find(query[1])) {
                result[query[3]] = true;
            }
        }
        return result;
    }

    private void union(int u, int v) {
        int parent_u = find(u);
        int parent_v = find(v);
        if (parent_u == parent_v) {
            return;
        }
        parents[parent_u] = parent_v;
    }

    private int find(int node) {
        if (node == parents[node]) {
            return node;
        }
        parents[node] = find(parents[node]);
        return parents[node];
    }

    private int[][] sortQueries(int[][] queries) {
        int[][] sortedQueries = new int[queries.length][queries[0].length];
        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[2]));
        return sortedQueries;
    }


    /**
     * This will give TLE
     */
    public boolean[] distanceLimitedPathsExistWithDFS(int n, int[][] edgeList, int[][] queries) {
        // Key will be node1-node2
        Map<String, Integer> minDistanceFromNodeToAnotherNode = new HashMap<>();
        for (int[] edge : edgeList) {
            String key = Math.min(edge[0], edge[1]) + "-" + Math.max(edge[0], edge[1]);
            if (minDistanceFromNodeToAnotherNode.containsKey(key)) {
                minDistanceFromNodeToAnotherNode.compute(key, (k, minDis) -> Math.min(minDis, edge[2]));
            } else {
                minDistanceFromNodeToAnotherNode.put(key, edge[2]);
            }
        }
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edgeList) {
            String key = Math.min(edge[0], edge[1]) + "-" + Math.max(edge[0], edge[1]);
            int minDis = minDistanceFromNodeToAnotherNode.get(key);
            if (minDis == edge[2]) {
                // then only add the node
                adjacencyList.get(edge[0]).add(new int[]{edge[1], edge[2]});
                adjacencyList.get(edge[1]).add(new int[]{edge[0], edge[2]});
            }
        }

        boolean[] result = new boolean[queries.length];
        Arrays.fill(result, false);
        int count = 0;
        for (int[] query : queries) {
            boolean[] visited = new boolean[n];
            if (doPathExist(query[0], query[1], query[2], visited)) {
                result[count] = true;
            }
            count++;
        }
        return result;
    }

    private boolean doPathExist(int src, int dst, int max, boolean[] visited) {
        visited[src] = true;

        if (src == dst) {
            return true;
        }
        for (int[] neighbour : adjacencyList.get(src)) {
            if (!visited[neighbour[0]] && neighbour[1] < max) {
                if (doPathExist(neighbour[0], dst, max, visited)) {
                    return true;
                }
            }
        }


        return false;
    }
}
