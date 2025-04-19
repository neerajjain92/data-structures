package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/
 * 2244. Minimum Rounds to Complete All Tasks
 */
public class MinimumRoundsToCompleteAllTask {

    public static void main(String[] args) {
        System.out.println(minimumRounds(new int[]{69, 65, 62, 64, 70, 68, 69, 67, 60, 65, 69, 62, 65, 65, 61, 66, 68, 61, 65, 63, 60, 66, 68, 66, 67, 65, 63, 65, 70, 69, 70, 62, 68, 70, 60, 68, 65, 61, 64, 65, 63, 62, 62, 62, 67, 62, 62, 61, 66, 69}));
        System.out.println(minimumRounds(new int[]{66, 66, 63, 61, 63, 63, 64, 66, 66, 65, 66, 65, 61, 67, 68, 66, 62, 67, 61, 64, 66, 60, 69, 66, 65, 68, 63, 60, 67, 62, 68, 60, 66, 64, 60, 60, 60, 62, 66, 64, 63, 65, 60, 69, 63, 68, 68, 69, 68, 61}));
    }

    public static int minimumRounds(int[] tasks) {
        Map<Integer, Integer> taskFrequencies = new HashMap<>();
        for (int task : tasks) {
            taskFrequencies.put(task, taskFrequencies.getOrDefault(task, 0) + 1);
        }

        // Now just walk over them
        int rounds = 0;
        for (Map.Entry<Integer, Integer> entry : taskFrequencies.entrySet()) {
            int freq = entry.getValue();
            if (freq < 2) return -1;

            // Any number greater than 2 can be represented as
            // 3k, 3k+1, 3k+2 [where k is changing]
            // 4 = 3*1+1
            // 5 = 3*1+2
            // 6 = 3*2
            // and so on
            if (freq % 3 == 0) {
                rounds += freq / 3;
            } else {
                // Why assume 10 ==> 10%3 = 1, so we did 3 task thrice and 1 remainder
                // but we can't pick one so we have to either pick 2 or 3 task
                // if i pick 3 task twice (2 rounds) then 4 is left so i can pick (2+2) for the remaining ones
                // so total rounds is 4 (3*2 + 2*2) ==> or what you can say since when we picked last 3rd
                // 10 Rounds
                // [--bucket of 3] [--bucket of 3] [--bucket of 3] [--bucket of 1] , Since 1 is not allowed to be picked
                // so we club last 2 buckets and split 4/2
                // [--buck et of 3] [--bucket of 3] [--bucket of 2] [--bucket of 2]
                rounds += (freq / 3 + 1);
            }
        }
        return rounds;

    }
}
