package com.leetcode.year_2020.two_pointers;

import com.datastructures.array.ArrayUtil;
import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1762. Buildings With an Ocean View Premium
 * There are n buildings in a line. You are given an integer array
 * heights of size n that represents the heights of the buildings
 * in the line.
 * The ocean is to the left and right of the buildings. A building
 * has an ocean view if the building can see the ocean without
 * obstructions. Formally, a building has an ocean view if all the
 * buildings to its right or left have a smaller height.
 * Return a list of indices (0-indexed) of buildings that have an
 * ocean view, sorted in increasing order.
 * <p>
 * Variant in Meta: where we have ocean on both sides
 * Example 1:
 * Input: heights = [2,5,3, 10,9,8]
 * Output: [0,1,3,4,5]
 * Explanation: Building 2 (0-indexed) does not
 * have an ocean view because building 1 is
 * taller from the left and buildings 3, 4, and 5
 * are taller from the right.
 * <p>
 * https://www.youtube.com/watch?v=tbkzCFlKNWU&ab_channel=CodingwithMinmer
 */
@SuppressWarnings("JavadocLinkAsPlainText")
public class BuildingsWithOceanView {

    public static void main(String[] args) {
        LogUtil.printArray(findBuildingsWithOceanView(new int[]{2, 5, 3, 10, 9, 8}));
    }

    public static int[] findBuildingsWithOceanView(int[] heights) {
        int leftMax = heights[0]; // Since on leftMost item, first building is the talllest
        int rightMax = heights[heights.length - 1]; // on Right side no other building than this one is taller
        int leftIndex = 0;
        int rightIndex = heights.length - 1;
        List<Integer> leftView = new ArrayList<>();
        List<Integer> rightView = new ArrayList<>();
        leftView.add(leftIndex);
        rightView.add(rightIndex);
        // We will check on both sides wherever it's possible for a building to see ocean we will consider that window
        while (leftIndex <= rightIndex) {
            if (leftMax < rightMax) {
                if (heights[leftIndex] > leftMax) {
                    leftView.add(leftIndex);
                    leftMax = heights[leftIndex];
                }
                leftIndex++;
            } else {
                if (heights[rightIndex] > rightMax) {
                    rightView.add(rightIndex);
                    rightMax = heights[rightIndex];
                }
                rightIndex--;
            }
        }
        Collections.reverse(rightView);
        return mergeList(leftView, rightView);
    }

    private static int[] mergeList(List<Integer> leftView, List<Integer> rightView) {
        int[] result = new int[leftView.size() + rightView.size()];
        int counter = 0;
        for (Integer integer : leftView) {
            result[counter++] = integer;
        }
        for (Integer integer : rightView) {
            result[counter++] = integer;
        }
        return result;
    }

}
