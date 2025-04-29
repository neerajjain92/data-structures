package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.Stack;

/**
 * 402. Remove K Digits
 * https://leetcode.com/problems/remove-k-digits/description/
 */
public class RemoveKDigits {

    public static void main(String[] args) {
        RemoveKDigits removeKDigits = new RemoveKDigits();
        System.out.println(removeKDigits.removeKdigits(String.valueOf(1432219), 3));
        System.out.println(removeKDigits.removeKdigits(String.valueOf(10200), 1));
        System.out.println(removeKDigits.removeKdigits(String.valueOf(10), 2));
        System.out.println(removeKDigits.removeKdigits(String.valueOf(9), 1));
    }

    /*
     * Some Intuition:
     *
     * 1. To make the smallest number from given digits: Assume [8 3 9 1]
     *      How will you think about converting this to a smallest number, you obviously pick smallest number on the most significant bit
     *      right, because it's basic Maths
     *
     *       Thousand  Hundred  Tens   Ones
     *      _________| _______| _____| _____
     *
     *      So let's put 1 then 3 then 8 then 9 [1 3 8 9], you can't make any smaller number than this, but here we had freedom to shuffle but in question we don't
     *
     * So to make smallest number, you need to put them in increasing order, and if you have the liberty i.e; (k > 0) delete/ignore those digits
     *
     * So atleast try to make increasing  numbers
     *
     * Example:
     *
     *      1  4   3  2  2  1 9                     k=3 - O/P: "1 4"----------> At i = 2 we delete 4 and k-- = k=2 O/P: "1 3
     *      i
     *         i                                    ==>  (still increasing)
     *             i                                ==>  (Decreasing, so we need to course correct), Now you tell me which one you will delete obviously the bigger one
     *                i                             ==> Again decreasing so delete 3 So K=1 left and O/P "1 2
     *                  i                           --> Again decreasing so delete 2 k=0 and O/P "1 2" [because there were 2 two's]
     *
     * K is 0 so take remaining and O/P [1 2 1 9] smallest you can ever make
     *
     * The only minor observation is we keep deleting until we find the increasing sequence again till we have k
     * so for keeping history STACK BRO STACK
     */
    public String removeKdigits(String num, int k) {
        if (num.length() < k) return "0";
        Stack<Integer> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > (c - '0') && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c - '0');
        }
        while (k-- > 0 && !stack.isEmpty()) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (Integer i : stack) {
            sb.append(i);
        }

        // Remove leading 0's
        while (!sb.isEmpty() && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.isEmpty() ? "0" : sb.toString();
    }
}
