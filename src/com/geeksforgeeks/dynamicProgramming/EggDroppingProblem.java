package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.Rotate2DMatrix;

public class EggDroppingProblem {

    public static void main(String[] args) {
        System.out.println("Minimum No of Tries is " + getMinimumNoOfTries(2, 100));
        System.out.println("Minimum No of Tries is " + getMinimumNoOfTries(2, 36));
        System.out.println("Minimum No of Tries is " + getMinimumNoOfTries(2, 50));
    }

    public static int getMinimumNoOfTries(int eggs, int floors) {

        int[][] eggFloor = new int[eggs][floors + 1];

        // With 1 egg it will take N tries to find out the thresh-hold floor
        for (int i = 1; i <= floors; i++) {
            eggFloor[0][i] = i;
        }
        //Rotate2DMatrix.print2DArray(eggFloor);
        for (int i = 1; i < eggs; i++) { // Eggs starting from 2 as for 1 egg we have already calculated

            for (int j = 1; j <= floors; j++) { // Start of the section

                eggFloor[i][j] = Integer.MAX_VALUE - 100;
                // Now we have to test all floors up to  jth floor
                for (int x = 1; x <= j; x++) {

                    int minTries = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]); // 1 + Max( if egg breaks , if egg doesn't break)

                    if (minTries < eggFloor[i][j]) {
                        eggFloor[i][j] = minTries;
                    }
                }
            }
        }

        Rotate2DMatrix.print2DArray(eggFloor);
        return eggFloor[eggs - 1][floors];

    }
}
