package com.leetcode.year_2020.Greedy.coding_with_mik;

/**
 * 1578. Minimum Time to Make Rope Colorful
 * https://leetcode.com/problems/minimum-time-to-make-rope-colorful/description/
 */
public class MinimumTimeToMakeRopeColorful {

    public static void main(String[] args) {
        System.out.println(minCost("abaac", new int[]{1, 2, 3, 4, 5}));
        System.out.println(minCost("abc", new int[]{1, 2, 3}));
        System.out.println(minCost("aabaa", new int[]{1, 2, 3, 4, 1}));
        System.out.println(minCost("cddcdae", new int[]{4, 8, 8, 4, 4, 5, 4, 2}));
    }

    public static int minCost(String colors, int[] neededTime) {
        if (colors == null || colors.isEmpty() || colors.length() == 1) {
            return 0;
        }
        char[] colorArr = colors.toCharArray();
        int i = 0, j = 1, n = colorArr.length;
        int cost = 0;
        while (j < n) {
            if (colorArr[i] == colorArr[j]) {
                cost += Math.min(neededTime[i], neededTime[j]);
                if (neededTime[i] < neededTime[j]) {
                    // only if we are skipping i then we should move i, else keep it at same place
                    i = j;
                }
            } else {
                i = j;
            }
            j++;
        }
        return cost;
    }
}
