package com.leetcode.year_2020.graph;

import com.util.LogUtil;

/**
 * https://leetcode.com/problems/course-schedule/
 *
 * @author neeraj on 26/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CourseSchedule {

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{
                {1, 0}
        }));

        System.out.println(canFinish(2, new int[][]{
                {1, 0}, {0, 1}
        }));

        System.out.println(canFinish(3, new int[][]{
                {1, 0}, {0, 2}, {2, 1}
        }));

        System.out.println(canFinish(3, new int[][]{
                {1, 0}, {2, 1}
        }));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        /**
         *  So this problem is a classic example of finding a cycle in graph
         *   if pre-requisite has a cycle there is no way user can complete
         *.  all course.
         **/
        Graph graph = new Graph(numCourses);
        for (int[] requirement : prerequisites) {
            graph.addEdge(requirement[0], requirement[1], true);
        }
        LogUtil.logIt("Can finish " + numCourses + " courses with given prerequisite ?");
        LogUtil.printMultiDimensionArray(prerequisites);
        return !graph.ifGraphHasCycle(graph);
    }
}
