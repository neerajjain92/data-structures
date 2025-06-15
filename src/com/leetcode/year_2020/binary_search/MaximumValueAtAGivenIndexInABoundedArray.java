package com.leetcode.year_2020.binary_search;

/**
 * https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/description/
 */
public class MaximumValueAtAGivenIndexInABoundedArray {

    public static void main(String[] args) {

    }

    class Solution {
        public int maxValue(int n, int index, int maxSum) {
            // Classic Binary Search on answer
            int low = 1; // since nums[i] >= 1;
            int high = maxSum; // At any point in time
            int answer = -1;
            while (low <= high) {
                int mid_num = low + (high - low) / 2;
                if (canPlace(mid_num, n, index, maxSum)) {
                    answer = mid_num;
                    low = mid_num + 1;
                } else {
                    high = mid_num - 1;
                }
            }
            return answer;
        }

        private boolean canPlace(long mid_num, int n, int index, int maxSum) {
            // What we want is that
            // left_part_sum + mid_num + right_part_sum <= maxSum;
            // So we need to get access to left_part_sum and right_part_sum in less than O(N) right
            // Let's do some analysis
            //                index
            // [                X                ]
            //   0   1   2   3  4  5  6  7  8  9
            // If i put X at index then. i have to put X-1, X-2 and so on
            // Assume index = 4, then total elements on the left side would be 4 i.e left_total_elements = index
            // and elements on the right side is N - index - 6 ==> 10 - 4 - 1 ==> 5 which is true, you have from index [5 to 9]

            // Awesome so now i have to put remaining on the left
            // [X-4, X-3, X-2, X-1, X,                          ]
            // Taking X common, that's (X-4) + (X-3) + (X-2) + (X-1)
            // 4X - (1+2+3+4)
            // isn't this just sum of the first n natural numbers  n*(n+1)/2
            // 4X - (4*5)/2 ==> 4X - 10

            // okay but here is another problem, you can only put positive number in the array and that too not less than 1
            // So assume this X is 3
            // [               X(3)                ]
            //   0   1   2   3  4  5  6  7  8  9
            // So now you will put these in 0-3 index
            // [ 1   1   1   2  3                  ] ----------> value
            //   0   1   2   3  4  5  6  7  8  9 ============>> INdex
            //  So now till index 1-2 everything is fine these are 1st 2 natural number
            // but before that we are just keeping 1, so you can't put
            // index*mid_num -((index*(index+1))/2);
            // so the sum is correct only till value 1, so how about we take the value at index, as the index
            // where sum of n natural number can be producted

            // So with this value at index 4 was 3
            // So 3*3-(3*4/2) ==> 9 - 6 ==> 3; and that's correct only check sum of index 2 to 3
            // and for remaining just add bloody 1s sum which is
            // (Actual_Index - Actual_Mid_Value) + 1 ==> will be the total additional elements which will just contain num
            // so then formula becomes
            // <<<<<<-- SUM OF ONLY 1s---->>>>>   <<<<<<<<<<< Sum of 1st n natual numbers in left
            //                                       if you are placing 3 at index 4,so max left me sirf 1 and 2 hi ho skta hai right
            //                                       So total elements in the left is 3-1 i.e 2
            //                                      and formula becomes val_in_left*mid_num (2 * 3) - (val_in_left*val_in_left+1)/2
            // (index-actual_mid_num)+1         +  [actual_mid_num * (actual_mid_num-1) - (actual_mid_num-1_

            long left_count = Math.min(index, mid_num - 1); // since left me total index elements ho skte hai
            long extra_ones_to_be_added_in_left = index - left_count;

            long leftSum = getSumByFormula(left_count, mid_num) + extra_ones_to_be_added_in_left;

            int total_elements_in_right = n - index - 1;
            long right_count = Math.min(total_elements_in_right, mid_num - 1);
            long extra_ones_to_be_added_in_right = total_elements_in_right - right_count;

            long rightSum = getSumByFormula(right_count, mid_num) + extra_ones_to_be_added_in_right;

            return leftSum + mid_num + rightSum <= maxSum;

        }

        private long getSumByFormula(long count, long x) {
            // This is the same formula
            // [X-4, X-3, X-2, X-1, X,                          ]
            // Taking X common, that's (X-4) + (X-3) + (X-2) + (X-1)
            // 4X - (1+2+3+4)
            // isn't this just sum of the first n natural numbers  n*(n+1)/2
            // 4X - (4*5)/2 ==> 4X - 10
            return (count * x) - (count * (count + 1)) / 2;
        }

        private boolean canPlaceBruteForce_O_N_FOR_EVERY_CALL(int mid_num, int n, int index, int maxSum) {
            // Let's place mid at index
            maxSum -= mid_num;

            // Checking pyramid shape
            //     /\
            //.   /. \
            //.  /.   \
            // So try both ends

            //.        If i select 4 at the index
            // so let's try to put 3 on left of it, then i should subtract -3 from maxSum and not just 1

            // Going left side
            int i = index - 1;
            int sum = mid_num - 1;
            while (i >= 0) {
                maxSum -= Math.max(sum, 1);
                sum--;
                if (maxSum < 0) return false;
                i--;
            }

            i = index + 1;
            sum = mid_num - 1;
            while (i < n) {
                maxSum -= Math.max(sum, 1);
                sum--;
                if (maxSum < 0) return false;
                i++;
            }
            return true;
        }
    }
}
