package com.leetcode.year_2020.Greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/task-scheduler/
 * @author neeraj on 23/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TaskScheduler {

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{
                'A', 'A', 'A', 'B', 'B', 'B'
        }, 0));

        System.out.println(leastInterval(new char[]{
                'A', 'A', 'A', 'B', 'B', 'B'
        }, 2));
        System.out.println(leastInterval(new char[]{
                'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'
        }, 2));
        System.out.println(leastInterval(new char[]{
                'A','A','A','A','A','A','B','C','D','E','F','G'
        }, 1));
    }

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> taskFreq = new HashMap<>();
        for (char c : tasks) {
            taskFreq.put(c, taskFreq.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> taskLastProcessedIndex = new HashMap<>();

        // We need a priority queue
        // we want high frequency item on top.
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(Comparator.comparingInt(A -> -taskFreq.get(A)));
        maxHeap.addAll(taskFreq.keySet());

        Stack<Character> stack = new Stack<>();

        int timer = 1;
        while (!maxHeap.isEmpty()) {
            char item = maxHeap.poll();

            // Item was already processed.
            if (taskLastProcessedIndex.containsKey(item)) {
                int diff = timer - taskLastProcessedIndex.get(item);
                if (diff > n) { // diff > allowed difference.
                    addItemToHeapIfRequired(taskFreq, item, maxHeap);
                    taskLastProcessedIndex.put(item, timer);
                } else {
                    while (!maxHeap.isEmpty()) {
                        char itemOnTop = maxHeap.peek();
                        if (taskLastProcessedIndex.containsKey(itemOnTop)) {
                            diff = timer - taskLastProcessedIndex.get(itemOnTop);
                            if (diff > n) {
                                itemOnTop = maxHeap.poll();
                                addItemToHeapIfRequired(taskFreq, itemOnTop, maxHeap);
                                break;
                            } else {
                                stack.push(maxHeap.poll());
                            }
                        } else {
                            itemOnTop = maxHeap.poll();
                            taskLastProcessedIndex.put(itemOnTop, timer);
                            addItemToHeapIfRequired(taskFreq, itemOnTop, maxHeap);
                            break;
                        }
                    }
                    while (!stack.isEmpty()) {
                        maxHeap.add(stack.pop());
                    }
                    maxHeap.add(item);
                }
            } else { // encountering the item for the first time
                taskLastProcessedIndex.put(item, timer);
                addItemToHeapIfRequired(taskFreq, item, maxHeap);
            }
            if (!maxHeap.isEmpty()) {
                timer++;
            }
        }
        return timer;
    }

    private static void addItemToHeapIfRequired(Map<Character, Integer> taskFreq,
                                                char item, PriorityQueue<Character> maxHeap) {
        int itemsFreqLeftToBeProcessed = taskFreq.get(item) - 1;
        if (itemsFreqLeftToBeProcessed > 0) {
            taskFreq.put(item, itemsFreqLeftToBeProcessed);
            maxHeap.add(item);
        }
    }
}
