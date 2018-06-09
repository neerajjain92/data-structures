package com.datastructures.mustDoInterviewQuestions.array;

/**
 * Created by jaine03 on 23/07/17.
 */
public class StockBuyAndSell {

    public static void main(String[] args) {
//        getMaxProfit(new int[]{100, 180, 260, 310, 40, 535, 695});
//        getMaxProfit(new int[]{50, 100, 90, 500});
//        getMaxProfit(new int[]{1000, 10, 5000});
        getMaxProfit(new int[]{1, 1, 1000, 1, 1, 100000});
        getMaxProfit(new int[]{4, 3, 8, 10});
    }

    public static void getMaxProfit(int[] stockRates) {
        System.out.println("===================NEW TEST CASE===============");
        Boolean isPurchased = false;
        int purchasedStockIndex = -1;
        for (int i = 0; i < stockRates.length - 1; i++) {

            // Check if any Stock already purchased
            if (!isPurchased && stockRates[i] < stockRates[i + 1]) {
                isPurchased = true;
                purchasedStockIndex = i;
            }

            if (isPurchased && stockRates[i] > stockRates[i + 1]) {
                System.out.println("Purchased --> " + purchasedStockIndex + " Sold -->" + i);
                isPurchased = false;
            }
        }
        if (isPurchased)
            System.out.println("Purchased --> " + purchasedStockIndex + " Sold -->" + (stockRates.length - 1));
    }
}
