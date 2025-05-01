package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode.com/problems/parsing-a-boolean-expression/description/
 */
public class ParsingABooleanExpression {

    private static final Set<Character> allowedOperatord = Set.of('&', '|', '!');

    public static void main(String[] args) {
        ParsingABooleanExpression obj = new ParsingABooleanExpression();
        System.out.println(obj.parseBoolExpr("&(|(f))"));
        System.out.println(obj.parseBoolExpr("|(f,f,f,t)"));
        System.out.println(obj.parseBoolExpr("!(&(f,t))"));
    }

    public boolean parseBoolExpr(String expression) {
        Stack<Character> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (c == 'f' || c == 't' || c == '(') {
                operands.push(c);
            } else if (allowedOperatord.contains(c)) {
                operators.push(c);
            } else if (c == ')') {
                // Now check what's their on top of stack
                char operator = operators.pop();
                evaluateTopExpression(operator, operands);
            }
        }
        return operands.peek() == 't';
    }

    private void evaluateTopExpression(char operator, Stack<Character> operands) {
        List<Character> values = popOperandsUntilOpenCharacter(operands);
        char result = switch (operator) {
            case '&' -> evaluateAnd(values);
            case '|' -> evaluateOr(values);
            case '!' -> evaluateNot(values);
            default -> 'f';
        };
        operands.push(result);
    }

    private char evaluateNot(List<Character> values) {
        return values.get(0) == 'f' ? 't' : 'f';
    }

    private char evaluateOr(List<Character> values) {
        for (char c : values) {
            if (c == 't') {
                return 't';
            }
        }
        return 'f';
    }

    private char evaluateAnd(List<Character> values) {
        for (char c : values) {
            if (c == 'f') {
                return 'f';
            }
        }
        return 't';
    }

    private List<Character> popOperandsUntilOpenCharacter(Stack<Character> operands) {
        List<Character> values = new ArrayList<>();
        while (!operands.empty() && operands.peek() != '(') {
            values.add(operands.pop());
        }
        if (!operands.empty() && operands.peek() == '(') {
            operands.pop(); // Remove this open it's job is done
        }
        return values;
    }
}
