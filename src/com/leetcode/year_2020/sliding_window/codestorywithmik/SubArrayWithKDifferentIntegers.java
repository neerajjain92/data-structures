package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.HashMap;
import java.util.Map;

public class SubArrayWithKDifferentIntegers {

    public static void main(String[] args) {
        SubArrayWithKDifferentIntegers obj = new SubArrayWithKDifferentIntegers();
        System.out.println(obj.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
        System.out.println(obj.subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));

        System.out.println(obj.atMostKDistinctOnePass(new int[]{1, 2, 1, 2, 3}, 2));
        System.out.println(obj.atMostKDistinctOnePass(new int[]{1, 2, 1, 3, 4}, 3));
    }

    /*
     * Intuition is simple(Not really simple but with observation its fun to crack
     * TL:Dr; we basically calculate total subArrays ending at j (where distinct elements are
     * less than equal to k with (j - i  + 1)
     *
     * So for k = 2; and index=3;
     * if we get [1 2 1 2], [2 1 2], [1 2], [2]
     * these are all subArrays where we have <= k distinct elements
     * if i can somehow calculate or do the same activity for k=1
     * and subtract the count of distinct(with <=k=2) - count of distinct(with <=k=1)
     * Yipeeeee we got the answer.
     *
     * [1,2,1,2,3] k = 2
     *  0 1 2 3 4
     * I know one thing for sure, if i keep a Map with item and frequency
     * so at any point in time during iteration i can easily tell the total distinct items
     * in the sliding window with help of Map#size right ??
     *
     * and also one more thing we know that to get subArrays ending at index j
     * is simple (j - i + 1) or (end - beg + 1).
     *
     * [ 1,  2,  1,  2,  3] k = 2
     *  b|e                       ----> count(items) = 1, so total = e-b+1 = 1
     *       e                    ====> e - b + 1 = 2
     *           e                ====> 3
     *               e            ====> 4
     *                   e        ====> Size(items) = 3; this makes sliding window invalid
     *                                  So shrink
     *      b            e         ====> still size(items) = 3
     *           b       e
     *               b   e        ===> Yes now again 2, so e-b+1 => 2
     *
     *  So with k=2; we got 1+2+3+4+2 => 12 count, do the same with k=1
     *  and subtract that from 12 is your answer
     *
     * Now similarly when k=3, we calculate all SubArray for 3 and then subtract the k=2, and not
     * go forward with k=1 why????? because k=2 already contains all subArrays in which total distinct
     * items in the subArrays are <= 2 (which includes 2 and 1 distinct alls)
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    private int atMostKDistinct(int[] nums, int k) {
        Map<Integer, Integer> itemFreq = new HashMap<>();
        int total = 0, end = 0, begin = 0;

        while (end < nums.length) {
            int item = nums[end];
            itemFreq.put(item, itemFreq.getOrDefault(item, 0) + 1);
            while (itemFreq.size() > k) {
                int itemAtBegin = nums[begin];
                itemFreq.put(itemAtBegin, itemFreq.get(itemAtBegin) - 1);
                if (itemFreq.get(itemAtBegin) == 0) {
                    itemFreq.remove(itemAtBegin);
                }
                begin++;
            }
            total += end - begin + 1; // total valid subArrays with distinct <=k ending at end
            end++;
        }
        return total;
    }

    /*
     * Intuition is very very tricky in this but easy to understand
     * Take Example
     *
     * [3 2  1  1  1  2]   For k=2
     *             b  e   ===> Initially asssume begin and ending standing at these positions
     *                      Do you think it's the smallest valid SubArray, yes right ?? yes
     *
     *                      So now let's check how much we can expand this smallest valid subArray
     *                      that it doesn't break k=2 distinct elements in subArray condition.
     *          b     e    ===> [1 1 2] still valid
     *       b        e    ===> [1 1 1 2] still valid
     *    b           e    ===> [2 1 1 1 2] still valid
     * b              e    ===> Now invalid [since 3 distinct elements in the subArray]
     *
     * So basically in the smallest valid subArray [1 2] how much you can extend it ?
     * by 3 right which is
     *                  [1 1 2]
     *                  [2 1 1 2]
     *                  [1 2 1 1 2]
     *
     * and if i keep an index at 1st index which is pointing to 2 and call if begin_start
     *
     * So begin - begin_start which is 3 gives you these total extra 3 valid subArray
     * and it make sense as well right from a validSmallestSubArray we keep extending until it's valid
     *
     * Now instead of going from end just take it from front how we do this in usual sliding window
     * We will maintain 3 pointers, all pointing to 0 index in start
     * beg, end, beg_start shorted to b|e|b_s
     * [  1       2       1       2     3]
     *  b|e|b_s                             ==> Not yet valid
     *   b|b_S    e                         ==> Valid subArray and smallest one so begin-begin_start+1
     *    b_s     b       e                 ==> same valid, but are you the smallest subArray no right so keep shrinking
     *                                          Now b and e are at smallest SubArray, so how much do you think we can expand
     *                                          this on the left so that this subArray is valid ending at end or j
     *                                          (begin - begin_start) + 1 (??? +1 for the subArray ending at j)
     *                                           [1 2 1] and [2 1]
     *                    b       e         ==> still valid, but not the smallest so shring
     *                                           (2 - 0) + 1 ==> 3
     *                                          [1 2 1 2],[2 1 2], [1 2] (All valid ending at j)
     *                                  e   ==> Now it's invalid, so we reset begin_start to the begin
     *                                          because none of the previous can match
     */
    private int atMostKDistinctOnePass(int[] nums, int k) {
        int begin = 0, end = 0, begin_start = 0;
        Map<Integer, Integer> itemFreq = new HashMap<>();
        int total = 0;
        while (end < nums.length) {
            itemFreq.put(nums[end], itemFreq.getOrDefault(nums[end], 0) + 1);

            // Handle invalid SubArray
            while (itemFreq.size() > k) {
                int itemAtBegin = nums[begin];
                itemFreq.put(itemAtBegin, itemFreq.get(itemAtBegin) - 1);
                if (itemFreq.get(itemAtBegin) == 0) {
                    itemFreq.remove(itemAtBegin);
                }
                begin++;
                begin_start = begin; // Since SubArray was invalid
            }

            // Handle to find the smallest SubArray
            while (itemFreq.get(nums[begin]) > 1) {
                int itemAtBegin = nums[begin];
                itemFreq.put(itemAtBegin, itemFreq.get(itemAtBegin) - 1);
                begin++;
            }
            if (itemFreq.size() == k) {
                total += 1 + (begin - begin_start);
            }
            end++;
        }
        return total;
    }

}
