package com.leetcode.year_2020.Greedy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author neeraj on 23/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TaskScheduler {

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{
                'A','A','A','B','B','B'
        }, 0));
    }

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> taskFreq = new HashMap<>();
        for(char c: tasks) {
            taskFreq.put(c, taskFreq.getOrDefault(c, 0)+1);
        }
        Map<Character, Integer> taskLastProcessedIndex = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        queue.addAll(taskFreq.keySet());

        int currentTaskInProcessCounter = 0;
        Character polledTask = null;
        while(!queue.isEmpty()) {
            polledTask = null;

            // Task Already exist
            // Let's compare when it last executed.
            if(taskLastProcessedIndex.containsKey(queue.peek())) {
                if(currentTaskInProcessCounter - taskLastProcessedIndex
                        .get(queue.peek()) > n) {
                    polledTask = queue.poll();
                }
            } else {
                // First Time Task Encountered.
                polledTask = queue.poll();
                taskLastProcessedIndex.put(polledTask, currentTaskInProcessCounter);
            }

            // Decrement the freq.
            if(polledTask!= null) {
                taskFreq.put(polledTask, taskFreq.get(polledTask) - 1);
                if(taskFreq.get(polledTask) > 0) {
                    queue.add(polledTask);
                }
            }

            if(!queue.isEmpty()) {
                taskLastProcessedIndex.put(polledTask, currentTaskInProcessCounter);
            }
            // Increment the Task Counter regardless of whether task got polled off.
            currentTaskInProcessCounter++;
            System.out.println("currentTaskInProcessCounter value is "+ currentTaskInProcessCounter);
        }
        return currentTaskInProcessCounter;
    }
}
