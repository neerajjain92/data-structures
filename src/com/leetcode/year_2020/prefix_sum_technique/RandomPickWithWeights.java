package com.leetcode.year_2020.prefix_sum_technique;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/description/
 * <p>
 * 528. Random Pick with Weight
 * Most intuitive solution
 * https://www.youtube.com/watch?v=v-_aEMtgnkI&ab_channel=KnowledgeCenter
 */
@SuppressWarnings("JavadocLinkAsPlainText")
public class RandomPickWithWeights {

    public static void main(String[] args) {
        int[] quest = new int[]{1, 3, 4, 5, 2};
        Solution solution = new Solution(quest);
        // Calculate the probability of index
        int[] picked_index = new int[quest.length];
        for (int i = 0; i < 1000; i++) {
            picked_index[solution.pickIndex()]++;
        }
        // Now we have sum of the times it got picked
        int pickedFreqSum = Arrays.stream(picked_index).sum();
        float totalProbability = 0;
        for (int i = 0; i < picked_index.length; i++) {
            float probab = ((picked_index[i] / (float) pickedFreqSum) * 100);
            System.out.println("Index " + i + " and probability = " + probab);
            totalProbability += probab;
        }
        System.out.println("Total probability = " + totalProbability);
    }

    @SuppressWarnings("GrazieInspection")
    static class Solution {
        Random random = new Random();
        private final int[] prefixSumOfBuckets;
        private int sum = 0;

        public Solution(int[] w) {
            // We know that weights decide which index gets how much weightage
            // So if we have weights
            // [1, 3, 4, 5, 2]
            // Then basically we have buckets like this
            // [bucket_of_size_1]|[bucket_of_size_3]|[bucket_of_size_4]|[bucket_of_size_5]|[bucket_of_size_2]
            // now visually assume their bucket width based on their weight
            // How does it look on number line
            // [Hold weight upto 1, holdWeight from 2 till 4 (2, 3, 4), hold Weight from 5 till 8 (5, 6, 7, 8) and so on
            // [1   ,       4,         8,               13,      15]  ===> by the way notice this is just prefix sum
            //  [1]    [2,3,4] [5,6,7,8]   [9,10,11,12,13]   [14, 15]
            // Notice how their weight got assigned to their respective bucket boundaries according to their weightage
            // Now the job is simply to find out rolling a random and figuring out which bucket it belongs to
            prefixSumOfBuckets = new int[w.length];
            prefixSumOfBuckets[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                prefixSumOfBuckets[i] = prefixSumOfBuckets[i - 1] + w[i];
            }
            sum = prefixSumOfBuckets[prefixSumOfBuckets.length - 1];
        }

        public int pickIndex() {
            int randomVal = random.nextInt(sum) + 1;

            // Now just find out in which bucket this randomVal value belongs to
            // which is basically to find an upper bound in a sorted array (because prefix sum is always sorted)
            return findLowerBound(randomVal);
        }

        private int findLowerBound(int random) {
            int low = 0, high = prefixSumOfBuckets.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (prefixSumOfBuckets[mid] >= random) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }

        private int lowerBound(int []arr, int target) {
            int left = 0, right = arr.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if(arr[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        private int upperBound(int []arr, int target) {
            int left = 0, right = arr.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if(arr[mid] > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }
    }
}
