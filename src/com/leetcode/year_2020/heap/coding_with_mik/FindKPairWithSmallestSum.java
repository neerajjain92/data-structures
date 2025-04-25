package com.leetcode.year_2020.heap.coding_with_mik;

import java.util.*;

public class FindKPairWithSmallestSum {

    public static void main(String[] args) {
        FindKPairWithSmallestSum obj = new FindKPairWithSmallestSum();
        System.out.println(obj.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        System.out.println(obj.kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 1, 3}, 2));
        System.out.println(obj.kSmallestPairs(new int[]{1, 2, 4, 5, 6}, new int[]{3, 5, 7, 9}, 3));

        System.out.println("Optimized Now");
        System.out.println(obj.kSmallestPairsOptimized(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        System.out.println(obj.kSmallestPairsOptimized(new int[]{1, 1, 2}, new int[]{1, 1, 3}, 2));
        System.out.println(obj.kSmallestPairsOptimized(new int[]{1, 2, 4, 5, 6}, new int[]{3, 5, 7, 9}, 3));
    }

    /**
     * Time complexity
     * <p>
     * m = size of nums1, n = size of nums2
     * So O (m*n log(k)) this is the time complexity, log(k) is push and pop complexity of minheap
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.sum - a.sum);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (maxHeap.size() < k) {
                    // keep pushing until heap is of size k
                    maxHeap.add(new Pair(i, j, nums1[i] + nums2[j]));
                } else if (maxHeap.peek().sum > nums1[i] + nums2[j]) {
                    // Now the heap is > size k
                    // since the peek's sum is greater we should pop it
                    maxHeap.poll();
                    maxHeap.add(new Pair(i, j, nums1[i] + nums2[j]));
                } else {
                    break; // Because at this point j is sorted if we add any new item from j it's sum will be greater than current j
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            Pair pair = maxHeap.poll();
            result.add(List.of(nums1[pair.i], nums2[pair.j]));
        }
        return result;
    }
    
    /**
     * Optimal Solution
     */
    public List<List<Integer>> kSmallestPairsOptimized(int[] nums1, int[] nums2, int k) {
        // We will always keep the minimum sum int the priority queue
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.sum));


        /*
         * We know nums1 and num2 2 is sorted
         * So the smallest ever sum with nums1 and nums2 can't be smaller than nums1[0], nums2[0]
         * Post first items things can change but till the 0th index the smallest is always 0th item
         * Now after that you have to compare 2 items
         * whether (nums1[index+1]+nums2[index], nums1[index]+nums2[index+1])
         * we will try both and our minHep will make sure smallest sum is on top
         *
         * Example:
         * {1, 7, 11}  ---- {2, 4, 6}
         *
         * 1st: {1,2} -[0,0] index
         * 2nd: Here now we have 2 choices whether 7+2 or 1+4 ==> so 1+4 is smallest hence
         *      {1,4} - [0,1]
         * 3rd: {1,6} - [0-3]
         *
         * Now another example where nums1[index] is not necessarily give good answer sometime we have to pick nums1[index+1]
         * {1, 2, 4, 5, 6} ---- {3, 5, 7, 9} k = 3
         *
         * Can you blindly take 1 from nums1 and all from nums2
         * {1, 3}, {1,5}, {1,7}
         *
         * Notice when you picked {1, 5} = sum(6)
         * but if you would have picked {2,3} = sum(5) is a better option
         *
         */
        int n1 = nums1.length;
        int n2 = nums2.length;
        Pair pair = new Pair(0, 0, nums1[0] + nums2[0]);
        minHeap.add(pair); // Add the smallest
        List<List<Integer>> result = new ArrayList<>();
        Set<Pair> visited = new HashSet<>();
        visited.add(pair);

        while (!minHeap.isEmpty() && k > 0) {
            Pair polled = minHeap.poll();
            int i = polled.i;
            int j = polled.j;
            result.add(List.of(nums1[polled.i], nums2[polled.j]));
            k--;

            // Check i, j+1
            if (j + 1 < n2 && !visited.contains(new Pair(i, j + 1, nums1[i] + nums2[j + 1]))) {
                minHeap.add(new Pair(i, j + 1, nums1[i] + nums2[j + 1]));
                visited.add(new Pair(i, j + 1, nums1[i] + nums2[j + 1]));
            }

            // Check i+1, j
            if (i + 1 < n1 && !visited.contains(new Pair(i + 1, j, nums1[i + 1] + nums2[j]))) {
                minHeap.add(new Pair(i + 1, j, nums1[i + 1] + nums2[j]));
                visited.add(new Pair(i + 1, j, nums1[i + 1] + nums2[j]));
            }
        }
        return result;
    }

    static class Pair {
        int i, j, sum;

        public Pair(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i && j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j, sum);
        }
    }
}
