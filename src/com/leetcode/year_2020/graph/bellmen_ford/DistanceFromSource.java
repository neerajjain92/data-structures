package com.leetcode.year_2020.graph.bellmen_ford;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.List;

import static java.util.List.of;

/**
 * https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
 * https://www.youtube.com/watch?v=0vVofAhAYjc&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=42
 */
public class DistanceFromSource {

    public static void main(String[] args) {
        LogUtil.printArray(bellman_ford(6,
                of(of(3, 2, 6),
                        of(5, 3, 1),
                        of(0, 1, 5),
                        of(1, 5, -3),
                        of(1, 2, -2),
                        of(3, 4, -2),
                        of(2, 4, 3)), 0));
    }

    static int[] bellman_ford(int V, List<List<Integer>> edges, int S) {
        /**
         * Do Relaxation of edges V-1 time
         * so if you have an entry 0-->5 (Cost 2)
         * then we can simply apply  distance[source]+ weight[edge] < distance[destination]
         * then distance[destination] = distance[source]+weight[edge] do this for V-1 times, and you will have the shortest distance from Source to all vertex
         */
        int[] distance = new int[V];
        Arrays.fill(distance, (int) 1e8);
        distance[S] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (List<Integer> edge : edges) {
                if (distance[edge.get(0)] < (int) 1e8) {
                    distance[edge.get(1)] = Math.min(distance[edge.get(1)], distance[edge.get(0)] + edge.get(2));
                }
            }
        }
        // Nth Relaxation to check for negative cycle
        for (List<Integer> edge : edges) {
            if (distance[edge.get(0)] != (int) 1e8 && distance[edge.get(0)] + edge.get(2) < distance[edge.get(1)]) {
                return new int[]{-1};
            }
        }
        return distance;
    }
}
