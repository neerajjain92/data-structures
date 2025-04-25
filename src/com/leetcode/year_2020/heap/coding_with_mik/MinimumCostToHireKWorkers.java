package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
 * 857. Minimum Cost to Hire K Workers
 */
public class MinimumCostToHireKWorkers {

    public static void main(String[] args) {
        MinimumCostToHireKWorkers m = new MinimumCostToHireKWorkers();
        System.out.println(m.mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2));
    }

    /*
     * A very sexy real world problem, seriously happy
     *
     * Okay intuition:
     *
     * Rules in question:
     *
     * 1. Every worker in the paid group must be paid at least their minimum wage expectation.
     * 2. In the group, each worker's pay must be directly proportional to their quality.
     * This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker.
     *
     * Simple minimum Salary dena hai....
     * If one worker's quality is better than other person's quality to salary usi hisab se deni hai
     *
     * and how the heck do you measure quality, it's given in question.
     *
     * So if you have 2 person
     * Person:   A       B
     * Quality:  10      5
     *
     * so if you give 100 rupees to person A, you can only give 50 to person B why ????
     *
     *    Quality_of_Person_A                                       Wage_given_to_Person_A
     *    -------------------   ======Should be equal to =========  -------------------
     *    Quality_of_Person_B                                       Wage_given_to_Person_B
     *
     * This ratio should match
     *  So to calculate how much wage i should give to person B it should be simple
     *
     *                             |Wage_given_to_person_A|
     *   Wage_given_to_Person_B =  |----------------------|    * Quality_of_person_B
     *                             |Quality_of_person_A   |
     *
     *
     *
     * So to find out least amount to be spent to hire k workers and pay them atleast minimumWage and
     * also match the ratio equality we can follow this story.
     *
     * Also before that how about we call this person_A the manager and the ratio of wage/quality of that as manager's ratio
     *
     * So formula is wage_of_worker = manager's_ratio * worker_quality
     *
     *
     * Now story to solve the problem
     *
     * 1. Try all members as Manager  for manager=0;manager<n;manager++;
     * 2. Calculate manager_ratio = min_wage_manager/quality_of_manager
     * 3. Declare the group as MaxHeap, since we want to keep only the k minimum in the group_max_heap
     * 3. for(worker=0;worker<n;worker++)
     *     {
     *       // Skip manager
     *       worker_wage = manager_ratio * worker_quality
     *       if (worker_wage < min_wage) continue
     *       else group_max_heap.add(worker_wage); if group_max_heap.size > k poll
     *     }
     * 4. if group_max_heap.size < k continue; this manager wasn't able to create a group
     * 5. Else Sum all wages and keep in Min_global_variable
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double minTotalInvestment = Double.MAX_VALUE;
        for (int manager = 0; manager < quality.length; manager++) {
            PriorityQueue<Double> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            double managerRatio = (double) wage[manager] / (double) quality[manager];
            maxHeap.add((double) wage[manager]);
            for (int worker = 0; worker < quality.length; worker++) {
                if (manager == worker) continue;
                double workerWage = managerRatio * quality[worker];
                if (workerWage < wage[worker]) {
                    continue;
                } else {
                    maxHeap.offer(workerWage);
                    if (maxHeap.size() > k) maxHeap.poll();
                }
            }

            if (maxHeap.size() != k) {
                continue; // this manager couldn't contribute
            } else {
                double sum = 0;
                while (!maxHeap.isEmpty()) {
                    sum += maxHeap.poll();
                }
                minTotalInvestment = Math.min(minTotalInvestment, sum);
            }
        }
        return minTotalInvestment;
    }
}
