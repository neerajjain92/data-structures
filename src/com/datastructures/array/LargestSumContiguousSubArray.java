package com.datastructures.array;

/**
 * Created by jaine03 on 02/05/17.
 *
 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 */
public class LargestSumContiguousSubArray {

    public static void main(String[] args) {
        int [] sample = new int[]{-2,-3,4,-1,-2,1,5,-3};

        System.out.println(getMaxSumWithDP(sample));
    }

    public static int getMaxSum(int [] sample){
        int maxTillNow = 0;
        int maxEndingHere = 0;
        int start=0,end=0;

        for(int i=0;i<sample.length;i++){
            maxEndingHere+= sample[i];

            if(maxEndingHere < 0) {
                maxEndingHere = 0;
                start = i+1;
            }

            if(maxTillNow < maxEndingHere) {
                maxTillNow = maxEndingHere;
                end = i;
            }
        }
        System.out.println("Starts at "+start);
        System.out.println("Ends at "+end);

        return maxTillNow;
    }

    public static int getMaxSumWithDP(int []sample){
        int maxEndingHere = sample[0];
        int maxTillNow = sample[0];

        for(int i=0;i<sample.length;i++){
            maxEndingHere = Math.max(sample[i],maxEndingHere+sample[i]);
            maxTillNow = Math.max(maxEndingHere,maxTillNow);

            System.out.println("Max Ending Here is "+maxEndingHere);
            System.out.println("Max Till Now  Here is "+maxTillNow);
        }
        return maxTillNow;
    }
}
