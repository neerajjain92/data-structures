package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReverseSubstringsBetweenEachPairofParentheses {

    public static void main(String[] args) {
        ReverseSubstringsBetweenEachPairofParentheses obj = new ReverseSubstringsBetweenEachPairofParentheses();
        System.out.println(obj.reverseParentheses_O_N("(a(bc)d)"));
        System.out.println(obj.reverseParentheses_O_N("(abcd)"));
        System.out.println(obj.reverseParentheses_O_N("(u(love)i)"));
        System.out.println(obj.reverseParentheses_O_N("(ed(et(oc))el)"));
        System.out.println(obj.reverseParentheses_O_N("(f(b(dc)e)a)"));
    }

    /**
     * Intuition is simple
     * <p>
     * (a (b c) d)
     * <p>
     * Basically for every opening bracket find the matching closing bracket
     * and store in array.
     */
    public String reverseParentheses_O_N(String s) {
        Stack<Integer> stack = new Stack<>();
        int[] bracketPair = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                // The top of the stack is matching it
                bracketPair[stack.peek()] = i;
                bracketPair[i] = stack.pop();
            }
        }

        // Now you have matching pairs
        // Initially we will move from left to right [or +1 as the direction]
        // Whenever we found '('  or ')' we change direction and jump to that.
        StringBuilder sb = new StringBuilder();
        int n = s.length(), direction = 1;
        for (int i = 0; i < n; i += direction) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = bracketPair[i];
                direction = -direction; // Change direction
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * This is O(N^2)
     */
    public String reverseParentheses_ON_2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                List<Character> temp = new ArrayList<>();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    temp.add(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // to remove this '('
                }
                for (char t : temp) {
                    stack.push(t);
                }
            } else {
                stack.push(c);
            }
        }

        // While stack is empty
        // Append to StringBuilder
        // return toString();
        char[] res = new char[stack.size()];
        int i = res.length - 1;
        while (!stack.isEmpty()) {
            res[i--] = stack.pop();
        }
        return new String(res);
    }
}
