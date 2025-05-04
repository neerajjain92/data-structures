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

        System.out.println(obj.parseBoolExprSingleStack("&(|(f))"));
        System.out.println(obj.parseBoolExprSingleStack("|(f,f,f,t)"));
        System.out.println(obj.parseBoolExprSingleStack("!(&(f,t))"));
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

    public boolean parseBoolExprSingleStack(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c != ')') {
                stack.push(c);
            } else {
                // We found a closing bracket, let's solve everything inside the bracket
                StringBuilder innerExpression = new StringBuilder();
                while (stack.peek() != '(') {
                    char popped = stack.pop();
                    if (popped == 'f' || popped == 't') {
                        innerExpression.append(popped);
                    }
                }
                // Pop the opening bracket
                stack.pop();
                char result = evalExpression(innerExpression, stack.pop()); // this  pop is to pop the operator
                if (stack.isEmpty()) {
                    return result == 't';
                } else {
                    stack.push(result); // to combine with a bigger bracket
                }
            }
        }
        return false; // Nothing evaled out to true hence false
    }

    private char evalExpression(StringBuilder innerExpression, Character operator) {
        if (operator == '&') {
            return innerExpression.toString().contains("f") ? 'f' : 't';
        } else if (operator == '|') {
            return innerExpression.toString().contains("t") ? 't' : 'f';
        } else {
            return innerExpression.charAt(0) == 't' ? 'f' : 't';
        }
    }
}
