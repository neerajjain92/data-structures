package com.leetcode.year_2020.MayChallenge.week3;

import com.leetcode.year_2020.stack.NearestGreaterElementToTheLeft;

import java.util.Stack;

/**
 * https://leetcode.com/problems/online-stock-span/
 *
 * @author neeraj on 19/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * <p>
 * This problem is similar to finding {@link NearestGreaterElementToTheLeft}
 */
public class StockSpanner {

    class Pair {
        int data;
        int index;

        public Pair(int data, int index) {
            this.data = data;
            this.index = index;
        }
    }

    Stack<Pair> stack;
    int index;

    public StockSpanner() {
        stack = new Stack<>();
        index = 1;
    }

    public int next(int price) {
        Pair pair = new Pair(price, index);
        int span = 1; // Why span is 1 by default since current element has to be included in the span.
        if (!stack.isEmpty()) {
            while (!stack.isEmpty() && stack.peek().data <= price) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                span = index;
            } else {
                span = (index - stack.peek().index);
            }
        }
        stack.push(pair);
        index++;
        return span;
    }

    public static void main(String[] args) {
        StockSpanner obj = new StockSpanner();
        /**
         * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85],
         * then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
         */
        System.out.println(obj.next(100));
        System.out.println(obj.next(80));
        System.out.println(obj.next(60));
        System.out.println(obj.next(70));
        System.out.println(obj.next(60));
        System.out.println(obj.next(75));
        System.out.println(obj.next(85));

        /**
         * [[],[31],[41],[48],[59],[79]]
         */
//        System.out.println(obj.next(31));
//        System.out.println(obj.next(41));
//        System.out.println(obj.next(48));
//        System.out.println(obj.next(59));
//        System.out.println(obj.next(79));
    }

    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */
}
