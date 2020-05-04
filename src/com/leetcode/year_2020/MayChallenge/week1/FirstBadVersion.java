package com.leetcode.year_2020.MayChallenge.week1;

/**
 * @author neeraj on 04/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FirstBadVersion {

    public static void main(String[] args) {
        System.out.println(firstBadVersion(50));
    }

    public static int firstBadVersion(int n) {
        return binarySearch(1, n);
    }

    public static int binarySearch(int low, int high) {
        int mid = low + (high - low)/2;
        if(low >= high) {
            return low;
        }
        if(isBadVersion(mid)) {
            return binarySearch(low, mid);
        } else {
            return binarySearch(mid+1, high);
        }
    }

    private static boolean isBadVersion(int mid) {
        return mid >=3;
    }
}
