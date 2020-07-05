package com.leetcode.year_2020.july_challenge.week1;

/**
 * @author neeraj on 05/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class HammingDistance {

    public static void main(String[] args) {
        hammingDistance(1, 4);
        hammingDistanceUsingXOR(1, 4);
    }

    public static int hammingDistanceUsingXOR(int x, int y) {
        /**
         * Since XOR only gives 1 for different bits
         */
        int xor = x ^ y;
        int hd = 0;
        while (xor > 0) {
            if (xor % 2 == 1) hd++;
            xor /= 2;
        }
        System.out.println(hd);
        return hd;
    }

    public static int hammingDistance(int x, int y) {
        int binary_x[] = getBinary(x);
        int binary_y[] = getBinary(y);

        int hd = 0;
        for (int i = 0; i < binary_x.length; i++) {
            if (binary_x[i] != binary_y[i]) {
                hd++;
            }
        }
        System.out.println(hd);
        return hd;
    }

    public static int[] getBinary(int x) {
        int[] binary = new int[1000];
        int i = 0;
        while (x > 0) {
            binary[i++] = x % 2;
            x = x / 2;
        }
        return binary;
    }
}
