package com.leetcode.year_2020.binary_search;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/magnetic-force-between-two-balls/description/
 */
public class MagneticForceBetweenTwoBalls {

    public static void main(String[] args) {
        MagneticForceBetweenTwoBalls force = new MagneticForceBetweenTwoBalls();
//        System.out.println(force.maxDistance(new int[]{1, 2, 3, 4, 7}, 3));
        System.out.println(force.maxDistance(new int[]{5, 4, 3, 2, 1, 1000000000}, 2));
    }

    public int maxDistance(int[] position, int m) {
        int[] minAndMax = getMinAndMaxVal(position);
        long leastPower = minAndMax[0]; // if they are placed adjacent, you won't place on top of itself right
        long highestPower = minAndMax[1] - minAndMax[0];
        Set<Integer> positionSet = new HashSet<>();
        for (int i : position) {
            positionSet.add(i);
        }
        int answer = -1;
        while (leastPower <= highestPower) {
            long midPower = leastPower + (highestPower - leastPower) / 2;
            if (canPlace(midPower, positionSet, position[0], m)) {
                answer = (int) midPower;
                leastPower = midPower + 1;
            } else {
                highestPower = midPower - 1;
            }
        }
        return answer;
    }

    private boolean canPlace(long midPower, Set<Integer> positions, int firstPosition, int m) {
        int i = firstPosition;
        while (--m > 0) {
            i += midPower;
            if (!positions.contains(i)) {
                return false;
            }
        }
        return true;
    }

    private int[] getMinAndMaxVal(int[] positionArr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int position : positionArr) {
            max = Math.max(position, max);
            min = Math.min(position, min);
        }
        return new int[]{min, max};
    }


}
