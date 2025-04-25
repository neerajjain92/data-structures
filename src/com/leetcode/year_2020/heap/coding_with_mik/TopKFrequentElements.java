package com.leetcode.year_2020.heap.coding_with_mik;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {

    public static void main(String[] args) {
        TopKFrequentElements obj = new TopKFrequentElements();
        LogUtil.printArray(obj.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        LogUtil.printArray(obj.topKFrequent(new int[]{1}, 1));
    }

    public int[] topKFrequent(int[] nums, int k) {
        // the max value of a frequency is nums.length when all elements are same
        // so let's keep array of that length
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        List<List<Integer>> freqToElements = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            freqToElements.add(new ArrayList<>());
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            freqToElements.get(entry.getValue()).add(entry.getKey());
        }

        // Now simply iterate from the last
        int n = freqToElements.size();
        int[] result = new int[k];
        for (int i = n - 1; i >= 0; i--) {
            if (!freqToElements.get(i).isEmpty()) {
                int totalEntries = freqToElements.get(i).size();
                int count = 0;
                while (count < totalEntries) {
                    result[k - 1] = freqToElements.get(i).get(count);
                    k--;
                    count++;
                    if (k < 1) {
                        break;
                    }
                }
                if (k == 0)
                    break;
            }
        }
        return result;
    }
}
