package com.leetcode.year_2020.backtracking.codeStoryWithMik;

/**
 * https://leetcode.com/problems/fair-distribution-of-cookies/description/
 * 2305. Fair Distribution of Cookies
 * Medium
 * Topics
 * Companies
 * Hint
 * You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag. You are also given an integer k that denotes the number of children to distribute all the bags of cookies to. All the cookies in the same bag must go to the same child and cannot be split up.
 * <p>
 * The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.
 * <p>
 * Return the minimum unfairness of all distributions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: cookies = [8,15,10,20,8], k = 2
 * Output: 31
 * Explanation: One optimal distribution is [8,15,8] and [10,20]
 * - The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
 * - The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
 * The unfairness of the distribution is max(31,30) = 31.
 * It can be shown that there is no distribution with an unfairness less than 31.
 * Example 2:
 * <p>
 * Input: cookies = [6,1,3,2,2,4,1,2], k = 3
 * Output: 7
 * Explanation: One optimal distribution is [6,1], [3,2,2], and [4,1,2]
 * - The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
 * - The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
 * - The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
 * The unfairness of the distribution is max(7,7,7) = 7.
 * It can be shown that there is no distribution with an unfairness less than 7.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= cookies.length <= 8
 * 1 <= cookies[i] <= 105
 * 2 <= k <= cookies.length
 */
public class FairDistributionOfCookies {

    public static void main(String[] args) {
        FairDistributionOfCookies obj = new FairDistributionOfCookies();
        System.out.println(obj.distributeCookies(new int[]{8, 15, 10}, 2));
        System.out.println(obj.distributeCookies(new int[]{8, 15, 10, 20, 8}, 2));
        System.out.println(obj.distributeCookies(new int[]{6, 1, 3, 2, 2, 4, 1, 2}, 3));
    }

    public int distributeCookies(int[] cookies, int k) {
        int[] answer = {Integer.MAX_VALUE};
        backtrack(0, cookies, new int[k], k, answer);
        return answer[0];
    }

    private void backtrack(int cookieIndex, int[] cookies, int[] distribution, int k, int[] answer) {
        if (cookieIndex == cookies.length) {
            answer[0] = Math.min(answer[0], findMax(distribution));
            return;
        }

        // Now the cookie you have
        int cookie = cookies[cookieIndex];
        // we should try and distribute to each kth student to see if this helps
        for (int i = 0; i < k; i++) {
            distribution[i] += cookie;
            backtrack(cookieIndex + 1, cookies, distribution, k, answer);
            distribution[i] -= cookie;
        }
    }

    private int findMax(int[] distribution) {
        int max = Integer.MIN_VALUE;
        for (int i : distribution) {
            max = Math.max(max, i);
        }
        return max;
    }
}
