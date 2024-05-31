package com.leetcode.year_2020.graph.bfs;

import java.util.*;

/**
 * Can be solved via Dijkastra
 * https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
 * 1976. Number of Ways to Arrive at Destination
 */
public class NumberOfWaysToArriveDestinationDijkastra {

    public static void main(String[] args) {
        System.out.println(countPaths(7,
                new int[][]{{0, 6, 7}, {0, 1, 2}, {1, 2, 3},
                        {1, 3, 3}, {6, 3, 3}, {3, 5, 1},
                        {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}}));
    }

    public static int countPaths(int n, int[][] roads) {
        int[] distance = new int[n];
        int mod = (int) (1e9 + 7);
        List<List<Integer>>[] adjancencyListArr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjancencyListArr[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            adjancencyListArr[road[0]].add(List.of(road[1], road[2]));
            adjancencyListArr[road[1]].add(List.of(road[0], road[2])); // BiDirectional
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(0);
        int[] ways = new int[n];
        ways[0] = 1;
        while (!queue.isEmpty()) {
            Integer polled = queue.poll();
            if (polled == n - 1) {
                continue;
            }
            for (List<Integer> adjacent : adjancencyListArr[polled]) {
                Integer neighbour = adjacent.get(0);
                if (distance[neighbour] > distance[polled] + adjacent.get(1)) {
                    distance[neighbour] = distance[polled] + adjacent.get(1);
                    ways[neighbour] = ways[polled];
                    queue.add(neighbour);
                } else if (distance[neighbour] == distance[polled] + adjacent.get(1)) {
                    ways[neighbour] = ((ways[neighbour] + ways[polled]) % mod);
                }
            }
        }
        return (ways[n - 1] % mod);
    }
}
