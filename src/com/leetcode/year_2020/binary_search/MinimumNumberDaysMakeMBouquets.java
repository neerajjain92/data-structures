package com.leetcode.year_2020.binary_search;

/**
 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
 * <p>
 * 1482. Minimum Number of Days to Make m Bouquets
 */
public class MinimumNumberDaysMakeMBouquets {

    public static void main(String[] args) {
        System.out.println(minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
        System.out.println(minDays(new int[]{1, 10, 3, 10, 2}, 3, 2));
        System.out.println(minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }

    public static int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) return -1;
        int[] minAndMax = findMax(bloomDay);
        int low = minAndMax[0];
        int high = minAndMax[1];
        int answer = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean canMakeMBoquets = canMakeMBoquets(bloomDay, mid, m, k);
            if (canMakeMBoquets) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    private static boolean canMakeMBoquets(int[] bloomDay, int day, int m, int k) {
        int count = 0;
        int noOfBoquet = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= day) {
                count++;
            } else {
                noOfBoquet += count / k;
                count = 0;
            }
        }
        noOfBoquet += count / k;
        return noOfBoquet >= m;
    }

    private static int[] findMax(int[] bloomDay) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : bloomDay) {
            max = Math.max(i, max);
            min = Math.min(i, min);
        }
        return new int[]{min, max};
    }
}
