package com.leetcode.year_2020.Greedy;

import java.util.*;

/**
 * @author neeraj on 25/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReducedTheArraySizeToHalf {

    public static void main(String[] args) {
        System.out.println(minSetSize(new int[]{
                3, 3, 3, 3, 5, 5, 5, 2, 2, 7
        }));
        System.out.println(minSetSize(new int[]{
                7, 7, 7, 7, 7, 7
        }));
        System.out.println(minSetSize(new int[]{
                1, 9
        }));
        System.out.println(minSetSize(new int[]{
                1000, 1000, 3, 7
        }));
        System.out.println(minSetSize(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        }));
        System.out.println(minSetSize(new int[]{
                7,61,65,48,46,67,98,72,40,9,13,11,8,6,84,66,47,45,68,50,75,27,43,21,37,39,46,57,86,37,63,25,31,65,83,28,32,88,16,28,17,29,96,11,64,19,60,95,61,63,6,67,34,9,10,44,55,58,80,27,1,23,35,65,7,3,54,100,33,46,1,47,26,43,8,45,73,93,44,91,50,58,19,65,30,54,86,65,53,26,8,20,49,42,10,88,87,36,100,5,24,85,16,5,95,90,57,27,11,84,5,60,57,39,95,19,10,25,98,27,20,41,37,8,91,64,97,80,17,25,5,29,86,28,54,18,83,91,89,53,23,32,1,5,87,88,5,51,15,14,14,27,85,22,2,77,83,41,67,1,95,39,48,10,17,4,10,73,63,25,55,59,75,22,53,15,73,50,62,80,19,76,28,78,11,57,2,3,93,20,68,53,41,2,46,11,100,75,97,61
        }));
    }

    public static int minSetSize(int[] arr) {
        Map<Integer, Integer> itemFreq = new HashMap<>();
        for (int i : arr) {
            itemFreq.put(i, itemFreq.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> maxHeapByFreq = new PriorityQueue<>(Collections.reverseOrder());
        maxHeapByFreq.addAll(itemFreq.values());
        int count = 0;
        int result = 0;
        while (true) {
            count += maxHeapByFreq.poll();
            result++;
            if (count >= arr.length/2) break;
        }
        return result;
    }
}
