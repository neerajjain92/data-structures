package com.leetcode.problems.medium;

import java.util.*;

/**
 * @author neeraj on 10/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KeysAndRooms_841 {

    public static void main(String[] args) {
        solveProblem(new int[][]
                {{4}, {3}, {}, {2, 5, 7}, {1}, {}, {8, 9}, {}, {}, {6}}
        );

        solveProblem(new int[][]{
                {1},{2},{3},{}
        });
    }

    private static void solveProblem(int[][] inputs) {
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> keys;
        for (int i = 0; i < inputs.length; i++) {
            keys = new ArrayList<>();
            for (int j = 0; j < inputs[i].length; j++) {
                keys.add(inputs[i][j]);
            }
            rooms.add(keys);
        }
        System.out.println("Can visit all rooms ? " + canVisitAllRooms(rooms));
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // To avoid visiting the room already visited.
        Set<Integer> visitedRooms = new HashSet<>();
        visitedRooms.add(0);

        Stack<Integer> dfs = new Stack<>();
        dfs.add(0);
        Integer polledKey;

        while (!dfs.isEmpty()) {

            List<Integer> polledKeys = rooms.get(dfs.pop());

            for(Integer key: polledKeys) {
                if(!visitedRooms.contains(key)) {
                    visitedRooms.add(key);
                    dfs.add(key);
                }
            }
        }
        return visitedRooms.size() == rooms.size();
    }
}
