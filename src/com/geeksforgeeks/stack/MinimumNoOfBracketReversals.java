package com.geeksforgeeks.stack;

import java.util.Stack;

public class MinimumNoOfBracketReversals {

    public static void main(String[] args) {
        System.out.println(findMinimumNoOfBracketReversals("{{{{"));
        System.out.println(findMinimumNoOfBracketReversals("{{{{}}"));
        System.out.println(findMinimumNoOfBracketReversals("}{{}}{{{"));
    }

    public static int findMinimumNoOfBracketReversals(String input) {
        char[] expression = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        Integer result = 0;
        for (char c : expression) {
            if (c == '{') {
                stack.push(c);
            } else {
                stack.push('{');
                result += 1;
            }
        }
        if (stack.isEmpty())
            return result;
        else {
            if (stack.size() % 2 == 0) {
                return result + (stack.size() / 2);
            } else {
                System.out.println("Expression can't be balanced");
                return -1;
            }
        }
    }
}
