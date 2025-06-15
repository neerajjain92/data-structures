package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SatisfiabilityofEqualityEquations {

    int[] parents;

    public static void main(String[] args) {
        SatisfiabilityofEqualityEquations obj = new SatisfiabilityofEqualityEquations();
        System.out.println(obj.equationsPossible(new String[]{
                "a==b", "b!=a"
        }));
        System.out.println(obj.equationsPossible(new String[]{
                "b==a", "a==b"
        }));
        System.out.println(obj.equationsPossible(new String[]{
                "a==b", "b==c", "a==c"
        }));
        System.out.println(obj.equationsPossible(new String[]{
                "a==b", "b!=c", "c==a"
        }));
        System.out.println(obj.equationsPossible(new String[]{
                "c==c", "b==d", "x!=z"
        }));
        System.out.println(obj.equationsPossible(new String[]{
                "a==b", "e==c", "b==c", "a!=e"
        }));
        System.out.println(obj.equationsPossible(new String[]{
                "a==b", "c==d", "a==c", "a!=d"
        }));

        /**
         * false
         * true
         * true
         * false
         * true
         * false
         * false
         */
    }

    public boolean equationsPossible(String[] equations) {
        parents = new int[26];
        for (int i = 0; i < 26; i++) {
            parents[i] = i;
        }

        // On first pass focus on ==
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char operand1 = equation.charAt(0);
                char operand2 = equation.charAt(3);
                union(operand1 - 'a', operand2 - 'a');
            }
        }

        // Now focus on !=
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char operand1 = equation.charAt(0);
                char operand2 = equation.charAt(3);
                int parent1 = findParent(operand1 - 'a');
                int parent2 = findParent(operand2 - 'a');
                if (parent1 == parent2) {
                    return false;
                }
            }
        }
        return true;
    }

    private void union(int operand1, int operand2) {
        int parent1 = findParent(operand1);
        int parent2 = findParent(operand2);

        if (parent1 != parent2) {
            parents[parent1] = parent2;
        }
    }

    private int findParent(int operand) {
        if (operand == parents[operand]) {
            return parents[operand];
        }
        parents[operand] = findParent(parents[operand]);
        return parents[operand];
    }

}
