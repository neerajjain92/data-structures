package com.datastructures.recursion;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author neeraj on 25/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RecursivelyRemoveAllAdjacentDuplicates {

    public static void main(String[] args) {
        System.out.println(removeAdjacentDuplicates("AZXXZY"));
        System.out.println(removeAdjacentDuplicates("geeksforgeeg"));
        System.out.println(removeAdjacentDuplicates("caaabbbaacdddd"));
        System.out.println(removeAdjacentDuplicates("acaaabbbacdddd"));

        System.out.println("-------------------Without Recursion using Queue--------------------->");
        System.out.println(removeDuplicates("AZXXZY"));
        System.out.println(removeDuplicates("geeksforgeeg"));
        System.out.println(removeDuplicates("caaabbbaacdddd"));
        System.out.println(removeDuplicates("acaaabbbacdddd"));
        System.out.println(removeDuplicates("aaaaaaaaa"));

        System.out.println("-------------------Without Recursion using Queue remove all--------------------->");
        System.out.println(removeAllDuplicates("AZXXZY"));
        System.out.println(removeAllDuplicates("geeksforgeeg"));
        System.out.println(removeAllDuplicates("caaabbbaacdddd"));
        System.out.println(removeAllDuplicates("acaaabbbacdddd"));
        System.out.println(removeAllDuplicates("aaaaaaaaa"));
        System.out.println(removeAllDuplicates("abccba")); // expeced ""
        System.out.println(removeAllDuplicates("abbaca")); // expected "ca"
        System.out.println(removeAllDuplicates("azxxzy")); // expected "azxxzy"
        System.out.println(removeAllDuplicates("aabbc")); // expected "c"
        System.out.println(removeAllDuplicates("ccddb")); // expected "b"
        System.out.println(removeAllDuplicates("abbaaa")); // expected "b"
        System.out.println(removeAllDuplicates("abcddcbeea")); // expected "b"

        System.out.println("-------------------Without Recursion using Stack--------------------->");
        System.out.println(removeDuplicatesUsingStack("AZXXZY"));
        System.out.println(removeDuplicatesUsingStack("geeksforgeeg"));
        System.out.println(removeDuplicatesUsingStack("caaabbbaacdddd"));
        System.out.println(removeDuplicatesUsingStack("acaaabbbacdddd"));
        System.out.println(removeDuplicatesUsingStack("aaaaaaaaa"));


        System.out.println("-------------------Without Recursion using Stack--------------------->");
        System.out.println(removeAdjacentDuplicatesUsingSimpleIteration("AZXXZY"));
        System.out.println(removeAdjacentDuplicatesUsingSimpleIteration("geeksforgeeg"));
        System.out.println(removeAdjacentDuplicatesUsingSimpleIteration("caaabbbaacdddd"));
        System.out.println(removeAdjacentDuplicatesUsingSimpleIteration("acaaabbbacdddd"));
        System.out.println(removeAdjacentDuplicatesUsingSimpleIteration("aaaaaaaaa"));
    }

    public static String removeAdjacentDuplicates(String s) {
        //base condition
        if (s.length() <= 1) return s; // There can't be any duplicate in single letter or no letter.

        // Hypothesis
        String uniqueItems = removeAdjacentDuplicates(s.substring(1));

        // Induction
        int index = uniqueItems.indexOf(s.charAt(0));

        if (index > -1) {
            StringBuilder sb = new StringBuilder(uniqueItems);
            sb.deleteCharAt(index);
            return s.charAt(0) + "";
        } else {
            return s.charAt(0) + uniqueItems;
        }
    }

    public static String removeDuplicates(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char popped = queue.poll();
//            boolean foundDuplicate = false;
//            while (!queue.isEmpty() && queue.peek() == popped) {
//                queue.poll();
//                foundDuplicate = true;
//            }
//            if (foundDuplicate) continue;
            // Only removing 2 adjacent
            if (!queue.isEmpty() && queue.peek() == popped) {
                queue.poll();
                continue;
            }
            if (!sb.isEmpty() && sb.charAt(sb.length() - 1) == popped) {
                sb.deleteCharAt(sb.length() - 1);
                continue;
            }
            sb.append(popped);
        }
        return sb.toString();
    }

    public static String removeAllDuplicates(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char popped = queue.poll();
            boolean foundDuplicate = false;
            while (!queue.isEmpty() && queue.peek() == popped) {
                queue.poll();
                foundDuplicate = true;
            }
            if (!sb.isEmpty() && sb.charAt(sb.length() - 1) == popped) {
                sb.deleteCharAt(sb.length() - 1);
                continue;
            }
            if (foundDuplicate) continue;
            sb.append(popped);
        }
        return sb.toString();
    }

    public static String removeDuplicatesUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            stack.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char popped = stack.pop();
            // Only removing 2 adjacent
            if (!stack.isEmpty() && stack.peek() == popped) {
                stack.pop();
                continue;
            }
            if (!sb.isEmpty() && sb.charAt(sb.length() - 1) == popped) {
                sb.deleteCharAt(sb.length() - 1);
                continue;
            }
            sb.append(popped);
        }
        return sb.reverse().toString();
    }

    public static String removeAdjacentDuplicatesUsingSimpleIteration(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!sb.isEmpty() && sb.charAt(sb.length() - 1) == c) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
