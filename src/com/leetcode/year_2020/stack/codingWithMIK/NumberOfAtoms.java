package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class NumberOfAtoms {

    public static void main(String[] args) {
        NumberOfAtoms obj = new NumberOfAtoms();
        System.out.println(obj.countOfAtoms("H2O"));
        System.out.println(obj.countOfAtoms("Mg(OH)2"));
        System.out.println(obj.countOfAtoms("K4(ON(SO3)2)2"));
    }

    /**
     * 1. Atom starts with Capital letter
     * 2. Any small case letter will be the part of atom which must have started
     * with Capital letter
     * Atoms can atmost be of 2 chars
     * 3. For any number you get it's just
     */
    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new TreeMap<>());

        int n = formula.length(), i = 0;

        while (i < n) {
            char c = formula.charAt(i);

            if (c == '(') { // A new formula coming in
                stack.push(new TreeMap<>());
                i++;

            } else if (c == ')') {
                // End of a group
                Map<String, Integer> atoms = stack.pop();
                i++;

                // Parse multiplier after ')'
                int multiplier = 0;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    multiplier = multiplier * 10 + Character.getNumericValue(formula.charAt(i));
                    i++;
                }
                // Only multiply if it will change the result
                if (multiplier > 1) {
                    for (Map.Entry<String, Integer> atom : atoms.entrySet()) {
                        atom.setValue(atom.getValue() * multiplier);
                    }
                }

                // Merge back the popped formula/segment into the below segment
                for (Map.Entry<String, Integer> atom : atoms.entrySet()) {
                    stack.peek().put(atom.getKey(),
                            stack.peek().getOrDefault(atom.getKey(), 0) + atom.getValue());
                }

            } else if (isCapitalLetter(c)) {
                // Start of new Atom
                StringBuilder atom = new StringBuilder();
                atom.append(c);
                i++;

                // Check for subsequent small-letters
                while (i < n && isSmallCaseLetter(formula.charAt(i))) {
                    atom.append(formula.charAt(i));
                    i++;
                }

                // Count for optional digits
                int count = 0;
                while (i < n && isDigit(formula.charAt(i))) {
                    count = count * 10 + Character.getNumericValue(formula.charAt(i));
                    i++;
                }
                if (count == 0) count = 1;
                stack.peek().put(atom.toString(), stack.peek().getOrDefault(atom.toString(), 0) + count);
            }
        }
        // Now last thing is sort lexographically
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> atom : stack.peek().entrySet()) {
            sb.append(atom.getKey());
            if (atom.getValue() > 1) {
                sb.append(atom.getValue());
            }
        }
        return sb.toString();
    }

    private boolean isCapitalLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isSmallCaseLetter(char c) {
        return c >= 'a' && c <= 'z';
    }
}
