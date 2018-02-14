package com.geeksforgeeks.graph;

import com.datastructures.array.ArrayUtil;

import java.util.*;

public class GraphUtil {

    static class Graph {
        int V;
        LinkedList<Integer>[] adjacentListArr;

        public Graph(int totalVertices) {
            V = totalVertices;

            // Define size of array as the no of vertices
            adjacentListArr = new LinkedList[totalVertices];

            // Creating New List for each vertex
            // Such that all adjacent nodes can be stored
            for (int i = 0; i < adjacentListArr.length; i++) {
                adjacentListArr[i] = new LinkedList<>();
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        addEdge(graph, 0, 1, false);
        addEdge(graph, 0, 4, false);
        addEdge(graph, 1, 2, false);
        addEdge(graph, 1, 3, false);
        addEdge(graph, 1, 4, false);
        addEdge(graph, 2, 3, false);
        addEdge(graph, 3, 4, false);

        printGraph(graph);

        letsDo("BREADTH FIRST TRAVERSAL OF GRAPH");
        graph = new Graph(4);

        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 3, 3, true);

        BreadthFirstTraversal(graph, 2);
        newLine();

        letsDo("DEPTH FIRST TRAVERSALS");
        DepthFirstTraversal(graph, 2);
        newLine();

        letsDo("Recursive DEPTH FIRST TRAVERSAL");
        recursiveDFS(graph, 2);
        newLine();

        letsDo("Recursive DFT again");
        graph = new Graph(5);
        addEdge(graph, 1, 0, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 2, 1, true);
        addEdge(graph, 0, 3, true);
        addEdge(graph, 3, 4, true);
        recursiveDFS(graph, 0);
        newLine();

        letsDo("Find Out WTF is Strongly Connected Component");
        printStronglyConnectedComponents(graph);
        newLine();

        letsDo("Find Mother Vertex");
        findMotherVertex(graph);
        newLine();

        letsDo("Find Transitive Closure using Floyd Warshall Algorithm");
        int[][] distanceMatrix = {
                {0, 3, 6, 15},
                {INF, 0, -2, INF},
                {INF, INF, 0, 2},
                {1, INF, INF, 0}
        };

        floydWarshallAllPairShortestPathAlgo(distanceMatrix);


        letsDo("Find Transitive Closure using DFS which is O(V^2) algo better than Flyod Warshall");
        graph = new Graph(4);

        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 0, true);
        addEdge(graph, 2, 3, true);

        transitiveClosureUsingDFS(graph);
        newLine();


        letsDo("Let's Check if Directed Graph Has Cycle");
        graph = new Graph(7);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 1, 3, true);

        addEdge(graph, 4, 1, true);
        addEdge(graph, 4, 5, true);
        addEdge(graph, 5, 6, true);
        addEdge(graph, 6, 4, true);

        System.out.println(directedGraphHasCycle(graph));

        letsDo("Let's Check if Undirected Graph has Cycle");
        // Even though we are doing this check for Undirected graph
        // But we still pass isDirectedGraph as true in addEdge method
        // Because here we are interested in getting unique edges
        // So A-->B will suffice we don't need B-->A
        // Hope I remember when i read it 2-3 months/year later.
        graph = new Graph(4);
        addEdge(graph, 0, 1, true);
        addEdge(graph, 0, 2, true);
        addEdge(graph, 1, 2, true);
        addEdge(graph, 2, 3, true);

        System.out.println(undirectedGraphHasCycle(graph));
    }

    public static void letsDo(String task) {
        System.out.println("==================" + task + "======================");
    }

    public static void newLine() {
        System.out.println();
    }


    public static void addEdge(Graph graph, int source, int destination, Boolean isDirectedGraph) {
        // Adding an edge from Source to Destination
        graph.adjacentListArr[source].add(destination);


        if (!isDirectedGraph)
            //Since It's an undirected graph so we have to have edge from both source-destination and vice versa
            graph.adjacentListArr[destination].add(source);
    }

    public static void printGraph(Graph graph) {
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArr;
        for (int i = 0; i < adjacentListArr.length; i++) {
            System.out.println("Adjacency List of " + i);
            System.out.println(adjacentListArr[i]);
        }
    }

    public static void BreadthFirstTraversal(Graph graph, int source) {
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArr;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.V];
        queue.add(source);
        visited[source] = true;
        Integer temp;

        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp + "\t");

            LinkedList<Integer> adjacentList = adjacentListArr[temp];

            for (Integer i : adjacentList) {
                if (visited[i] == false) {
                    visited[i] = true;
                    queue.add(i);
                }
            }

        }
    }

    public static void DepthFirstTraversal(Graph graph, int source) {
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArr;
        boolean[] visited = new boolean[graph.V];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        Integer popped;

        while (!stack.isEmpty()) {
            popped = stack.pop();

            if (!visited[popped]) {
                System.out.print(popped + "\t");
                visited[popped] = true;
            }
            LinkedList<Integer> adjacentVertex = adjacentListArr[popped];

            for (Integer i : adjacentVertex) {
                if (!visited[i])
                    stack.push(i);
            }
        }
    }

    public static void recursiveDFS(Graph graph, int source) {
        LinkedList<Integer>[] adjacentListArr = graph.adjacentListArr;
        boolean[] visited = new boolean[graph.V];
        recursiveDFSUtil(adjacentListArr, source, visited);
    }

    public static void recursiveDFSUtil(LinkedList<Integer>[] adjacentListArr, int source, boolean[] visited) {
        visited[source] = true;
        System.out.print(source + "\t");

        LinkedList<Integer> adjacentList = adjacentListArr[source];
        for (Integer i : adjacentList) {
            if (!visited[i])
                recursiveDFSUtil(adjacentListArr, i, visited);
        }
    }

    public static void printStronglyConnectedComponents(Graph graph) {
        boolean[] visited = new boolean[graph.V];

        Stack<Integer> vertexOrderedByFinishTimeInDecreasingOrder = new Stack<>();

        // Pass 1 Do DFS for all vertices
        for (int source = 0; source < graph.V; source++) {
            if (!visited[source]) {
                recursiveDFSUtilForSCC(graph.adjacentListArr, source, visited, vertexOrderedByFinishTimeInDecreasingOrder);
            }
        }
        newLine();
        // Before Pass 2, we have to change the direction of each edge, into reverse direction
        graph = reverseGraphEdges(graph);


        // Now 2nd Pass While Stack is not empty
        // Clear your visited array

        visited = new boolean[graph.V];
        System.out.println("Strongly Connected Components are ");
        while (!vertexOrderedByFinishTimeInDecreasingOrder.isEmpty()) {
            Integer popped = vertexOrderedByFinishTimeInDecreasingOrder.pop();
            if (!visited[popped]) {
                recursiveDFSUtil(graph.adjacentListArr, popped, visited);
                newLine();
            }
        }
    }

    private static Graph reverseGraphEdges(Graph graph) {
        Graph outputGraph = new Graph(graph.V);
        for (int i = 0; i < graph.V; i++) {
            LinkedList<Integer> adjacentList = graph.adjacentListArr[i];
            for (Integer vertex : adjacentList) {
                outputGraph.adjacentListArr[vertex].add(i);
            }
        }
        return outputGraph;
    }

    public static void recursiveDFSUtilForSCC(LinkedList<Integer>[] adjacentListArr, int source, boolean[] visited, Stack<Integer> stack) {
        visited[source] = true;
        LinkedList<Integer> adjacentList = adjacentListArr[source];
        for (Integer i : adjacentList) {
            if (!visited[i])
                recursiveDFSUtilForSCC(adjacentListArr, i, visited, stack);
        }
        // After Successfully explored the child of this vertex, Store it into the stack
        stack.push(source);
    }

    public static void findMotherVertex(Graph graph) {
        boolean[] visited = new boolean[graph.V];
        int lastVisitedNode = -1;
        for (int i = 0; i < graph.V; i++) {
            if (!visited[i]) {
                doDFSForMotherVertexProblem(graph.adjacentListArr, i, visited);
                lastVisitedNode = i;
            }
        }

        // Now we have last Visited Node, Let's do the DFS now.
        visited = new boolean[graph.V];

        doDFSForMotherVertexProblem(graph.adjacentListArr, lastVisitedNode, visited);

        // Now Traverse the visited array and check if all of them are visited
        for (boolean value : visited) {
            if (!value) {
                System.out.println("No Mother Vertex Present");
                return;
            }
        }
        System.out.println(lastVisitedNode + " is the mother vertex");
    }

    public static void doDFSForMotherVertexProblem(LinkedList<Integer>[] adjacentListArr, int source, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        Integer popped;

        while (!stack.isEmpty()) {
            popped = stack.pop();

            if (!visited[popped]) {
                visited[popped] = true;
            }

            LinkedList<Integer> adjacentNodes = adjacentListArr[popped];
            for (Integer node : adjacentNodes) {
                if (!visited[node]) {
                    stack.push(node);
                }
            }
        }
    }

    /**
     * Tansitive Closure of A Graph, using Floyd Warshall Algorithm
     * INF means there is no path between nodes and it will take infinity distance to reach to that Node
     *
     * @param distanceMatrix
     */
    private static int INF = 1000000;

    public static void floydWarshallAllPairShortestPathAlgo(int[][] distanceMatrix) {
        int[][] distance = new int[distanceMatrix.length][distanceMatrix.length];
        int[][] path = new int[distanceMatrix.length][distanceMatrix.length];


        // Let's populate distance and path matrix
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                distance[i][j] = distanceMatrix[i][j];

                if (distanceMatrix[i][j] != INF && i != j) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
            }
        }

        // Let's Populate the distance and Path matrix with shortest path
        for (int k = 0; k < distance.length; k++) {
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance.length; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        letsDo("Before Finding Shortest Path");
        ArrayUtil.print2DArray(distanceMatrix, distanceMatrix.length, distanceMatrix.length);
        newLine();
        letsDo("After Finding Shortest Path");
        ArrayUtil.print2DArray(distance, distance.length, distance.length);
        newLine();
    }

    private static int[][] transitiveClosure;

    private static void transitiveClosureUsingDFS(Graph graph) {
        transitiveClosure = new int[graph.V][graph.V];

        for (int i = 0; i < graph.V; i++) {
            doDFSForTransitiveClosure(graph, i, i);
        }

        ArrayUtil.print2DArray(transitiveClosure, graph.V, graph.V);
    }

    private static void doDFSForTransitiveClosure(Graph graph, int source, int destination) {
        transitiveClosure[source][destination] = 1;

        for (Integer adjacentNode : graph.adjacentListArr[destination]) {
            if (transitiveClosure[source][adjacentNode] == 0) {
                doDFSForTransitiveClosure(graph, source, adjacentNode);
            }
        }
    }

    private static Boolean directedGraphHasCycle(Graph graph) {
        // Set of Unvisited Vertex
        Set<Integer> whiteSet = new HashSet<>();

        // Set of currently Visiting Vertex
        Set<Integer> graySet = new HashSet<>();

        // Set of completely visited Vertex
        Set<Integer> blackSet = new HashSet<>();

        // Let's put all unvisited vertex in whiteSet
        for (int i = 1; i < graph.V; i++) {
            whiteSet.add(i);
        }

        while (!whiteSet.isEmpty()) {
            Integer current = whiteSet.iterator().next();

            if (dfsForIdentifyingGraphCycle(graph, current, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfsForIdentifyingGraphCycle(Graph graph, Integer current, Set<Integer> whiteSet, Set<Integer> graySet, Set<Integer> blackSet) {

        // Move Vertex from White Set(unVisited) to Gray Set(currently Visiting);
        moveVertex(current, whiteSet, graySet);

        LinkedList<Integer> adjacentVertices = graph.adjacentListArr[current];
        for (Integer adjacent : adjacentVertices) {

            if (blackSet.contains(adjacent)) { // Already visited node So skip
                continue;
            }

            if (graySet.contains(adjacent)) { // Found a Node whose child is being explored, i.e Cycle
                return true;
            }

            if (dfsForIdentifyingGraphCycle(graph, adjacent, whiteSet, graySet, blackSet)) {
                return true;
            }
        }

        // Current Node processing is done, Let's move it to Black Node
        moveVertex(current, graySet, blackSet);

        return false;
    }

    private static void moveVertex(Integer vertexToBeMoved, Set<Integer> sourceSet, Set<Integer> destinationSet) {
        sourceSet.remove(vertexToBeMoved);
        destinationSet.add(vertexToBeMoved);
    }

    private static boolean undirectedGraphHasCycle(Graph graph) {
        DisjointSet disjointSet = new DisjointSet();

        for (int i = 0; i < graph.V; i++)
            disjointSet.makeSet(i);

        for (int source = 0; source < graph.V; source++) {

            LinkedList<Integer> adjacentList = graph.adjacentListArr[source];

            for (Integer adjacentVertex : adjacentList) {
                long parent1 = disjointSet.findSet(source);
                long parent2 = disjointSet.findSet(adjacentVertex);

                if (parent1 == parent2) {
                    return true;
                }
                disjointSet.union(source, adjacentVertex);
            }
        }
        return false;
    }
}
