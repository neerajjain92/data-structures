package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/
 * 1910. Remove All Occurrences of a Substring
 */
public class RemoveAllOccurrencesofaSubstring {

    public static void main(String[] args) {
        RemoveAllOccurrencesofaSubstring obj = new RemoveAllOccurrencesofaSubstring();
        System.out.println(obj.removeOccurrencesUsingStringBuilder("daabcbaabcbc", "abc"));
        System.out.println(obj.removeOccurrencesUsingStringBuilder("axxxxyyyyb", "xy"));
        System.out.println(obj.removeOccurrencesUsingStringBuilder("eemckxmckx", "emckx"));
        System.out.println(obj.removeOccurrencesUsingStringBuilder("ccctltctlltlb", "ctl"));

        System.out.println("Using Stack............");
        System.out.println(obj.removeOccurrences("daabcbaabcbc", "abc"));
        System.out.println(obj.removeOccurrences("axxxxyyyyb", "xy"));
        System.out.println(obj.removeOccurrences("eemckxmckx", "emckx"));
        System.out.println(obj.removeOccurrences("ccctltctlltlb", "ctl"));
    }

    public String removeOccurrencesUsingStringBuilder(String s, String part) {
        StringBuilder sb = new StringBuilder();
        int pLen = part.length();
        for (char c : s.toCharArray()) {
            sb.append(c);
            if (sb.length() >= pLen) {
                int t1 = sb.length() - 1;
                int t2 = pLen - 1;
                while (t2 >= 0 && sb.charAt(t1) == part.charAt(t2)) {
                    t2--;
                    t1--;
                }
                if (t2 == -1) {
                    // We found a part
                    sb.setLength(t1 + 1);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Let's solve using stack, A simple hack to avoid multiple push and pop
     */
    public String removeOccurrences(String s, String part) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> tempStack = new Stack<>();
        int pLen = part.length();
        for (char c : s.toCharArray()) {
            stack.push(c);
            tempStack.clear();
            if (stack.size() >= pLen) {
                int pCounter = pLen - 1;
                while (!stack.isEmpty() && pCounter >= 0 && stack.peek() == part.charAt(pCounter)) {
                    tempStack.push(stack.pop());
                    pCounter--;
                }
                if (tempStack.size() == pLen) {
                    // we should skip this guy
                } else {
                    while (!tempStack.isEmpty()) {
                        stack.push(tempStack.pop());
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
