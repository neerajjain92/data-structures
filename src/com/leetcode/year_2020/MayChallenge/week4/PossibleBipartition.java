package com.leetcode.year_2020.MayChallenge.week4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/possible-bipartition/
 *
 * @author neeraj on 27/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PossibleBipartition {

    public static void main(String[] args) {
        System.out.println(possibleBipartition(4, new int[][]{
                {1, 2}, {1, 3}, {2, 4}
        }));

        System.out.println(possibleBipartition(3, new int[][]{
                {1, 2}, {1, 3}, {2, 3}
        }));

        System.out.println(possibleBipartition(5, new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}
        }));

        System.out.println(possibleBipartition(50, new int[][]{
                {21, 47}, {4, 41}, {2, 41}, {36, 42}, {32, 45}, {26, 28}, {32, 44}, {5, 41}, {29, 44}, {10, 46}, {1, 6}, {7, 42},
                {46, 49}, {17, 46}, {32, 35}, {11, 48}, {37, 48}, {37, 43}, {8, 41}, {16, 22}, {41, 43}, {11, 27},
                {22, 44}, {22, 28}, {18, 37}, {5, 11}, {18, 46}, {22, 48}, {1, 17}, {2, 32}, {21, 37}, {7, 22},
                {23, 41}, {30, 39}, {6, 41}, {10, 22}, {36, 41}, {22, 25}, {1, 12}, {2, 11}, {45, 46}, {2, 22},
                {1, 38}, {47, 50}, {11, 15}, {2, 37}, {1, 43}, {30, 45}, {4, 32}, {28, 37}, {1, 21}, {23, 37},
                {5, 37}, {29, 40}, {6, 42}, {3, 11}, {40, 42}, {26, 49}, {41, 50}, {13, 41}, {20, 47}, {15, 26},
                {47, 49}, {5, 30}, {4, 42}, {10, 30}, {6, 29}, {20, 42}, {4, 37}, {28, 42}, {1, 16}, {8, 32},
                {16, 29}, {31, 47}, {15, 47}, {1, 5}, {7, 37}, {14, 47}, {30, 48}, {1, 10}, {26, 43}, {15, 46},
                {42, 45}, {18, 42}, {25, 42}, {38, 41}, {32, 39}, {6, 30}, {29, 33}, {34, 37}, {26, 38}, {3, 22},
                {18, 47}, {42, 48}, {22, 49}, {26, 34}, {22, 36}, {29, 36}, {11, 25}, {41, 44}, {6, 46}, {13, 22},
                {11, 16}, {10, 37}, {42, 43}, {12, 32}, {1, 48}, {26, 40}, {22, 50}, {17, 26}, {4, 22}, {11, 14},
                {26, 39}, {7, 11}, {23, 26}, {1, 20}, {32, 33}, {30, 33}, {1, 25}, {2, 30}, {2, 46}, {26, 45},
                {47, 48}, {5, 29}, {3, 37}, {22, 34}, {20, 22}, {9, 47}, {1, 4}, {36, 46}, {30, 49}, {1, 9},
                {3, 26}, {25, 41}, {14, 29}, {1, 35}, {23, 42}, {21, 32}, {24, 46}, {3, 32}, {9, 42}, {33, 37},
                {7, 30}, {29, 45}, {27, 30}, {1, 7}, {33, 42}, {17, 47}, {12, 47}, {19, 41}, {3, 42}, {24, 26},
                {20, 29}, {11, 23}, {22, 40}, {9, 37}, {31, 32}, {23, 46}, {11, 38}, {27, 29}, {17, 37}, {23, 30},
                {14, 42}, {28, 30}, {29, 31}, {1, 8}, {1, 36}, {42, 50}, {21, 41}, {11, 18}, {39, 41}, {32, 34},
                {6, 37}, {30, 38}, {21, 46}, {16, 37}, {22, 24}, {17, 32}, {23, 29}, {3, 30}, {8, 30}, {41, 48},
                {1, 39}, {8, 47}, {30, 44}, {9, 46}, {22, 45}, {7, 26}, {35, 42}, {1, 27}, {17, 30}, {20, 46},
                {18, 29}, {3, 29}, {4, 30}, {3, 46}
        }));
    }

    public static boolean possibleBipartition(int N, int[][] dislikes) {
        /**
         * So this problem clearly specifies that you need to check whether a graph is
         * bipartite or not.
         * More specifically we have to divide the person into 2 group
         * such that those who don't like the other peron shouldn't be in same group
         * Each person may dislike some other people, and they should not go into the same group.
         */
        // Initially all persons will not be in any group.
        PersonGroup[] personGroups = putAllPersonsInUnAssignedGroup(N + 1); // ? why +1 ? since constraint says 1 <= N <= 2000

        // Now we will do the BFS and check if all person can be allocated
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        // We need to prepare Adjacency List arr
        LinkedList<Integer>[] personDislikes = new LinkedList[N + 1];
        for (int i = 0; i < personDislikes.length; i++) {
            personDislikes[i] = new LinkedList<>();
        }
        for (int[] dislike : dislikes) {
            personDislikes[dislike[0]].add(dislike[1]);
            personDislikes[dislike[1]].add(dislike[0]);
        }


        // Since graph can be disjoint we need to do BFS on each and every vertex
        for (int i = 1; i <= N; i++) {
            if (!seen.contains(i)) {
                personGroups[i] = PersonGroup.GROUP_A; // Now let's try traversing all the person and keep 1st person in group A
                queue.add(i);
                seen.add(i);

                while (!queue.isEmpty()) {
                    int polledPerson = queue.poll();


                    LinkedList<Integer> personsThisPolledPersonDisliked = personDislikes[polledPerson];

                    for (Integer dislikedPerson : personsThisPolledPersonDisliked) {
                        PersonGroup groupWhichThisDislikedPersonBelongTo = personGroups[dislikedPerson];

                        if (groupWhichThisDislikedPersonBelongTo == PersonGroup.GROUP_UNASSIGNED) {
                            personGroups[dislikedPerson] = getOppositeGroup(personGroups[polledPerson]);
                        } else if (groupWhichThisDislikedPersonBelongTo == personGroups[polledPerson]) {
                            // We have a situation where we can't divide dislikedPerson who dislike each other
                            // in different groups.
                            return false;
                        }

                        if (!seen.contains(dislikedPerson)) {
                            queue.add(dislikedPerson);
                            seen.add(dislikedPerson);
                        }
                    }

                }
            }
        }
        return true;
    }

    private static PersonGroup getOppositeGroup(PersonGroup personGroup) {
        return personGroup == PersonGroup.GROUP_A ? PersonGroup.GROUP_B : PersonGroup.GROUP_A;
    }

    private static PersonGroup[] putAllPersonsInUnAssignedGroup(int n) {
        PersonGroup[] personGroups = new PersonGroup[n];
        for (int i = 0; i < n; i++) {
            personGroups[i] = PersonGroup.GROUP_UNASSIGNED;
        }
        return personGroups;
    }

    static enum PersonGroup {GROUP_A, GROUP_B, GROUP_UNASSIGNED}
}
