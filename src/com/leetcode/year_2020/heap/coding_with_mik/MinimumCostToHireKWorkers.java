package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.Arrays;
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
        System.out.println(m.mincostToHireWorkers(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 2));
        System.out.println(m.mincostToHireWorkersImprovement_1(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2));
        System.out.println(m.mincostToHireWorkersImprovement_1(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 2));

        System.out.println(m.mincostToHireWorkersOptimal(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2));
        System.out.println(m.mincostToHireWorkersOptimal(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 2));
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
            for (int worker = 0; worker < quality.length; worker++) {
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


    /*
     * Theory of previous explanation of {@link MinimumCostToHireKWorkers#mincostToHireWorkers(int[], int[], int)} is still
     * correct so we will optimize on top of that only
     *
     * We just mentioned in the story above that
     *
     *       worker_wage >= minimum_wage_of_worker
     *  Worker_wage = {manager_ratio * quality_of_worker}
     *
     *  So
     *     manager_ratio * quality_of_worker >= wage[worker]
     *  or manager_ration >= wage[worker]/quality_of_worker
     *
     * , can we call the right side as worker_ratio similar to manager's ratio
     *
     * So all this story is to say, that we will only choose those workers, where
     *
     *   Manager's_Ratio >= Worker's Ratio
     *
     * So can't i pre-calculate everyone's ratio in an array and basically sort it so that when i pick my manager
     * I know i need to just pick worker's from my left and since we have to make a group of k members my manager have to start from kth index
     *
     * ------------------------------------------------------------
     * wage[0]/quality[0] | wage[1]/quality[1]| ....... and so on
     * ------------------------------------------------------------
     *
     * Now the timeline is
     *
     * 1. instead of picking everyone as manager you do
     *    for (manager=k-1;manager<n;manager++)
     * 2. Declare the group as MaxHeap, since we want to keep only the k minimum in the group_max_heap
     * 3.   for(worker=0;worker<=manager;worker++)
     * 4.     we already have the ratio [wage[worker]/quality[worker]] so no need to check min_wage criteria
     *           maxHeap.push(worker_wage) = manager_ration * quality_worker
     *
     * 5. Just calculate the sum and answer
     *
     */
    public double mincostToHireWorkersImprovement_1(int[] quality, int[] wage, int k) {
        PairWithRatioAndQuality[] workerRatios = new PairWithRatioAndQuality[quality.length];
        for (int i = 0; i < quality.length; i++) {
            workerRatios[i] = new PairWithRatioAndQuality(wage[i] / (quality[i] * 1.0), quality[i]);
        }
        Arrays.sort(workerRatios, Comparator.comparingDouble(a -> a.ratio));

        double minTotalInvestment = Double.MAX_VALUE;
        for (int manager = k - 1; manager < quality.length; manager++) {
            PriorityQueue<Double> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            double managerRatio = workerRatios[manager].ratio;
            for (int worker = 0; worker <= manager; worker++) {
                double workerWage = managerRatio * workerRatios[worker].quality;
                maxHeap.offer(workerWage);
                if (maxHeap.size() > k) maxHeap.poll();
            }
            double sum = 0;
            while (!maxHeap.isEmpty()) {
                sum += maxHeap.poll();
            }
            minTotalInvestment = Math.min(minTotalInvestment, sum);
        }
        return minTotalInvestment;
    }

    /**
     * Optimal Approach
     * <p>
     * This has to be built on top of mincostToHireWorkersImprovement_1 because that's where we are improving
     * <p>
     * if you notice we were doing this
     * At the end if i pick a manager at k-1th index I am pushing worker_wage from 0th index till k-1th
     * <p>
     * So manager_ratio * quality_worker[0] + manager_ratio * quality_worker[1] + manager_ratio * quality_worker[2] and so on
     * Notice manager_ratio is common bro
     * so manager_ratio (quality[0]+quality[1]+quality[2]) annd so on
     * See, and when you will pick the next manager at k-1 to kth index
     * you will add quality of kth index so then totalSum will be
     * so manager_ratio_new (quality[0]+quality[1]+quality[2]+quality[3]), but since you will be using MaxHeap so will kick out the quality who is maximum in these
     * and there is no need to store the wage in maxHeap now we can just store quality since we will be multiplying the manager_ration at the end
     */
    public double mincostToHireWorkersOptimal(int[] quality, int[] wage, int k) {
        PairWithRatioAndQuality[] workerRatios = new PairWithRatioAndQuality[quality.length];
        for (int i = 0; i < quality.length; i++) {
            workerRatios[i] = new PairWithRatioAndQuality(wage[i] / (quality[i] * 1.0), quality[i]);
        }
        Arrays.sort(workerRatios, Comparator.comparingDouble(a -> a.ratio));

        double minTotalInvestment = Double.MAX_VALUE;
        // this will hold the quality
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        double sum = 0;
        for (int i = 0; i < k; i++) {
            maxHeap.offer(workerRatios[i].quality);
            sum += workerRatios[i].quality;
        }
        minTotalInvestment = Math.min(minTotalInvestment, sum * workerRatios[k - 1].ratio);
        // Now we have calculation for k-1th manager, lets try post that

        for (int manager = k; manager < quality.length; manager++) {
            sum += workerRatios[manager].quality;
            maxHeap.offer(workerRatios[manager].quality);
            sum -= maxHeap.poll(); // Let's remove the maxQuality from the heap
            minTotalInvestment = Math.min(minTotalInvestment, sum * workerRatios[manager].ratio);
        }
        return minTotalInvestment;
    }

    static class PairWithRatioAndQuality {
        double ratio, quality;

        public PairWithRatioAndQuality(double ratio, double quality) {
            this.ratio = ratio;
            this.quality = quality;
        }
    }

}
