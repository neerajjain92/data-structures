package com.leetcode.year_2020.Greedy.coding_with_mik;

/**
 * https://leetcode.com/problems/broken-calculator/
 */
public class BrokenCalculator {

    public static void main(String[] args) {
        System.out.println(brokenCalc(5, 100));
    }
    /**
     * So we have given 2 operations, either subtract or multiply
     * if we take opposite approach of dividing the target and doing addition instead
     *
     * Also for odd we can't really divide by 2 else we will get decimal, so we should make it even by adding 1
     * and then divide
     *
     */
    public static int brokenCalc(int startValue, int target) {
        int totalOperation = 0;
        while (startValue < target) {
            target = target % 2 == 0 ? target / 2 : target + 1;
            totalOperation++;
        }
        return totalOperation + (startValue - target);
    }
}
