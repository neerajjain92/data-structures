package com.leetcode.year_2020.Greedy.coding_with_mik;

/**
 * https://leetcode.com/problems/minimum-replacements-to-sort-the-array/
 * 2366. Minimum Replacements to Sort the Array
 */
public class MinimumReplacementToSortTheArray {

    public static void main(String[] args) {
        System.out.println(minimumReplacement(new int[]{3, 9, 3}));
        System.out.println(minimumReplacement(new int[]{1, 2, 3, 4, 5}));
        System.out.println(minimumReplacement(new int[]{2, 10, 20, 19, 1}));
        System.out.println(minimumReplacement(new int[]{19, 7, 2, 24, 11, 16, 1, 11, 23}));
    }

    public static long minimumReplacement(int[] nums) {
        /*
         * There are many observations in this to solve the question :
         *
         * 1. Given [3, 4, 6, 8, 3, 10]
         * If we move from left to right once we reach last 3 we found it to be < 8, now we should break 8 such that
         * it's not greater than 3 in any part, now if we break 8 into [3 3 2] but the issue now is that 6 is now they are
         * less than 6, so we need to keep backtrack till the first index to find if we are splitting the part the right way
         *
         * Instead how about if we start from the right, we just need to take care of immediate right, if we are smaller than
         * the immediate right we are good.
         *  So for [3, 4, 6, 8, 3, 10] ===> we missed the bucket at 8, and now we know we have to break 8 into parts <= 3
         * without worrying about remaining 6, 4, or 3 to be honest
         *
         * So fact 1 ==> Always traverse from Right
         *
         * 2. In one operation[replacement] you always split the number into 2 parts, so if a number is divided into X parts,
         *  it would always take [parts - 1] replacement/operation.
         *
         * Example split 12 and no more than 3
         * Example 12 ===> [6 | 6] ==> 1 operation
         * --------------> [3 - 3 | 3 - 3] ==> +2 more on both end
         *
         * Now we have total 4 parts [3 3 3 3] but operation is just [1+2 = 3]
         *
         * Fact 2 ==> operation ==> parts - 1;
         *
         * 3. For nums[i-1]%nums[i] == 0,  parts are always nums[i-1]/nums[i]
         *    For nums[i-1]%nums[i] != 0, parts are nums[i-1]/nums[i] + 1, why because we split as much as we can till nums[i],
         *                                                              now whatever is left is obviously a smaller chunk than nums[i]
         *                                                              so to be considered as one more part.
         *
         *  Example: [12 3], split 12 into 12/3 ==> 4 parts
         *           [13, 3] Split 13 into parts of 3 => [3 3 3 3 1] notice extra last part of 1, thats why part++;
         *
         * 4. Most important and final nail in the coffin
         *    When deciding to split a number we should split in such a way that the remaining members on the left
         *      should also perform minimum replacement
         *
         * Imagine [12, 9, 7, 6] , if i split 7 into [1 6] we met the right side criteria, but now the entire 12 has to be split
         * into 12/1 ==> 12 parts and that sucks
         *
         * 7 can be broken into [1 6]
         *                      [2 5]
         *                      [3 4]
         * ideal choice would be to split this into the max permisiable limit so that we satisfy right side
         * and at same time doesn't hurt left candidates too
         *
         * Observe isn't choosing [3 4] can be picked if i simply divide the number by it's parts
         * So 7 ==> parts =((7/6)+1) ==> 2
         *
         * So 7/2 ==> 3, now just floor of it tells you basically how much bigger 1st part can be if i have to split into 2 parts
         *
         * and that my Boysssss is the final observation,
         * For every nums[i] > nums[i+1], the number should be broken into  (int) nums[i]/parts
         *
         */
        long replacement = 0;
        int n = nums.length - 2;
        for (int i = n; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                continue; // sequence is correct
            }
            int parts = nums[i] / nums[i + 1];
            if (nums[i] % nums[i + 1] != 0) {
                parts++; // As explained above
            }
            replacement += parts - 1; // As explained above
            nums[i] = nums[i] / parts; // Why this because we want to always keep ith index with the least possible value from right
            // and dividing by parts give me exactly that
        }
        return replacement;
    }
}
