package com.leetcode.year_2020.Greedy.coding_with_mik;

/**
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/description/
 * 1326. Minimum Number of Taps to Open to Water a Garden
 */
public class MinimumNumberOfTapsToOpenToWaterAGarden {

    public static void main(String[] args) {

    }

    /**
     * Intuition:
     * For every index we want to calculate how much max left it can go and how much max
     * in the right it can go
     *
     * n = 5, ranges = [3, 3, 1, 1, 2, 0]
     *  Bound is left = 0 and right = 5, if we cross these we will bound them using
     *  left = Math.max(0, i - ranges[i])
     *  right = Math.min(n, i + ranges[i])
     * ----------------------------
     * Index   Left   Right
     *   0      0      3
     *   1      0      4
     *   2      1      3
     *   3      2      4
     *   4      2      5
     *   5      5      5
     * ------------------------------
     *
     * Now let's create an array where index represent leftNode and value is how much max distance
     * in right we can travel
     * |----------|-------------------
     * |index     | 0  1  2  3  4  5   ===> since 3 and 4 were not present so we kept the value to 0
     * |maxRight  | 4  3  5  0  0  5
     * |--------- |-------------------
     *
     * Now if someone is inspecting from index-0 that did all the field is wet, so they will start from
     * index 0 and we will check how much maxEnd it can conver from that index,
     * The inspector is only happy if land is wet
     *
     * So tapOpened = 0;
     * currentEnd = 0;
     * maxEnd = 0;
     *
     * From index = 0, maxEnd = 4, if (i < currentEnd) which means we are safe and no need to open new tap
     * From index = 1, maxEnd still 4 but i > currentEnd so currentEnd = maxEnd and we will open tap and so on
     */
    public int minTaps(int n, int[] ranges) {
        int[] maxRightFromLeft = new int[n+1];
        for (int i = 0; i < ranges.length; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            maxRightFromLeft[left] = Math.max(maxRightFromLeft[left], right);
        }

        // Now let's iterate
        int tapOpened = 0, currentEnd = 0, maxEnd = 0;
        for (int i = 0; i <= n; i++) {
            if (i > maxEnd) {
                // Not possible at all to wet all fields
                return -1;
            }
            if (i > currentEnd) {
                // we are on dry land
                tapOpened++;
                currentEnd = maxEnd;
            }
            maxEnd = Math.max(maxRightFromLeft[i], maxEnd);
        }
        return tapOpened;
    }
}
