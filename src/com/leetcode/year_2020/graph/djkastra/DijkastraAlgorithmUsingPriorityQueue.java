package com.leetcode.year_2020.graph.djkastra;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://www.youtube.com/watch?v=V6H1qAeB-l4&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=32
 */
public class DijkastraAlgorithmUsingPriorityQueue {

    public static void main(String[] args) {
        int[][][] adjacencyListWithWeight = new int[][][]{
                {{0, 1, 4}, {0, 2, 4}},
                {{1, 0, 4}, {1, 2, 2}},
                {{2, 0, 4}, {2, 1, 2}, {2, 3, 3}, {2, 4, 1}, {2, 5, 6}},
                {{3, 2, 3}, {3, 5, 2}},
                {{4, 2, 1}, {4, 5, 3}},
                {{5, 3, 2}, {5, 2, 6}, {5, 4, 3}}
        };

        LogUtil.printArray(shortestDistanceViaDijkastra(6, adjacencyListWithWeight, 0));
    }

    private static class Pair {
        int distance;
        int vertex;

        public Pair(int distance, int vertex) {
            this.distance = distance;
            this.vertex = vertex;
        }
    }

    private static int[] shortestDistanceViaDijkastra(int V, int[][][] adjacencyList, int sourceVertex) {
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.distance == b.distance) {
                return a.vertex - b.vertex;
            }
            return a.distance - b.distance;
        });

        Pair sourceNode = new Pair(0, sourceVertex);
        minHeap.add(sourceNode);
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[sourceVertex] = 0;
        while (!minHeap.isEmpty()) {
            Pair polled = minHeap.poll();
            // Traverse Adjacency list
            for (int[] adjacentNode : adjacencyList[polled.vertex]) {
                int destinationNode = adjacentNode[1];
                int adjacentNodeDistance = adjacentNode[2];
                if (distance[destinationNode] > polled.distance + adjacentNodeDistance) {
                    distance[destinationNode] = polled.distance + adjacentNodeDistance;
                    minHeap.add(new Pair(polled.distance + adjacentNodeDistance, destinationNode));
                }
            }
        }
        return distance;
    }
}
