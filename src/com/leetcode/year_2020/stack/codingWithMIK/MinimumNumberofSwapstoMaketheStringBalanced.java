package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.Stack;

public class MinimumNumberofSwapstoMaketheStringBalanced {

    public static void main(String[] args) {
        MinimumNumberofSwapstoMaketheStringBalanced obj = new MinimumNumberofSwapstoMaketheStringBalanced();
        System.out.println(obj.minSwaps("][]["));
        System.out.println(obj.minSwaps("]]][[["));
        System.out.println(obj.minSwaps("[]"));

        System.out.println(obj.minSwaps2("][]["));
        System.out.println(obj.minSwaps2("]]][[["));
        System.out.println(obj.minSwaps2("[]"));

        System.out.println(obj.minSwaps3("][]["));
        System.out.println(obj.minSwaps3("]]][[["));
        System.out.println(obj.minSwaps3("[]"));
    }

    /**
     * "] ] [] ] [] [ [] [ ["
     * <p>
     * Intuition is simple, ignore all balanced ones because we shouldn't touch them as they are already balanced
     * So after you remove any balanced, you  will always find this Pattern
     * <p>
     * "]]]]]] [[[[[[" ---> A bunch of closed brackets followed by open ones as simple as that because if they any open would be on right
     * side it would already be balanced and ignored
     * <p>
     * Now just check for each open [1 swap] fixes 2 incorrect brackets
     * <p>
     * " ] ] [ [ "  --> upon swapping 0th index with 4th index we get "[ ] [ ]" --> Both are balanced.
     * So the total extra opening brackets, will always help solve 2 hence answer will be totalOpeningBrackets/2
     * Now since question clearly says S is of even length, so our opening remaining will always be in odd number
     * and other odd are closed.
     * <p>
     * Hence if you divide odd/2 it will be decimal but you can't swap partial hence [total_extra_opens+1]/2
     */
    public int minSwaps(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                stack.push(c);
            } else if (c == ']' && !stack.isEmpty()) {
                stack.pop(); // Popping all balanced pairs
            }
        }
        return (stack.size() + 1) / 2;
    }

    /**
     * Good and best improvement on minSwaps2 and very intuitive
     */
    public int minSwaps2(String s) {
        // In minSwaps3 we were checking for stack.size, so why do we need stack for that
        int size = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                size++;
            } else if (size > 0) {
                size--;
            }
        }
        return (size + 1) / 2;
    }

    public int minSwaps3(String s) {
        // Now just think, do you really need a Stack ??? We just said (total_extra_close+1)/2 will also solve the same thing for us right ???
        // So while traversing if somehow we can let the  current_char which is "]" figure out that brodaa there is no opening bracket left to balance
        // you, so that becomes our extra
        // Lets solve that

        int total_opens = 0;
        int total_extra_close = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                total_opens++;
            } else {
                total_opens--; // Reduce open to balance
                if (total_opens < 0) { // If there were no opens to balance
                    // i.e extra close are present, so calculate them
                    total_extra_close = Math.max(total_extra_close, -total_opens); // Why -total_opens, since total_opens is already negative to compare correctly
                }
            }
        }
        return (total_extra_close + 1) / 2;
    }


}
