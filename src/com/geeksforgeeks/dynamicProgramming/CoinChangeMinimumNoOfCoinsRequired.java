package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.ArrayRotation;
import com.geeksforgeeks.array.Rotate2DMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChangeMinimumNoOfCoinsRequired {
    public static void main(String[] args) {
//        System.out.println("Minimum Coins required to form a sum of 11 with {1,5,6,8} is " + getMinimumNoOfCoinChangeRequired(new int[]{1, 5, 6, 8}, 11));
//        System.out.println("Minimum Coins required to form a sum of 13 with {7,2,3,6} is " + getMinimumNoOfCoinChangeRequired(new int[]{7, 2, 3, 6}, 13));
//        System.out.println("Minimum Coins required to form a sum of 30 with {25, 10, 5} is " + getMinimumNoOfCoinChangeRequired(new int[]{25, 10, 5}, 30));
//        System.out.println("Minimum Coins required to form a sum of 11 with {9, 6, 5, 1} is " + getMinimumNoOfCoinChangeRequired(new int[]{9, 6, 5, 1}, 11));


        getMinimumNoOfCoinsRequiredToAchieve(13,new int[]{7,2,3,6});
        getMinimumNoOfCoinsRequiredToAchieve(30,new int[]{25,10,5});
        getMinimumNoOfCoinsRequiredToAchieve(11,new int[]{1,5,6,9});
    }

    public static int getMinimumNoOfCoinChangeRequired(int[] coins, int total) {
        Arrays.sort(coins);
        int[][] minChange = new int[coins.length][total + 1];


        for (int i = 0; i <= total; i++) {
            if (i % coins[0] == 0) {
                minChange[0][i] = i / coins[0];
            } else {
                minChange[0][i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < minChange[i].length; j++) {
                if (coins[i] > j) {
                    minChange[i][j] = minChange[i - 1][j];
                } else {
                    minChange[i][j] = minChange[i - 1][j] < minChange[i][j - coins[i]] ? minChange[i - 1][j] : minChange[i][j - coins[i]] + 1;
                }
            }
        }
        Rotate2DMatrix.print2DArray(minChange);
        return minChange[coins.length - 1][total];
    }

    /**
     * This solution works for all denomination
     *
     * @param sum
     */
    public static void getMinimumNoOfCoinsRequiredToAchieve(int sum, int[] denominations) {
        int[] minCoins = new int[sum + 1];
        int [] actualCoinUsed = new int[sum + 1];

        // For a Sum of 0 you need 0 coins
        minCoins[0] = 0;
        actualCoinUsed[0] = 0;

        for(int i=1;i<minCoins.length;i++) {
            minCoins[i] = Integer.MAX_VALUE - 100;
        }

        for (int i = 0; i < denominations.length; i++) {
            for (int j = 1; j < minCoins.length; j++) {

                if(denominations[i] <= j) {
                    int sumWithCoin = 1 + minCoins[Math.abs(j - denominations[i])];
                    if( sumWithCoin < minCoins[j]) {
                        minCoins[j] = sumWithCoin;
                        actualCoinUsed[j] = denominations[i];
                    }
                }
            }
            //ArrayRotation.printArray(minCoins);
        }
        ArrayRotation.printArray(minCoins);

        System.out.println("Minimum Coins used to make sum "+sum+" is "+minCoins[sum]);
        ArrayRotation.printArray(actualCoinUsed);

        System.out.println("Coins Used are");
        List<Integer> coinsUsed = new ArrayList<>();
        int sumCopy = sum;
        for(int i=sum;i>0;) {
            coinsUsed.add(actualCoinUsed[i]);
            sumCopy = sumCopy - actualCoinUsed[i];
            i = sumCopy;
        }
        System.out.println(coinsUsed);
    }
}
