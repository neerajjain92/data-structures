package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.*;

public class BuildAnArrayWithStackOperations {

    public static void main(String[] args) {
        BuildAnArrayWithStackOperations obj = new BuildAnArrayWithStackOperations();
        System.out.println(obj.buildArray(new int[]{1, 3}, 3));
        System.out.println(obj.buildArray(new int[]{1, 2, 3}, 3));
        System.out.println(obj.buildArray(new int[]{1, 2}, 4));
    }

    public List<String> buildArray(int[] target, int n) {
        int targetPointer = 0;
        List<String> operations = new ArrayList<>();

        int nTracker = 1;
        while (targetPointer < target.length) {
            if (nTracker == target[targetPointer]) {
                operations.add("Push");
                targetPointer++;
            } else {
                operations.add("Push");
                operations.add("Pop");
            }
            nTracker++;
        }
        return operations;
    }
}
