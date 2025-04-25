package com.leetcode.year_2020.heap.coding_with_mik;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/single-threaded-cpu/description/
 * 1834. Single-Threaded CPU
 */
public class SingleThreadedCPU {

    public static void main(String[] args) {
        SingleThreadedCPU cpu = new SingleThreadedCPU();
        LogUtil.printArray(cpu.getOrder(new int[][]{{1, 2}, {2, 4}, {3, 2}, {4, 1}}));
    }

    /*
     * Do as question is saying
     * 1. CPU will schedule the fastest processing time scheduled to be run at that instant(this is imporant if the current time is 1'o clock even if some task's processing time is just 1 hour but enqueu is 3'0 clock it can't be started before 3)
     * 2. If 2 or more tasks are there who are about to start at current instant, we should pick the one with smalles processing time
     * 3. If the processing time is also same then we pick the one with smallest index [basically the index we got in our question]
     *
     * Intuition: Assumption all these task values are in minutes
     * Since it's time based right, so we basicallly keep running our clock time and at every minute in clock we ask Knock knock "Anyone up for scheduling" ?
     *      Now you tell me if some School bus  comes at your home on Sunday will go sit in the bus and go to school no right because you were supposed to be going to school on Monday
     *      so you just keep waiting until either the bus comes on Monday or it comes on Tuesday as well, what i meant by this is you won't be scheduled before the time but post or equal to the time task will be picked up
     *
     * -> So sort the input based on enqueue time so when our clock is running we'll be able to pick up the task to be scheduled now or in past
     * -> Start currentTime with the minimum
     * -> For every currentTime[ask from your sortedPairs] do you want to be scheduled if yes put them in Heap so our sorting logic is met
     * -> Once till currentTime you have setup everyone in the heap
     * -> Now you need to basically forward the currentTime, can you forward the currentTime to next minute ??????
     *              Answer : No Why ? because it's Single Threaded CPU, so we have to wait till processing is finished
     *      So currentTime will be currentTime + processingTime on the earliest processingItem from the queue
     *
     * At the end you must have processed everyone from the index, if someone is left they must be in the Heap
     * Pull them up and add to your answer
     *
     */
    public int[] getOrder(int[][] tasks) {
        List<Task> pairs = new ArrayList<>();
        int index = 0;
        for (int[] task : tasks) {
            pairs.add(new Task(task[0], task[1], index++));
        }

        // Sort them by enqueue time since question didn't have it in sorted order
        pairs.sort(Comparator.comparingInt(task -> task.enqueueTime));

        // Now we need a min heap to get the items with least processing time
        // and if processing time is same then tie-braker with least index
        PriorityQueue<Task> minHeap = new PriorityQueue<>((p1, p2) -> {
            if (p1.processingTime != p2.processingTime) {
                return p1.processingTime - p2.processingTime;
            } else {
                return p1.index - p2.index;
            }
        });

        index = 0;
        int currentTime = pairs.get(0).enqueueTime; // CPU will not get any task before the first tasks enque time hence this.
        int[] result = new int[tasks.length];
        int resultCtr = 0;
        while (index < pairs.size() || !minHeap.isEmpty()) {

            // Enqueue all task which could be started before the currentTime or on currentTime
            while (index < pairs.size() && currentTime >= pairs.get(index).enqueueTime) {
                minHeap.offer(pairs.get(index));
                index++;
            }

            // Now we just need to move our time to the next window since we have all tasks in heap which should
            // have started before currentTime
            if (!minHeap.isEmpty()) {
                // If minHeap is not empty
                Task polled = minHeap.poll();
                currentTime += polled.processingTime; // since till processing time this task is booked
                result[resultCtr++] = polled.index;
            } else {
                // If heap is empty, jump time to next task's enqueueTime
                currentTime = pairs.get(index).enqueueTime;
            }
        }
        return result;
    }

    static class Task {
        int enqueueTime, processingTime, index;

        public Task(int enqueueTime, int processingTime, int index) {
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
            this.index = index;
        }
    }
}
