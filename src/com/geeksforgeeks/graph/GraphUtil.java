package com.geeksforgeeks.graph;

import com.datastructures.array.ArrayUtil;
import com.geeksforgeeks.heap.MinHeap;

import java.util.*;

public class GraphUtil {

    static class Graph {
        int V;
        int[] inDegree; //integer array to store the indegree of nodes
        LinkedList<Integer>[] adjacentListArr;

        public Graph(int totalVertices) {
            V = totalVertices;

            // Define size of array as the no of vertices
            adjacentListArr = new LinkedList[totalVertices];

            // To Store Indegree
            inDegree = new int[totalVertices];

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
        newLine();

        letsDo("Let's Check if Undirected Graph has Cycle using Graph");
        System.out.println(undirectedGraphHasCycleUsingDFS(graph));
        newLine();

        letsDo("Topological Sort");
        graph = new Graph(8);
        addEdge(graph, 1, 3, true);
        addEdge(graph, 2, 3, true);
        addEdge(graph, 2, 5, true);
        addEdge(graph, 3, 4, true);
        addEdge(graph, 4, 6, true);
        addEdge(graph, 5, 6, true);
        addEdge(graph, 6, 7, true);
        doTopologicalSort(graph);


        printAllTopologicalSort(graph);

        letsDo("Count the Number of Island in the Graph");

        int[][] landAndWaterMatrix = {{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        countNoOfIsland(landAndWaterMatrix);
        newLine();


        letsDo("Find No. of Triangles in a Graph");
        int undirectedGraphMatrix[][] = {{0, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 0}
        };
        findNoOfTriangles(undirectedGraphMatrix);
        newLine();


        letsDo("Find Minimum Spanning Tree using Kruskal Algorithm");
        GraphUsingDisjointSetDataStructures<Integer> krushkalGraph = new GraphUsingDisjointSetDataStructures(false);
        krushkalGraph.addEdge(1, 2, 3);
        krushkalGraph.addEdge(1, 4, 1);
        krushkalGraph.addEdge(2, 4, 3);
        krushkalGraph.addEdge(2, 3, 1);
        krushkalGraph.addEdge(4, 3, 1);
        krushkalGraph.addEdge(4, 5, 6);
        krushkalGraph.addEdge(3, 5, 5);
        krushkalGraph.addEdge(3, 6, 4);
        krushkalGraph.addEdge(6, 5, 2);

//        findMinimumSpanningTree(krushkalGraph);

        letsDo("Find Minimum Spanning Tree using Prim's Algorithm");
        // Reusing the same graph of Kruskal Algo.
        findMinimumSpanningTreeUsingPrimsAlgo(krushkalGraph);

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

        // increasing indegree of destination by 1;
        graph.inDegree[destination] = graph.inDegree[destination] + 1;

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

    public static boolean undirectedGraphHasCycleUsingDFS(Graph graph) {
        boolean[] visited = new boolean[graph.V];

        for (int source = 0; source < graph.V; source++) {
            if (!visited[source]) {
                if (undirectedGraphHasCycleUtil(graph, source, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean undirectedGraphHasCycleUtil(Graph graph, int source, boolean[] visited, int parent) {

        // Marking popped vertex as visited
        visited[source] = true;

        for (Integer adjacent : graph.adjacentListArr[source]) {

            // If Adjacent vertex not visited, let's explore this vertex and it's child
            if (!visited[adjacent]) {
                if (undirectedGraphHasCycleUtil(graph, adjacent, visited, source)) {
                    return true;
                }
            }

            // If Adjacent is already visited and not the parent of current Source vertex
            // Then there is a cycle
            else if (adjacent != parent) {
                return true;
            }
        }
        return false;
    }


    public static void doTopologicalSort(Graph graph) {
        boolean[] visited = new boolean[graph.V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i < graph.V; i++) {
            if (!visited[i])
                doDFSForTopologicalSort(graph, i, visited, stack);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }
        newLine();
    }

    private static void doDFSForTopologicalSort(Graph graph, Integer source, boolean[] visited, Stack<Integer> stack) {

        visited[source] = true;

        for (Integer adjacent : graph.adjacentListArr[source]) {
            if (!visited[adjacent]) {
                doDFSForTopologicalSort(graph, adjacent, visited, stack);
            }
        }
        // Explored all Adjacent Nodes of this vertex, So let's push into Stack of Topological Sorted vertices
        stack.push(source);
    }


    private static List<Integer> topologicalSortList = new ArrayList<>();

    private static void printAllTopologicalSort(Graph graph) {
        boolean[] visited = new boolean[graph.V];

        allTopologicalSort(graph, visited);
    }

    private static void allTopologicalSort(Graph graph, boolean[] visited) {

        // To indicate whether all topological are found or not
        boolean flag = false;

        for (int i = 0; i < graph.V; i++) {

            // If indegree is 0 and not yet visited then
            // only choose that vertex
            if (!visited[i] && graph.inDegree[i] == 0) {
                visited[i] = true;
                for (Integer adjacent : graph.adjacentListArr[i]) {
                    graph.inDegree[adjacent]--;
                }

                topologicalSortList.add(i);
                allTopologicalSort(graph, visited);

                visited[i] = false;
                for (Integer adjacent : graph.adjacentListArr[i]) {
                    graph.inDegree[adjacent]++;
                }
                topologicalSortList.remove(topologicalSortList.indexOf(i));
                flag = true;
            }
        }

        // We reach here if all vertices are visited.
        // So we print the solution here
        if (!flag) {
            for (int w = 0; w < graph.V; w++) {
                System.out.print(topologicalSortList.get(w) + " ");
            }
            System.out.print("\n");
        }
    }

    private static int ROWS = 0;
    private static int COLS = 0;

    private static void countNoOfIsland(int[][] landAndWaterMatrix) {

        ROWS = landAndWaterMatrix.length;
        COLS = landAndWaterMatrix[0].length;

        boolean[][] visited = new boolean[ROWS][COLS];
        int noOfIsland = 0;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (landAndWaterMatrix[row][col] == 1 && !visited[row][col]) {
                    DFS(landAndWaterMatrix, row, col, visited);
                    ++noOfIsland;
                }
            }
        }

        System.out.print("Total No of Island is " + noOfIsland);
    }

    /**
     * DFS for No Of Island Problem
     */
    private static void DFS(int[][] landAndWaterMatrix, int row, int col, boolean[][] visited) {

        // Let's calculate the directions of 8 Neighbours
        int[] rowNums = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNums = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Make current vertex as visited
        visited[row][col] = true;

        // Let's recursively traverse the 8 neighbours
        for (int k = 0; k < 8; k++) {
            if (isSafe(landAndWaterMatrix, row + rowNums[k], col + colNums[k], visited)) {
                DFS(landAndWaterMatrix, row + rowNums[k], col + colNums[k], visited);
            }
        }

    }

    // Method to check whether going to this coordinates will not throw any exception
    // and we are moving to unvisited Rocks/Land only
    private static boolean isSafe(int[][] landAndWaterMatrix, int row, int col, boolean[][] visited) {

        return (row >= 0) && (row < ROWS) &&
                (col >= 0) && (col < COLS) &&
                (landAndWaterMatrix[row][col] == 1) && (!visited[row][col]);

    }

    /**
     * Find A^3 Then Find Trace (Sum of Diagonal Elements in A^3) and return Trace/ (3 * 2)
     *
     * @param undirectedGraphMatrix
     */
    private static void findNoOfTriangles(int[][] undirectedGraphMatrix) {
        int V = undirectedGraphMatrix.length;
        // To Store graph^2
        int[][] aux2 = new int[V][V];

        // To Store graph^3
        int[][] aux3 = new int[V][V];

        // Initialising aux matrices
        // with 0
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                aux2[i][j] = aux3[i][j] = 0;
            }
        }

        // aux2 is graph^2 now
        // printMatrix(aux2)
        multiply(undirectedGraphMatrix, undirectedGraphMatrix, aux2, V);

        multiply(undirectedGraphMatrix, aux2, aux3, V);


        System.out.println("No Of Triangles is " + getTrace(aux3, V) / 6);
    }

    public static void multiply(int[][] A, int[][] B, int[][] C, int V) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                C[i][j] = 0;
                for (int k = 0; k < V; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    // Utility function to calculate
    // trace of a matrix (sum of
    // diagonal elements)
    static int getTrace(int graph[][], int V) {
        int trace = 0;

        for (int i = 0; i < V; i++) {
            trace += graph[i][i];
        }
        return trace;
    }


    private static void findMinimumSpanningTree(GraphUsingDisjointSetDataStructures<Integer> krushkalGraph) {
        List<Edge<Integer>> allEdges = krushkalGraph.getAllEdges();
        List<Edge<Integer>> result = new ArrayList<>();
        EdgeComparator edgeComparator = new EdgeComparator();

        // Sort Edges based on increasing weight
        Collections.sort(allEdges, edgeComparator);


        DisjointSet disjointSet = new DisjointSet();

        // Make set for all vertex
        for (Vertex<Integer> vertex : krushkalGraph.getAllVertex()) {
            disjointSet.makeSet(vertex.id);
        }

        for (Edge<Integer> edge : allEdges) {

            Long representative1 = disjointSet.findSet(edge.getVertex1().id);
            Long representative2 = disjointSet.findSet(edge.getVertex2().id);

            // Means both belong to same set, So no need to add them in minimum Spanning tree
            if (representative1 == representative2)
                continue;

            // We can avoid this above representative check
            // Since union operation is taking care of this.
            if (disjointSet.union(edge.getVertex1().id, edge.getVertex2().id)) {
                result.add(edge);
            }
        }

        // Edges in Minimum Spanning Tree
        for (Edge edge : result) {
            System.out.println(edge);
        }
    }

    private static void findMinimumSpanningTreeUsingPrimsAlgo(GraphUsingDisjointSetDataStructures<Integer> primsGraph) {

        // Heap + Map data structure to maintain and extract the minimum distance
        MinHeap<Vertex<Integer>> minHeap = new MinHeap<>();

        // Map to tell from which edge we reached this vertex
        // This will help us to get the edges in the result
        Map<Vertex<Integer>, Edge<Integer>> vertexToEdgeMap = new HashMap<>();


        //stores final result
        List<Edge<Integer>> result = new ArrayList<>();


        // Insert all vertices in the Map with distance/weight as Infinity
        for (Vertex<Integer> vertex : primsGraph.getAllVertex()) {
            minHeap.addNodeToHeap(Integer.MAX_VALUE, vertex);
        }

        // Let's Start Finding the Minimum Spanning Tree
        //1) Start with let's say 1st node

        Vertex<Integer> startVertex = primsGraph.getAllVertex().iterator().next();

        //2) Marking startVertex weight as 0, because to reach start Vertex no distance needs to be travelled
        minHeap.decreaseNodeWeight(startVertex, 0);

        while (!minHeap.empty()) {

            // Extract the minimum Vertex from MinHeap
            Vertex<Integer> current = minHeap.extractMinimumNode();

            //Let's check vertexToEdge map, if this is the first vertex
            // Or we are coming to this vertex from any edge, So add them to the result
            if (vertexToEdgeMap.containsKey(current)) {
                result.add(vertexToEdgeMap.get(current));
            }

            // Now get all the adjacent neighbours of current Vertex
            for (Edge<Integer> adjacentEdge : current.getEdges()) {
                Vertex<Integer> adjacentVertex = getVertexFromEdge(current, adjacentEdge);

                // If the weight in the Map is greater than the actual edge we need to decrease the weight
                // in the MinHeap
                if (minHeap.contains(adjacentVertex) && minHeap.getWeight(adjacentVertex) > adjacentEdge.getWeight()) {
                    minHeap.decreaseNodeWeight(adjacentVertex, adjacentEdge.getWeight());

                    // Add the Vertex to the VertexToEdgeMap
                    vertexToEdgeMap.put(adjacentVertex, adjacentEdge);
                }
            }
        }

        printMinimumSpanningTree(result);

    }

    public static void printMinimumSpanningTree(List<Edge<Integer>> edges) {
        for (Edge<Integer> edge : edges) {
            System.out.println(edge.getVertex1() + "----->" + edge.getVertex2() + " :: Weight (" + edge.getWeight() + ")");
        }
    }

    /**
     * This method basically gives the other end of vertex from an Edge
     * <p>
     * If getVertexFromEdge called from vertex1 then return vertex2 and vice-versa
     *
     * @param current
     * @param adjacentEdge
     * @return
     */
    private static Vertex<Integer> getVertexFromEdge(Vertex<Integer> current, Edge<Integer> adjacentEdge) {
        return adjacentEdge.getVertex1() == current ? adjacentEdge.getVertex2() : adjacentEdge.getVertex1();
    }


}
