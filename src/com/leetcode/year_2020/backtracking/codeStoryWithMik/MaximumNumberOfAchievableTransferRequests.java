package com.leetcode.year_2020.backtracking.codeStoryWithMik;

public class MaximumNumberOfAchievableTransferRequests {

    public static void main(String[] args) {
        MaximumNumberOfAchievableTransferRequests obj = new MaximumNumberOfAchievableTransferRequests();
        System.out.println(obj.maximumRequests(5, new int[][]{
                {0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}
        }));
    }

    public int maximumRequests(int n, int[][] requests) {
        int[] answer = new int[1];
        int[] balance = new int[n];
        backtrack(0, 0, requests, balance, answer);
        return answer[0];
    }

    private void backtrack(int requestIdx, int count, int[][] requests, int[] balance, int[] answer) {
        if (requestIdx == requests.length) {
            // Check if all of the balances are zero
            for (int i : balance) {
                if (i != 0) {
                    return;
                }
            }
            answer[0] = Math.max(answer[0], count);
            return;
        }

        // Skip transfer request
        // So we don't increase count variable
        backtrack(requestIdx + 1, count, requests, balance, answer);

        // Allow the request
        balance[requests[requestIdx][0]]--;
        balance[requests[requestIdx][1]]++;

        // We used request, so increase count
        backtrack(requestIdx + 1, count + 1, requests, balance, answer);

        balance[requests[requestIdx][0]]++;
        balance[requests[requestIdx][1]]--;
    }
}
