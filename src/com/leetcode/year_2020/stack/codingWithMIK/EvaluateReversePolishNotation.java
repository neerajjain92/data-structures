package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        EvaluateReversePolishNotation obj = new EvaluateReversePolishNotation();
        System.out.println(obj.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(obj.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(obj.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    private static final Map<Character, BiFunction<Integer, Integer, Integer>> operators = new HashMap<>();

    static {
        operators.put('+', Integer::sum);
        operators.put('-', (a, b) -> a - b);
        operators.put('*', (a, b) -> a * b);
        operators.put('/', (a, b) -> a / b);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isDigit(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                char operator = token.charAt(0);
                int secondOperand = stack.pop();
                int firstOperand = stack.pop();
                stack.push(operators.get(operator).apply(firstOperand, secondOperand));
            }
        }
        return stack.pop();
    }

    public boolean isDigit(String token) {
        try {
            Integer.parseInt(token);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
