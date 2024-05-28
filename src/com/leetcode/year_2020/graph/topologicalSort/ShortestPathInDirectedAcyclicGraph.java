package com.leetcode.year_2020.graph.topologicalSort;

/**
 * Shortest Path in Directed Acyclic Graph
 * https://www.youtube.com/watch?v=ZUFQfFaU-8U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=27
 * <p>
 * Input: n = 6, m= 7
 * edges =[[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
 */
public class ShortestPathInDirectedAcyclicGraph {

    public static void main(String[] args) {

    }

    private int[] shortestPathInDAG(int n, int m, int[][] edgesWithWeight, int source) {
        /**
         * Do TOPOLOGICAL SORT
         * with that we will know that the dependent nodes connecting the other node
         *
         *       1 -----[3]------->2
         *     /              /\   \\
         *   [2]             //     [6]
         *   /             //         \\
         *  /             //          \/
         * 0             [2]          3
         * \            //             /\
         *  \          //              //
         *  [1]       //              [1]
         *    \     //              //
         *    \/  //                //
         *    4-----[4]--------5
         *
         * Cost denoted as [cost]:
         *
         * From the above graph, if we do a topo sort, so we get
         * 0 --> 1 --> 4 --> 2 --> 5 --> 3
         *
         * Based on directed edges above, we can confirm that topo sort it correct or not.
         * 5 ---> 3 is an edge and similarly we can validate in topo sort, 5 is coming before 3
         */
    }
}
