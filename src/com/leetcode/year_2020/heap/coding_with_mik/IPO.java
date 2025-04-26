package com.leetcode.year_2020.heap.coding_with_mik;

import java.lang.reflect.Type;
import java.util.*;

/**
 * https://leetcode.com/problems/ipo/
 * 502. IPO
 */
public class IPO {

    public static void main(String[] args) {
        IPO ipo = new IPO();
        System.out.println(ipo.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        System.out.println(ipo.findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
        System.out.println(ipo.findMaximizedCapital(1, 2, new int[]{1, 2, 3}, new int[]{1, 1, 2}));

        System.out.println(ipo.findMaximizedCapitalOptimized(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        System.out.println(ipo.findMaximizedCapitalOptimized(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
        System.out.println(ipo.findMaximizedCapitalOptimized(1, 2, new int[]{1, 2, 3}, new int[]{1, 1, 2}));

        System.out.println(ipo.findMaximizedCapitalOptimizedWithoutMinHeap(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        System.out.println(ipo.findMaximizedCapitalOptimizedWithoutMinHeap(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
        System.out.println(ipo.findMaximizedCapitalOptimizedWithoutMinHeap(1, 2, new int[]{1, 2, 3}, new int[]{1, 1, 2}));
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < profits.length; i++) {
            tuples.add(new Tuple(capital[i], profits[i], i));
        }
        tuples.sort((o1, o2) -> o2.capitalRequired - o1.capitalRequired);
        Set<Integer> picked = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int maxForKthTask = 0;
            // Pick k distinct items
            int indexBeingPickedForKthTask = -1;
            for (Tuple tuple : tuples) {
                if (!(picked.contains(tuple.index)) && tuple.capitalRequired <= w) {
                    if (maxForKthTask <= tuple.profit) {
                        indexBeingPickedForKthTask = tuple.index;
                        maxForKthTask = tuple.profit;
                    }
                }
            }
            if (indexBeingPickedForKthTask != -1) {
                w += profits[indexBeingPickedForKthTask];
                picked.add(indexBeingPickedForKthTask);
            }
        }
        return w;
    }

    public int findMaximizedCapitalOptimized(int k, int moneyInPocket, int[] profits, int[] capital) {
        int n = profits.length;
        /*
         * You can only invest in a project if you have money, So logically it make sense
         * to start investing where you have minimum capital requirement, hence minCapitalHeap
         * stores that information
         */
        PriorityQueue<Tuple> minCapitalHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.capitalRequired));
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            minCapitalHeap.add(new Tuple(capital[i], profits[i], i));
        }

        // Now try picking k distinct projects
        for (int i = 0; i < k; i++) {
            // When choosing the currentProject we want to maximizeProfit
            // based on currentMoneyInMyPocket
            // So which better than giving max or min in constant time, Heap
            // Hence the maxProfitHeap keeps that information
            while (!minCapitalHeap.isEmpty() &&
                    minCapitalHeap.peek().capitalRequired <= moneyInPocket) { // Check if we can invest in this project depending on kitna paisa jeb me hai
                maxProfitHeap.add(minCapitalHeap.poll().profit);
            }

            // We didn't have enough money in pocket to put any profit in maxProfitHeap
            // so TATA
            if (maxProfitHeap.isEmpty()) break;

            // We completed project so lets bring that money into our pocket to
            // invest in next best opportunity
            moneyInPocket += maxProfitHeap.poll();
        }
        return moneyInPocket;
    }

    static class Tuple {
        int capitalRequired, profit, index;

        public Tuple(int capitalRequired, int profit, int index) {
            this.capitalRequired = capitalRequired;
            this.profit = profit;
            this.index = index;
        }
    }

    public int findMaximizedCapitalOptimizedWithoutMinHeap(int k, int moneyInPocket, int[] profits, int[] capital) {
        int n = profits.length;
        List<Tuple> tuples = new ArrayList<>();
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            tuples.add(new Tuple(capital[i], profits[i], i));
        }

        tuples.sort(Comparator.comparingInt(o -> o.capitalRequired));

        // Now try picking k distinct projects
        int i=0;
        while (k-- > 0) {
            // When choosing the currentProject we want to maximizeProfit
            // based on currentMoneyInMyPocket
            // So which better than giving max or min in constant time, Heap
            // Hence the maxProfitHeap keeps that information
            while (i < n && tuples.get(i).capitalRequired <= moneyInPocket) { // Check if we can invest in this project depending on kitna paisa jeb me hai
                maxProfitHeap.add(tuples.get(i).profit);
                i++;
            }

            // We didn't have enough money in pocket to put any profit in maxProfitHeap
            // so TATA
            if (maxProfitHeap.isEmpty()) break;

            // We completed project so lets bring that money into our pocket to
            // invest in next best opportunity
            moneyInPocket += maxProfitHeap.poll();
        }
        return moneyInPocket;
    }
}
