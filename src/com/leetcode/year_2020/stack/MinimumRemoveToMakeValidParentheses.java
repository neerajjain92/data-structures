package com.leetcode.year_2020.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author neeraj on 23/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumRemoveToMakeValidParentheses {

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValidViaPair("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
        System.out.println(minRemoveToMakeValidViaPair("a)b(c)d"));
        System.out.println(minRemoveToMakeValid("))(("));
        System.out.println(minRemoveToMakeValidViaPair("))(("));
        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));
        System.out.println(minRemoveToMakeValidViaPair("(a(b(c)d)"));
    }

    static class Pair {
        char c;
        int index;

        public Pair(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }

    private static String minRemoveToMakeValidViaPair(String s) {
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(new Pair(')', i));
            } else if (s.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peek().c != '-') {
                    stack.pop();
                } else {
                    stack.push(new Pair('-', i));
                }
            }
        }
        Set<Integer> indexToSkip = new HashSet<>();
        while (!stack.isEmpty()) {
            indexToSkip.add(stack.pop().index);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexToSkip.contains(i)) {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] input = s.toCharArray();

        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                stack.push(i);
            } else if (input[i] == ')') {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    if (input[stack.peek()] == '(') {
                        stack.pop();
                    } else {
                        stack.push(i);
                    }
                }
            }
        }
        // Now in our Stack we have index of only invalid brackets
        while (!stack.isEmpty()) {
            int index = stack.pop();
            input[index] = '-'; // mark flag to not choose
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            if (input[i] != '-') {
                str.append(input[i]);
            }
        }

        return str.toString();
    }
}
