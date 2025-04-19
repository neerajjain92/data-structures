package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.*;

public class OptimalPartitionOfString {

    public static void main(String[] args) {
        System.out.println(partitionString("abac"));
        System.out.println(partitionString("world"));
        System.out.println(partitionString("ssssss"));
        System.out.println(partitionString("abcabc"));
        System.out.println(partitionString("a"));
        System.out.println(partitionString("abacdecfgh"));
        System.out.println(partitionString("abacaba"));
    }

    public static int partitionString(String s) {
        int[] existingMembersInPartition = new int[26];
        Arrays.fill(existingMembersInPartition, -1);
        int totalPartitions = 1;
        int partitionStart = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            int index = c - 'a';
            if (existingMembersInPartition[index] != -1) {
                if (existingMembersInPartition[index] >= partitionStart) {
                    // We have seen the member in this partition
                    totalPartitions++;
                    partitionStart = i;
                }
            }
            existingMembersInPartition[index] = i;
        }
        return totalPartitions;
    }
}
