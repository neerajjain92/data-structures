package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.Stack;

public class BasicCalculator {

    public static void main(String[] args) {
        BasicCalculator basicCalculator = new BasicCalculator();
        System.out.println(basicCalculator.calculate("10-(4+5+2)-3+(6+8)"));
        System.out.println(basicCalculator.calculate("1 + 1"));
    }

    /*
     * Problem: Evaluate a basic expression string containing '+', '-', '(', ')', and digits.
     *
     * Approach (Story-based explanation):
     * ------------------------------------
     * We only have 5 types of characters to handle: digits, '+', '-', '(', ')', and spaces.
     *
     * Key variables:
     * - number:  To build the current operand (can be multi-digit).
     * - result:  To accumulate the computed result as we process characters.
     * - sign:    Tracks the current sign (+1 or -1) before any number.
     *
     * Strategy:
     * 1. Initialize sign as +1 (positive).
     * 2. Iterate over each character from left to right:
     *
     *    a) If the character is a digit:
     *       - Build the current number by `number = number * 10 + digit`.
     *
     *    b) If the character is '+' or '-':
     *       - Finalize the current number: add (number * sign) to result.
     *       - Update the sign for the next number.
     *       - Reset number to 0.
     *
     *    c) If the character is '(':
     *       - Push the current result onto the stack (to save context).
     *       - Push the current sign onto the stack.
     *       - Reset result and number to 0.
     *       - Reset sign to +1 (assume a positive context inside parentheses initially).
     *
     *    d) If the character is ')':
     *       - Finalize the current number and add (number * sign) to result.
     *       - Multiply result by the sign popped from the stack.
     *       - Add the previous result popped from the stack.
     *       - Reset number to 0.
     *
     *    e) Ignore spaces.
     *
     * 3. After the loop, there might be a leftover number — add it to the result.
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int number = 0, result = 0, sign = 1;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } else if (c == '+' || c == '-') {
                result += number * sign;
                sign = (c == '+') ? 1 : -1;
                number = 0;
            } else if (c == '(') {
                // Push everything outside bracket
                stack.push(result);
                stack.push(sign);

                // Reset the playground for inside bracket
                number = 0;
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += (number * sign);
                result *= stack.pop(); // Change  sign of result if sign is negative
                result += stack.pop();
                number = 0;
                sign = 1;
            }
            // Ignore spaces
        }
        return result + (number * sign);
    }
}
