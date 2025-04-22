package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

/**
 * 135. Candy
 * https://leetcode.com/problems/candy/description/
 * <p>
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 */
public class Candy {

    public static void main(String[] args) {
        Candy candy = new Candy();
        System.out.println(candy.candy(new int[]{1, 2, 10, 10, 10, 2, 1}));
        System.out.println(candy.candy(new int[]{1,3,4,5,2}));
    }

    /*
     * Wrong intuition : 1
     * 1-> Initialize the result array with 1 candies to each student
     * 2-> Iterate from left to right and check on both ends(i-1 and i+1) if you are greater than any of these increment the count from their respective less candidate +1
     *      which is if (rating[i] > rating[i-1) candy[i-1] + 1 and same for right
     * 3-> where it will fail
     *      [ 1, 2, 10, 10, 10, 2, 1] ==> if you notice last 3 entries 10 and 2 both are > than their right so see the issue now if you iterate from left to right
     *
     *      [1, 1,  1,  1,  1, 1, 1] ==> step 1
     *       i                                     (Skip since not greater than any) so [1, 1,  1,  1,  1, 1, 1]
     *          i                                  (increment since > than i-1) so [1, 2,  1,  1,  1, 1, 1]
     *              i                              (increment since > than i-1) so [1, 2,  3,  1,  1, 1, 1]
     *                  i                           (Skip)
     *                     i                       (increment since > than i+1) so [1, 2,  3,  1,  2, 1, 1]
     *                        i                    (increment since > than i+1) so [1, 2,  3,  1,  2, 2, 1]
     *                           i                 (Skip)
     *
     * Final[1, 2,  3,  1,  2,  2, 1] [Notice how Student with weight 10 and 2 both had 2 candies which is wrong]
     *
     * Hence you shouldn't just check for both ends in one shot but instead walk left to right first and then from right to left
     */
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        // Left to right
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Right to left
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int sum = 0;
        for (int i : candies) {
            sum += i;
        }
        return sum;
    }

}
