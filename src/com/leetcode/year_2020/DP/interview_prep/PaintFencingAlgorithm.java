package com.leetcode.year_2020.DP.interview_prep;

import com.util.LogUtil;

/**
 * https://www.geeksforgeeks.org/painting-fence-algorithm/
 * https://www.youtube.com/watch?v=deh7UpSRaEY
 *
 * @author neeraj on 06/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PaintFencingAlgorithm {

    public static void main(String[] args) {
        getNumberOfWaysToPaintFence(2, 3);
        getNumberOfWaysToPaintFence(2, 4);
        getNumberOfWaysToPaintFence(3, 2);
    }

    public static int getNumberOfWaysToPaintFence(int noOfPosts, int differentColors) {
        if (noOfPosts == 0) return 0; // We can't paint if there is no post.
        if (noOfPosts == 1)
            return differentColors; // you can take any of the colors, so we have k differentColors choices.

        /**
         * // Now when we have 2 posts, we can either paint the 2nd POST as same as the previous ones
         *         // or to make it different.
         *
         * A    B   and colors(Red, Blue, Green and Yellow)
         *
         * if we take the same color on Bth POST we have k choices. (since 2 adjacent can be of same colors.)
         *
         * Same = differentColors;
         *
         * and if we take a different color on POST B than  Ath. we have 1 less color to paint on B
         *
         * Different = differentColors * (differentColors-1) choices
         */

        int SAME = differentColors;
        int DIFFERENT = differentColors * (differentColors - 1);

        for (int i = 3; i <= noOfPosts; i++) {

            /**
             * Now also we have 2 choices to make either make the color same as the i-1th POST
             * or to make it different.
             */
            // When making different color
            /**
             * we have 2 more thing to consider here, when previous 2 were same or different.
             */
            int previous_diff = DIFFERENT;
            int previous_same = SAME;

            // when previous 2 were same to current different we can only choose from k-1.
            // or when previous 2 were different then we have all k different color choices.
            DIFFERENT = (previous_same * (differentColors - 1)) + (previous_diff * (differentColors - 1));

            // Now when we decide that we will do the current color as same.
            // it is only possible when the previous 2 colors were different else, if previous 2 colors were
            // same we can't make this one same since it will break the constraint.
            SAME = (previous_diff) * 1; // Only 1 choice which is whatever i-1th post choose.
        }
        LogUtil.logIt("Total Number of ways when N = " + noOfPosts + " K = " + differentColors + " is " + (SAME + DIFFERENT));
        return SAME + DIFFERENT;
    }
}
