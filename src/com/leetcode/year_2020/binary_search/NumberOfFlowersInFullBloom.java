package com.leetcode.year_2020.binary_search;

import java.util.Arrays;

public class NumberOfFlowersInFullBloom {

    public static void main(String[] args) {
        NumberOfFlowersInFullBloom obj = new NumberOfFlowersInFullBloom();
        obj.fullBloomFlowers(new int[][]{{1, 6}, {3, 7}, {9, 12}, {4, 13}}, new int[]{2, 3, 7, 11});
    }

    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int[] startTime = new int[flowers.length];
        int[] endTime = new int[flowers.length];

        for (int i = 0; i < flowers.length; i++) {
            startTime[i] = flowers[i][0];
            endTime[i] = flowers[i][1];
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int[] result = new int[people.length];
        int index = 0;
        for (int entryTime : people) {
            int flowerInBloom = upperBound(startTime, entryTime, true) + 1;
            int flowerCompletelyBloomed = upperBound(endTime, entryTime, false) + 1;
            result[index++] = flowerInBloom - flowerCompletelyBloomed;
        }
        return result;
    }

    private int upperBound(int[] arr, int target, boolean shouldConsiderEqual) {
        int low = 0, high = arr.length - 1;
        int answer = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (shouldConsiderEqual) {
                if (arr[mid] <= target) {
                    answer = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                if (arr[mid] <target) {
                    answer = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }
        return answer;
    }
}
