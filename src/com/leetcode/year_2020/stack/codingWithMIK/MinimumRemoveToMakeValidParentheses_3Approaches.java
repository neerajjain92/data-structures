package com.leetcode.year_2020.stack.codingWithMIK;

import com.leetcode.year_2020.stack.MinimumRemoveToMakeValidParentheses;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinimumRemoveToMakeValidParentheses_3Approaches {

    public static void main(String[] args) {
        MinimumRemoveToMakeValidParentheses_3Approaches obj = new MinimumRemoveToMakeValidParentheses_3Approaches();
//        System.out.println(obj.minRemoveToMakeValidApproach1("lee(t(c)o)de)"));
//        System.out.println(obj.minRemoveToMakeValidApproach2("lee(t(c)o)de)"));
//        System.out.println(obj.minRemoveToMakeValidApproach1("))(("));
//        System.out.println(obj.minRemoveToMakeValidApproach2("))(("));
//        System.out.println(obj.minRemoveToMakeValidApproach1("(a(b(c)d)"));
        System.out.println(obj.minRemoveToMakeValidApproach2("(a(b(c)d)"));
    }

    public String minRemoveToMakeValidApproach1(String s) {
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> toSkip = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    toSkip.add(i);
                }
            }
        }
        // This can happen when "(((" I/P is like this
        // Notice here you will never reach to else-if
        // so you should still skip them
        while (!stack.isEmpty()) {
            toSkip.add(stack.pop());
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (toSkip.contains(i)) {
                continue;
            }
            res.append(s.charAt(i));
        }
        return res.toString();
    }

    /**
     * Intuition is simple:
     * <p>
     * Suppose you have extra Close Parentheses
     * "(()()))"
     * <p>
     * Traverse from left to right and keep track of opens-to cancel out
     * 1. when you find open: open++;
     * 2. when you find close: if open > 0: open--; or ignore
     * <p>
     * with this you are done, but if by chance you had a extra OPEN instead
     * ((())()
     * <p>
     * In this case after completion from left to right, you will
     * have that open count > 0 then you should traverse from
     * right to left on the String prepared in Step:1 and keep track of close
     * ignore open if there is no matching close
     */
    public String minRemoveToMakeValidApproach2(String s) {
        int open = 0;
        StringBuilder res = new StringBuilder();
        // Traversing from left to right and eliminate any extra close
        for (char c : s.toCharArray()) {
            if (c == ')' && open == 0) {
                continue; // Ignore the close
            }
            if (c == '(') {
                open++;
            } else if (c == ')') {
                open--;
            }
            res.append(c);
        }

        if (open > 0) {
            int close = 0;
            // We had extra opens, so traverse from right to left and eliminate extra opens
            for (int i = res.length() - 1; i >= 0; i--) {
                char c = res.charAt(i);
                if (c == '(' && close == 0) {
                    res.deleteCharAt(i);
                    continue;
                }
                if (c == ')') {
                    close++;
                } else if (c == '(') {
                    close--;
                }
            }
        }
        return res.toString();
    }
}
