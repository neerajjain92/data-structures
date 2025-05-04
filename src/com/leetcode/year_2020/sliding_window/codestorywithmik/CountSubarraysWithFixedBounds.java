package com.leetcode.year_2020.sliding_window.codestorywithmik;

public class CountSubarraysWithFixedBounds {

    public static void main(String[] args) {
        CountSubarraysWithFixedBounds cs = new CountSubarraysWithFixedBounds();
        System.out.println(cs.countSubarrays(new int[]{1, 3, 5, 2, 7, 5}, 1, 5));
        System.out.println(cs.countSubarraysOptimized(new int[]{1, 3, 5, 2, 7, 5}, 1, 5));
        System.out.println(cs.countSubarraysOptimized(new int[]{1, 1, 1, 1}, 1, 1));
    }

    /**
     * At each index check how many subArrays ending at that i,
     * with minK and maxK
     * <p>
     * [1 3 5 2 7 5]
     * If you notice carefully, there are 2 such subArrays
     * [1 3 5]
     * [1 3 5 2]
     * <p>
     * As soon as you try to add this 7 it will no longer satisfy
     * the minK and maxK requirement
     * [1 3 5 2 7]; minK=1 and maxK=5
     * because 7 is not between 1<=7<=5 ==> false
     * <p>
     * So 7 becomes are culpritMember, initially you'll assume nothing is culprit
     * So Algo is simple:
     * 0. Consider how many SubArrays will end at the specific index
     * 1. Find minK and maxK index
     * 2. If during the iteration you find any culpritIndex update that
     * 3. Answer += Min(minK_Index, maxK_Index) - culpritIndex
     * 4. if [Min(minK_Index, maxK_Index) - culpritIndex] <= 0, don't add to
     * answer, what that signify is that you found the culpritIndex
     * between minIndex ---- culpritIndex ----- MaxIndex
     * so you can never make this subArray satisfy.
     * <p>
     * [1 3 7 4 5] --> There is no Sub-Array which can satisfy this.
     * <p>
     * And it make sense as well think about it, if culprit entered in between
     * no sub-Array can work
     * <p>
     * [1, 3, 5, 2, 7, 5] ==> In this case notice 7 lies outside of [1, 3, 5, 2] and
     * till/before you reach 7 all other indices are using -1 as culpritIndex
     */
    public long countSubarraysOptimized(int[] nums, int minK, int maxK) {
        int minKPos = -1, maxKPos = -1, culpritIndex = -1;
        long answer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                culpritIndex = i;
            }
            // SubArray ending at this index
            if (nums[i] == minK) {
                minKPos = i;
            }
            if (nums[i] == maxK) {
                maxKPos = i;
            }

            int temp = Math.min(minKPos, maxKPos) - culpritIndex;
            if (temp > 0) {
                answer += temp;
            }
        }
        return answer;
    }

    /**
     * This will give TLE
     */
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int totalSubarrays = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (isValidSubArray(i, j, nums, minK, maxK)) {
                    totalSubarrays++;
                }
            }
        }
        return totalSubarrays;
    }

    private boolean isValidSubArray(int i, int j, int[] nums, int minK, int maxK) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            min = Math.min(min, nums[k]);
            max = Math.max(max, nums[k]);
        }
        return min == minK && max == maxK;
    }
}
