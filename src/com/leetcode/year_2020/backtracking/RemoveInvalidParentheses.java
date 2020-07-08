package com.leetcode.year_2020.backtracking;

import java.util.*;

/**
 * @author neeraj on 08/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
    }

    public static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        /**
         * We will do the BFS to find minimum invalid parentheses to remove
         */
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        Boolean foundAValidString = false;
        String current = "";

        while (!queue.isEmpty()) {
            current = queue.poll();

            if (isValid(current)) {
                result.add(current);
                foundAValidString = true;
            }
            if (foundAValidString) continue;

            // Not a valid String, so we have to delete some invalid bracket
            for (int i = 0; i < current.length(); i++) {
                // we can only remove opening or closing bracket not alphabets
                if (current.charAt(i) != '(' && current.charAt(i) != ')') continue;

                String temp = current.substring(0, i) + current.substring(i + 1);
                if (!visited.contains(temp)) {
                    visited.add(temp);
                    queue.add(temp);
                }
            }
        }
        return result;
    }

    private static boolean isValid(String s) {
        int counter = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') counter++;
            if (c == ')') {
                // Trick catch if you have already reached to a 0 state then a closing bracket is coming before opening bracket
                // then that's invalid state.
                if (counter == 0) return false;
                counter--;
            }
        }
        return counter == 0;
    }
}
