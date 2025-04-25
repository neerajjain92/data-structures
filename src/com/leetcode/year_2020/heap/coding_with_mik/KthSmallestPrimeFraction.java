package com.leetcode.year_2020.heap.coding_with_mik;

import com.util.LogUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 786. K-th Smallest Prime Fraction
 * https://leetcode.com/problems/k-th-smallest-prime-fraction/description/
 */
public class KthSmallestPrimeFraction {

    public static void main(String[] args) {
        KthSmallestPrimeFraction ks = new KthSmallestPrimeFraction();
        LogUtil.printArray(ks.kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 3));
        LogUtil.printArray(ks.kthSmallestPrimeFractionOptimized(new int[]{1, 2, 3, 5}, 3));
        LogUtil.printArray(ks.kthSmallestPrimeFractionOptimized(new int[]{1, 7}, 1));
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.fraction, a.fraction));
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                maxHeap.offer(new Pair(arr[i], arr[j]));
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        Pair kthSmallestPair = maxHeap.poll();
        return new int[]{kthSmallestPair.numerator, kthSmallestPair.denominator};
    }

    static class Pair {
        int numerator, denominator;
        double fraction;

        public Pair(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            this.fraction = (double) numerator / (double) denominator;
        }
    }

    /**
     * Intuition:
     * <p>
     * We know that input is sorted so what is the smallest fraction you can ever get it's nums[0]/nums[last]
     * right because 0 index has smallest value in numerator and nums[last] has the largest value
     * So does that help anyhow ? Not really we need kth smallest not 1st smalllest else why would this be a Leetcode Quesiton bro.
     * <p>
     * Example :  arr = [1,2,3,5], k = 3
     * So In first iteration if i divide all number by largest i have the 1st smallest
     * [1/5], [2/5], [3/5]  ==> first is smallest, but 2nd and 3rd may or may not be 2nd and 3rd smallest, you may ask why ? because
     * because the same smallest number can produce a much less fraction  if devided with largest-1st number. So we can't rule that possibility
     * So how about if i keep a MinHeap to have the smallest fraction always on top, and we try k times to get kth smallest
     * <p>
     * and we also keep size of minHeap as k
     * <p>
     * Round 1: You have your 1st smallest on top
     * | 0.2 [1, 5]: index[0, 3] |
     * | 0.4, [2,5]: index[1, 3] |
     * | 0.6, [3,5]: index[2, 3] |
     * -------------------------
     * <p>
     * Now pop that smallest and you can divide the numerator with the next largest number which one would that be it's index of denominator - 1
     * So that would be index[0, 3] - 1 ==>> index[0, 2], which is 1/3
     * <p>
     * Round 2: 2nd smallest again on top
     * | 0.33 [1, 3]: index[0, 2] |
     * | 0.4, [2,5]: index[1, 3] |
     * | 0.6, [3,5]: index[2, 3] |
     * -------------------------
     * <p>
     * keep following this till you have your kth smallest
     */
    public int[] kthSmallestPrimeFractionOptimized(int[] arr, int k) {
        PriorityQueue<PairWithIndexAndFraction> minHeap = new PriorityQueue<>(Comparator.comparingDouble(a -> a.fraction));
        // Divide all numbers with last
        int n = arr.length;
        int largest = arr[n - 1];
        for (int i = 0; i < n - 1; i++) {
            minHeap.add(new PairWithIndexAndFraction((double) arr[i] / (double) largest, i, n - 1));
        }
        int[] result = new int[2];
        while (k-- > 0) {
            PairWithIndexAndFraction pair = minHeap.poll(); // Smallest element
            if (k == 0) {
                // this is kth smallest
                result[0] = arr[pair.numerator_index];
                result[1] = arr[pair.denominator_index];
                break;
            }

            // Now let's try to find next smallest, by dividing the numerator with next largest denominator
            minHeap.add(new PairWithIndexAndFraction((double) arr[pair.numerator_index] / arr[pair.denominator_index - 1], pair.numerator_index, pair.denominator_index - 1));
        }
        return result;
    }

    static class PairWithIndexAndFraction {
        double fraction;
        int numerator_index, denominator_index;

        public PairWithIndexAndFraction(double fraction, int numerator_index, int denominator_index) {
            this.fraction = fraction;
            this.numerator_index = numerator_index;
            this.denominator_index = denominator_index;
        }
    }
}
