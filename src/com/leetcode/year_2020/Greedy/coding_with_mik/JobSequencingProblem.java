package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
 * https://www.youtube.com/watch?v=QbwltemZbRg&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=6&ab_channel=takeUforward
 */
public class JobSequencingProblem {

    public static void main(String[] args) {
        System.out.println(jobSequencing(new int[]{4, 1, 1, 1}, new int[]{20, 10, 40, 30}));
        System.out.println(jobSequencing(new int[]{2, 1, 2, 1, 1}, new int[]{100, 19, 27, 25, 15}));
        System.out.println(jobSequencing(new int[]{3, 1, 2, 2}, new int[]{50, 10, 20, 30}));
    }

    /**
     * Intuition is quite simple, get maximum profit by scheduling jobs and 1 job takes whole day
     * so how about ordering the jobs in descending order of profit and we schedule the highest profit jobs first
     * but if you know you still have time left in deadline so won't you prefer doing the jobs whose deadline is earlier
     * <p>
     * That's exactly we are going to do, based on deadline find out the max deadline available
     * deadline[] = [4, 1, 1, 1], profit[] = [20, 10, 40, 30]
     * <p>
     * Construct an array for placement of jobs of the size of max deadline(4) [-1, -1, -1, -1, -1] [0 based indexing] (-1 represent the slot not allocated)
     * <p>
     * Sort [(40, 1), (30, 1), (20, 4), (10, 1)]
     * <p>
     * -1  -1  -1  -1
     * _   _   _   _
     * 1   2   3   4
     * <p>
     * Now first job can only be placed on index 1
     * <p>
     * J1          J3
     * _   _   _   _
     * 1   2   3   4
     * <p>
     * So we could only place J1(40)+J3(20) = > 60
     */
    public static ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int maxDeadline = deadline[0];
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < deadline.length; i++) {
            maxDeadline = Math.max(maxDeadline, deadline[i]);
            Pair pair = new Pair(i + 1, deadline[i], profit[i]);
            pairs.add(pair);
        }

        pairs.sort((p1, p2) -> p2.profit - p1.profit); // Sort in descending order
        int[] jobSequencing = new int[pairs.size() + 1]; // +1 to handle 1 based indexing
        Arrays.fill(jobSequencing, -1);
        int totalProfit = 0, totalJob = 0;
        for (Pair pair : pairs) {
            int deadlineValue = pair.deadline;
            while (deadlineValue > 0) {
                if (jobSequencing[deadlineValue] == -1) {
                    jobSequencing[deadlineValue] = pair.jobId;
                    totalProfit += pair.profit;
                    totalJob++;
                    break;
                }
                deadlineValue--;
            }
        }
        return new ArrayList<>(Arrays.asList(totalJob, totalProfit));
    }

    static class Pair {
        int jobId;
        int deadline;
        int profit;

        Pair(int jobId, int deadline, int profit) {
            this.jobId = jobId;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
}
