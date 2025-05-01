package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.Stack;

public class MinimumDeletionsToMakeStringBalanced {

    public static void main(String[] args) {
        MinimumDeletionsToMakeStringBalanced obj = new MinimumDeletionsToMakeStringBalanced();
        System.out.println(obj.minimumDeletions("aababbab"));
        System.out.println(obj.minimumDeletions("bbaaaaabb"));
        System.out.println(obj.minimumDeletions("aaaaaaa"));
        System.out.println(obj.minimumDeletions("bbbbbb"));
        System.out.println(obj.minimumDeletions("babab"));
    }

    public int minimumDeletions(String s) {
        int b_count = 0;
        int minDeletes = 0;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                b_count++;
            } else {
                if (b_count > 0) {
                    minDeletes++;
                    b_count--;
                }
            }
        }
        return minDeletes;
    }
}
