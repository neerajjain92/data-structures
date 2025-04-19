package com.leetcode.year_2020.Greedy.coding_with_mik;

public class JumpGame2 {

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jump(new int[]{2, 3, 0, 1, 4}));
        System.out.println(jump(new int[]{3, 2, 1, 0, 4}));
    }

    /**
     * Sexy Intuituion
     * https://www.youtube.com/watch?v=7SBVnw7GSTk&ab_channel=takeUforward
     * <p>
     * arr = [2 3 1 4 1 1 1 2]
     * <p>
     * So the intuition is this, we basically check ranges, with 1 jump what ranges are we reaching at
     * Intially l = 0, r = 0 [represent range]
     * <p>
     * 2   3   1   4   1   1   1   2
     * L-R
     * --> Calculate fathest you can reach from this index = 0 + 2
     * ---> So R goes to that and L goes to R --> and you need 1 jump from here
     * 2   3   1   4   1   1   1   2
     * L   R
     * ---> [Same calculation farthers from here is 1 index + 3 = 4
     * ---> R goes to index 4 and L goes to R+1 | Another jump+1 to reach 4
     * 2   3   1   4   1   1   1   2
     * L   R    ==========> index (3) +4 ==> 7  | Another jump+1
     * <p>
     * 2   3   1   4   1   1   1   2
     * L       R ===> So in 3 jumps minimum you reached
     */
    public static int jump(int[] nums) {
        int left = 0, right = 0, jump = 0;
        while (right < nums.length - 1) {
            int farthest = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            if (farthest == right) {
                return -1; // meaning you couldn't reach out the ranges
            }
            jump++;
            left = right + 1;
            right = farthest;
        }
        return jump;
    }
}
