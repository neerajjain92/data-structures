package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/find-top-k-or-most-frequent-numbers-in-a-stream/
 */
public class FindTopKNumbersInStream {

    public static void main(String[] args) {
        findTopKNumbersInStream(new int[]{5, 2, 1, 3, 2});
        System.out.println("=========================================================");
        findTopKNumbersInStream(new int[]{5, 2, 1, 3, 4});
    }

    public static void findTopKNumbersInStream(int[] arr) {
        StringBuffer prev = new StringBuffer();
        Map<Integer, Integer> freqMap = new HashMap<>();
        StringBuffer current = new StringBuffer();
        Boolean newValInserted = false;
        for (int i = 0; i < arr.length; i++) {
            newValInserted = false;
            if (freqMap.get(arr[i]) == null) {
                freqMap.put(arr[i], 1);
            } else {
                freqMap.put(arr[i], freqMap.get(arr[i]) + 1);
            }

            for (char c : prev.toString().toCharArray()) {
                int val = c - '0';
                if (!newValInserted && freqMap.get(val) < freqMap.get(arr[i])) {
                    current.append(arr[i]);
                    newValInserted = true;
                }
                if (!newValInserted && freqMap.get(val) == freqMap.get(arr[i])) {
                    if (arr[i] < val) {
                        current.append(arr[i]);
                        newValInserted = true;
                    }
                }
                if (newValInserted && val != arr[i]) {
                    current.append(val);
                } else if (val != arr[i]) {
                    current.append(val);
                }
            }
            if (!newValInserted) {// If everything traversed and new value is still not inserted
                current.append(arr[i]);
                newValInserted = true;
            }
            prev = current;
            System.out.println(current.toString());
            current = new StringBuffer();
        }

    }
}
