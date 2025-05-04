package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CheckIfAParenthesesStringCanBeValid {

    public static void main(String[] args) {
        CheckIfAParenthesesStringCanBeValid obj = new CheckIfAParenthesesStringCanBeValid();
        System.out.println(obj.canBeValid("))()))", "010100"));
        System.out.println(obj.canBeValid("()()", "0000"));
        System.out.println(obj.canBeValid(")", "0"));
        System.out.println(obj.canBeValid("(((())(((())", "111111010111"));
        System.out.println(obj.canBeValid(")", "1"));
        System.out.println(obj.canBeValid(")(", "00"));
    }

    public boolean canBeValid(String s, String locked) {
        if (s.length() % 2 != 0) return false; // Reject Odd they can never be balanced
        Stack<Integer> openStack = new Stack<>(); // Only this will always store open ones, which are locked
        Stack<Integer> openClosedStack = new Stack<>(); // This can store anyone which is not locked so can be used later to balance

        for (int i = 0; i < s.length(); i++) {
            boolean isLocked = locked.charAt(i) == '1';
            char c = s.charAt(i);
            if (c == '(') {
                if (isLocked) {
                    openStack.push(i); // Since it can't be changed later
                } else {
                    openClosedStack.push(i);
                }
            } else {
                // It's a ')'
                if (!isLocked) {
                    openClosedStack.push(i);
                } else {
                    // Ask openStack and ask openClosed if not then false
                    // and is locked as well
                    if (!openStack.isEmpty()) {
                        openStack.pop();
                    } else if (!openClosedStack.isEmpty()) {
                        openClosedStack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        // Now until openStack.isEmpty
        while (!openStack.isEmpty() && !openClosedStack.isEmpty()) {
            if (openStack.peek() > openClosedStack.peek()) {
                return false;
            }
            openStack.pop();
            openClosedStack.pop();
        }

        // Why are we not checking openClosed, since we are only considering the case when s is even
        // since odd can anyways never be balanced, Now just imagine how much ever we have balanced
        // in the openStack, the remaining ones in the openClosedStack will always be even only right
        // Suppose even length of S = 10, then you balanced 2 pairs, which means 4 is gone
        // So even - even will always be even hence whatever is left in open-and-closed can self balance
        // hence we just have to check for openStack emptiness.
        return openStack.isEmpty();
    }
}
