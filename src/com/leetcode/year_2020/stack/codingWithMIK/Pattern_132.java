package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.Stack;

public class Pattern_132 {

    public static void main(String[] args) {
        Pattern_132 obj = new Pattern_132();
        System.out.println(obj.find132pattern(new int[]{-2, 1, 1}));
        System.out.println(obj.find132pattern(new int[]{1, 2, 3, 4}));
        System.out.println(obj.find132pattern(new int[]{3, 1, 4, 2}));
        System.out.println(obj.find132pattern(new int[]{-1, 3, 2, 0}));
        System.out.println(obj.find132pattern(new int[]{1, 0, 1, -4, -3}));
    }

    /*
     * If we have 3 indexes
     * i j and k
     * <p>
     * Make sure                                 i    <   j    <  k
     * and numbers on those index               nums1   nums2    nums3
     * The number should be in this order       num1  < nums3 <  nums2   [Notice how i < j < k in the index but from values perspective it's i < k < j]
     * <p>
     * We know one thing for sure nums2 should always be largest, and then nums3 should be large enough to be small than nums2 but biggest than nums1
     * <p>
     * to satisfy i < j < k, we should always start from the right, so we never break that constraint
     * and to satisfy their number which is num1 < num3 < num2, we make sure num2 is always largest
     * <p>
     * Story:
     * Let's keep 2 variable (nums2, nums3) where nums2 will always hold the largest
     * <p>
     * 1. If item[right] > nums2: Push num2 value to num3 first since it can become a good candidate
     * and assign largest to num2
     * 2. if you find an item < num3, so we found a pair num1 < num3 < num2
     *
     * 3. If you  find a number item[right] < num2, in this case don't directly assign it to num3 but keep
     * it somewhere stored because
     *
     * I'll show you why for point3 you can't/shouldn't blindly put it into NUM_3 and what harm it will cause ?
     *
     *  0  1  2  3  4
     * [3, 5, 0, 3, 4]
     *              i                                Num_3 = -INFINITY, NUM_2=-INFINITY
     *                          4 > NUM_2, So NUM_3 will take -infinity and NUM_2 = 4
     *           i              3 NOT > NUM_2, but > NUM_3, so update NUM_3 = 3 and NUM_2 = 4
     *        i                Now 0 < NUM_3 directly so basically this should be our answer right but check
     *
     *                          NUM_1 = 0 , NUM_3 = 3 and NUM_2 = 4
     *                          we are choosing index [2, 3, 4] So i < j < k is true
     *                          but is nums[i] < nums[k] < nums[j] this true ?
     *                          NUMS[2] = 0, NUMS[3] = 3, NUMS[4] = 4
     *                          can you put them like this that nums[4] < nums[3] no right 4 is not < 3
     *
     * Now you know why simply updating won't work ? so you need to store the immediate smaller element
     * than largest which is NUMS_2 so when your i reached to value of 5, we found multiple candidates
     * which could be the part of NUM_3 but out of them the best one was 4.
     *
     * So now when you find a number < NUMS_2, you should put that into stack, because it may or may not
     * contribute in future, so to keep things for future a Stack works best
     * Now when you find a number > peek() of stack, keep popping until you find either  stack is empty or you find a number
     * greater than your current index
     *
     * So now come back to this again
     *
     *  0  1  2  3  4
     * [3, 5, 0, 3, 4]             ==> NUM_3 = 4
     *              i              Stack empty so push into stack                 : Stack---> Top[4 ]Bottom
     *           i                 Keep popping until you found a number > than 3 : Stack---> Top[3 4]Bottom
     *       i                     Stack---> Top[0 3 4]Bottom
     *     i                       Now 5 > Peek, so keep popping and update NUM_3
     *                             pop = 0 NUM_3 = 0 Stack---> Top[3 4]Bottom
     *                             pop = 3 NUM_3 = 3 Stack---> Top[4]Bottom
     *                             pop = 4 NUM_3 = 4 Stack---> Top[EMPTY]Bottom
     * i                           3 < NUM_3 which is 4, so you found a pair which is at 0, 1, 4, Notice how 3 < 4 < 5
     *
     */
    public boolean find132pattern(int[] nums) {
        int NUM_3 = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int item = nums[i];
            if (item < NUM_3) {
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < item) {
                NUM_3 = stack.pop();
            }
            stack.push(item);
        }
        return false;
    }
}
