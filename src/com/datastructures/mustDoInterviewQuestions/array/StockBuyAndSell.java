package com.datastructures.mustDoInterviewQuestions.array;

/**
 * Created by jaine03 on 23/07/17.
 */
public class StockBuyAndSell {

    public static void main(String[] args) {
        //getMaxProfit(new int[]{100, 180, 260, 310, 40, 535, 695});
        getMaxProfit(new int[]{50, 100, 90, 500});
    }

    public static void getMaxProfit(int []stockRates){
        Boolean isPurchased = false;
        int purchasedStockIndex = -1;
        for(int i=0;i<stockRates.length-1;i++){

            // Check if any Stock already purchased
            if(!isPurchased && stockRates[i] < stockRates[i+1]){
                isPurchased = true;
                purchasedStockIndex = i;
            }

            if(isPurchased && stockRates[i] > stockRates[i+1]){
                System.out.println("Purchased --> "+purchasedStockIndex+" Sold -->"+i);
                isPurchased = false;
            }
        }
        if(isPurchased)
            System.out.println("Purchased --> "+purchasedStockIndex+" Sold -->"+(stockRates.length-1));
    }
}
