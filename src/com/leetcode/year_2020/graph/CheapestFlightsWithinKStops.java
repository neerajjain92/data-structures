package com.leetcode.year_2020.graph;

import java.util.*;

/**
 * @author neeraj on 14/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        System.out.println(findCheapestPrice(3, new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        }, 0, 2, 1));
        System.out.println(findCheapestPriceV1(3, new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        }, 0, 2, 1));
        System.out.println(findCheapestPrice(3, new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        }, 0, 2, 0));

        System.out.println(findCheapestPriceV1(3, new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        }, 0, 2, 0));

        System.out.println(findCheapestPrice(5, new int[][]{
                {4, 1, 1},
                {1, 2, 3},
                {0, 3, 2},
                {0, 4, 10},
                {1, 4, 3}
        }, 2, 1, 1));

        System.out.println(findCheapestPriceV1(5, new int[][]{
                {4, 1, 1},
                {1, 2, 3},
                {0, 3, 2},
                {0, 4, 10},
                {1, 4, 3}
        }, 2, 1, 1));

        System.out.println(findCheapestPrice(17, new int[][]{
                {0, 12, 28}, {5, 6, 39}, {8, 6, 59}, {13, 15, 7}, {13, 12, 38}, {10, 12, 35}, {15, 3, 23}, {7, 11, 26}, {9, 4, 65}, {10, 2, 38}, {4, 7, 7}, {14, 15, 31}, {2, 12, 44}, {8, 10, 34}, {13, 6, 29}, {5, 14, 89}, {11, 16, 13}, {7, 3, 46}, {10, 15, 19}, {12, 4, 58}, {13, 16, 11}, {16, 4, 76}, {2, 0, 12}, {15, 0, 22}, {16, 12, 13}, {7, 1, 29}, {7, 14, 100}, {16, 1, 14}, {9, 6, 74}, {11, 1, 73}, {2, 11, 60}, {10, 11, 85}, {2, 5, 49}, {3, 4, 17}, {4, 9, 77}, {16, 3, 47}, {15, 6, 78}, {14, 1, 90}, {10, 5, 95}, {1, 11, 30}, {11, 0, 37}, {10, 4, 86}, {0, 8, 57}, {6, 14, 68}, {16, 8, 3}, {13, 0, 65}, {2, 13, 6}, {5, 13, 5}, {8, 11, 31}, {6, 10, 20}, {6, 2, 33}, {9, 1, 3}, {14, 9, 58}, {12, 3, 19}, {11, 2, 74}, {12, 14, 48}, {16, 11, 100}, {3, 12, 38}, {12, 13, 77}, {10, 9, 99}, {15, 13, 98}, {15, 12, 71}, {1, 4, 28}, {7, 0, 83}, {3, 5, 100}, {8, 9, 14}, {15, 11, 57}, {3, 6, 65}, {1, 3, 45}, {14, 7, 74}, {2, 10, 39}, {4, 8, 73}, {13, 5, 77}, {10, 0, 43}, {12, 9, 92}, {8, 2, 26}, {1, 7, 7}, {9, 12, 10}, {13, 11, 64}, {8, 13, 80}, {6, 12, 74}, {9, 7, 35}, {0, 15, 48}, {3, 7, 87}, {16, 9, 42}, {5, 16, 64}, {4, 5, 65}, {15, 14, 70}, {12, 0, 13}, {16, 14, 52}, {3, 10, 80}, {14, 11, 85}, {15, 2, 77}, {4, 11, 19}, {2, 7, 49}, {10, 7, 78}, {14, 6, 84}, {13, 7, 50}, {11, 6, 75}, {5, 10, 46}, {13, 8, 43}, {9, 10, 49}, {7, 12, 64}, {0, 10, 76}, {5, 9, 77}, {8, 3, 28}, {11, 9, 28}, {12, 16, 87}, {12, 6, 24}, {9, 15, 94}, {5, 7, 77}, {4, 10, 18}, {7, 2, 11}, {9, 5, 41}
        }, 13, 4, 13));

        System.out.println(findCheapestPriceV1(17, new int[][]{
                {0, 12, 28}, {5, 6, 39}, {8, 6, 59}, {13, 15, 7}, {13, 12, 38}, {10, 12, 35}, {15, 3, 23}, {7, 11, 26}, {9, 4, 65}, {10, 2, 38}, {4, 7, 7}, {14, 15, 31}, {2, 12, 44}, {8, 10, 34}, {13, 6, 29}, {5, 14, 89}, {11, 16, 13}, {7, 3, 46}, {10, 15, 19}, {12, 4, 58}, {13, 16, 11}, {16, 4, 76}, {2, 0, 12}, {15, 0, 22}, {16, 12, 13}, {7, 1, 29}, {7, 14, 100}, {16, 1, 14}, {9, 6, 74}, {11, 1, 73}, {2, 11, 60}, {10, 11, 85}, {2, 5, 49}, {3, 4, 17}, {4, 9, 77}, {16, 3, 47}, {15, 6, 78}, {14, 1, 90}, {10, 5, 95}, {1, 11, 30}, {11, 0, 37}, {10, 4, 86}, {0, 8, 57}, {6, 14, 68}, {16, 8, 3}, {13, 0, 65}, {2, 13, 6}, {5, 13, 5}, {8, 11, 31}, {6, 10, 20}, {6, 2, 33}, {9, 1, 3}, {14, 9, 58}, {12, 3, 19}, {11, 2, 74}, {12, 14, 48}, {16, 11, 100}, {3, 12, 38}, {12, 13, 77}, {10, 9, 99}, {15, 13, 98}, {15, 12, 71}, {1, 4, 28}, {7, 0, 83}, {3, 5, 100}, {8, 9, 14}, {15, 11, 57}, {3, 6, 65}, {1, 3, 45}, {14, 7, 74}, {2, 10, 39}, {4, 8, 73}, {13, 5, 77}, {10, 0, 43}, {12, 9, 92}, {8, 2, 26}, {1, 7, 7}, {9, 12, 10}, {13, 11, 64}, {8, 13, 80}, {6, 12, 74}, {9, 7, 35}, {0, 15, 48}, {3, 7, 87}, {16, 9, 42}, {5, 16, 64}, {4, 5, 65}, {15, 14, 70}, {12, 0, 13}, {16, 14, 52}, {3, 10, 80}, {14, 11, 85}, {15, 2, 77}, {4, 11, 19}, {2, 7, 49}, {10, 7, 78}, {14, 6, 84}, {13, 7, 50}, {11, 6, 75}, {5, 10, 46}, {13, 8, 43}, {9, 10, 49}, {7, 12, 64}, {0, 10, 76}, {5, 9, 77}, {8, 3, 28}, {11, 9, 28}, {12, 16, 87}, {12, 6, 24}, {9, 15, 94}, {5, 7, 77}, {4, 10, 18}, {7, 2, 11}, {9, 5, 41}
        }, 13, 4, 13));
    }

    static class Pair {
        int dst;
        int cost;
        int k;

        public Pair(int dst, int cost, int k) {
            this.dst = dst;
            this.cost = cost;
            this.k = k;
        }
    }

    /**
     * 30th May 2024 ::: Solve Via Dijkastra
     */
    public static int findCheapestPriceV1(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<List<Integer>>> adjacencyList = new HashMap<>();
        for (int[] flightEntry : flights) {
            adjacencyList.putIfAbsent(flightEntry[0], new ArrayList<>());
            adjacencyList.get(flightEntry[0]).add(List.of(flightEntry[1], flightEntry[2]));
        }
        // it will store [cost,stop,k]
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        minHeap.add(new Pair(src, 0, k + 1));

        while (!minHeap.isEmpty()) {
            Pair polled = minHeap.poll();
            if (polled.dst == dst) {
                return polled.cost;
            }
            if (adjacencyList.containsKey(polled.dst)) {
                for (List<Integer> adjacent : adjacencyList.get(polled.dst)) {
                    if (polled.k > 0) {
                        minHeap.add(new Pair(adjacent.get(0), polled.cost + adjacent.get(1), polled.k - 1));
                    }
                }
            }
        }
        return -1;
    }


    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<List<Integer>>> graph = new HashMap<>();

        for (int[] flight : flights) {
            graph.putIfAbsent(flight[0], new ArrayList<>());
            graph.get(flight[0]).add(Arrays.asList(flight[1], flight[2]));
        }

        minPrice = Integer.MAX_VALUE;
        findCheapestPrice(src, dst, graph, K + 1, 0);
        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    }

    static int minPrice = 0;

    public static void findCheapestPrice(int src, int dst, Map<Integer, List<List<Integer>>> graph,
                                         int K, int price) {
        if (K < 0) return;
        if (src == dst) {
            minPrice = price;
            return;
        }

        if (graph.containsKey(src)) {
            for (List<Integer> adjacentStop : graph.get(src)) {
                if (price + adjacentStop.get(1) > minPrice)
                    continue; // We know if we choose this path there is no way we can achieve cheaper-price than before
                findCheapestPrice(adjacentStop.get(0), dst, graph, K - 1, price + adjacentStop.get(1));
            }
        }
    }
}
