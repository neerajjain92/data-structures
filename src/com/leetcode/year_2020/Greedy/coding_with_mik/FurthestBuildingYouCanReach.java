package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

public class FurthestBuildingYouCanReach {

    public static void main(String[] args) {
        FurthestBuildingYouCanReach obj = new FurthestBuildingYouCanReach();
        System.out.println(obj.furthestBuilding(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1));
        System.out.println(obj.furthestBuilding(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2));
        System.out.println(obj.furthestBuilding(new int[]{14, 3, 19, 3}, 17, 0));
    }

    /*
     * We know we have 2 options either use brick or ladder, so we should try both
     * and for trying options nothing beats RECURSION
     *
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        return solve(heights, 0, bricks, ladders);
    }

    private int solve(int[] heights, int index, int bricks, int ladders) {
        if (index == heights.length - 1) return index; // you reached end yeahhh.
        if (heights[index] >= heights[index + 1]) {
            // We can simply jump without using ladder or brick
            return solve(heights, index + 1, bricks, ladders);
        } else {
            // we need both
            int diffInHeights = heights[index + 1] - heights[index];
            int maxReach = index;
            if (bricks >= diffInHeights) {
                maxReach = Math.max(maxReach, solve(heights, index + 1, bricks - diffInHeights, ladders));
            }
            if (ladders > 0) {
                maxReach = Math.max(maxReach, solve(heights, index + 1, bricks, ladders - 1));
            }
            return maxReach;
        }
    }
}
