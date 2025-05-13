package com.leetcode.year_2020.prefix_sum_technique;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 *
 * @author neeraj on 19/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SubArraySumDivisibleByK {

    public static void main(String[] args) {
        System.out.println(subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
        System.out.println(subarraysDivByKNewOptimized(new int[]{4, 5, 0, -2, -3, 1}, 5));
        System.out.println(subarraysDivByKNewOptimized(new int[]{2, 3, 5, 4, 5, 3, 4}, 7));
    }

    /**
     * Simpler explanation in @2024
     * https://www.youtube.com/watch?v=QM0klnvTQzk
     */
    private static int subarraysDivByKNewOptimized(int[] nums, int k) {
        Map<Integer, Integer> remainderFrequencyMap = new HashMap<>();
        /**
         * Nums:  2  3   5   4   5   3   4
         * K = 7
         *
         * The solution is based on this  intuition that
         * if there exist a Sum which is divisible by K and leaves a remainder X (Lets call it S1=((K*n)+X))
         * Similarly there exist another sum which also when divisible by K leaves remainder X (S2=((k*m)+X)
         *
         * where n and m might be any multiple,
         *
         * ------------------S1|
         * --------------------|-------------------------------------------------s2--------------------
         * ====================>-------(s2-s1) will be divisible by K------------<=====================
         *
         * How ?
         *
         * S1=K*n+x
         * S2=K*m+x =====> S2-S1 = (Km+x-kn-x) ==> K*(m-n), Hence while calculating a running sum, if we encounter the remainder again,
         * we definitely know that there is a new sub-array in between who is divisible by K
         */
        remainderFrequencyMap.put(0, 1);  // O is a sub-array which is divisible by k
        int runningSum = 0;
        int remainder = 0;
        int total = 0;
        for (int n : nums) {
            runningSum += n;
            remainder = runningSum % k;
            remainder = remainder < 0 ? remainder + k : remainder; // We don't want a negative remainder, so it can easily be converted to positive by adding the mod
            // How ? Imagine a remainder = (-x) Negative
            // So  Sum = k*n-x !, Now add k and  -k into this  equation
            // K*n-x+k-k
            // k*(n-1)+(k-x) // Since we were doing Mod by k, so k will be greater than x
            // and hence you converted your negative remainder to a positive one by just adding the one whose divisibility aspect is being checked

            if (remainderFrequencyMap.containsKey(remainder)) {
                total += remainderFrequencyMap.get(remainder);
            }
            remainderFrequencyMap.put(remainder, remainderFrequencyMap.getOrDefault(remainder, 0) + 1);
        }
        return total;
    }

    public static int subarraysDivByK(int[] A, int K) {
        /**
         * This problem is the variation of {@link ContinuousSubarraySum} also known asSubArraySumMultipleOfK
         * here we have to count Number of SubArrays where sum(subArray)%K == 0;
         * I/p [4,5,0,-2,-3,1] k = 5.
         *
         * Now if you notice we need sum of all sub-arrays, calculating them again and again is very inefficient.
         * so we will use prefixSum tech to find out sum of any sub-array in constant time
         *
         *PrefixSum of above = : [4, 9, 9, 7, 4, 5].... Now if someone asks what is the Sum of SubArray[1, 2]
         * how can we calculate : Sum[SubArray[i,j]] = PrefixSum[j] - PrefixSum[i-1].
         * Sum[SubArray[1,2] = PrefixSum[2] - PrefixSum[0] = > 9 - 4 = 5 and if we see the original sub-array it's  [5,0] and sum is exactly 5.
         *
         * To calculate how many sub-arrays are divisible by K is logically equivalent to saying, how many ways can we
         * pair up all prefix sum pairs (i,j) where i < j such that (prefix[j] - prefix[i]) % K == 0.
         *
         * Now we want to check the divisibility, so we will take the Modulo of PrefixSum%k
         *
         * PrefixSum%K = [4,4,4,2,4,0]..... we always know whenever we take mod with anything we can get only 0 to mod-1 as the output.
         *
         * So (AnyItem) % 5 ===> can give us 5 possible values ... 0, 1, 2, 3, 4
         * Also items which do not need any pairing will go in bucket 0 since those items are directly divisible by k
         *
         * So we have 5 different buckets and all the mods divided in these buckets
         *   2      0      1     0     4      ====> Occurrences for each in PrefixSum%k
         * |  |   |  |   |  |  |  |  |  |
         * |  |   |  |   |  |  |  |  |  |
         *  0      1      2     3     4
         *
         *  Now only  thing left is to count all possible sub-arrays which is a combination
         *
         *  2c2  + 4C2  = 7
         *
         *  NCp = N*(N-1)/p
         */
        int prefixSum = 0;
        int[] buckets = new int[K]; // Bucket of size k with values 0 to k-1
        for (int i = 0; i < A.length; i++) {
            prefixSum += A[i];
            int bucket = prefixSum % K;
            if (bucket < 0) bucket += K; //we don't have any negative bucket so let's take the actual positive mod.
            buckets[bucket]++;
        }

        int count = 0;
        for (int items : buckets) {
            if (items > 1) {
                count += (items * (items - 1)) / 2; // Since we are looking for pair : nc2 = n*(n-1)/2;
            }
        }
        // Why adding bucket[0]....since this bucket will contain all those items which do not need pairing to be
        // divisible by k.
        return count + buckets[0];
    }
}
