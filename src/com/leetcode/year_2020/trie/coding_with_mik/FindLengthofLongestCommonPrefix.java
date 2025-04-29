package com.leetcode.year_2020.trie.coding_with_mik;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindLengthofLongestCommonPrefix {

    public static void main(String[] args) {
        FindLengthofLongestCommonPrefix obj = new FindLengthofLongestCommonPrefix();
        System.out.println(obj.longestCommonPrefix(new int[]{1, 10, 100}, new int[]{1000}));
        System.out.println(obj.longestCommonPrefixUsingTrie(new int[]{1, 10, 100}, new int[]{1000}));
        System.out.println(obj.longestCommonPrefixUsingTrie(new int[]{10}, new int[]{17, 11}));
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> allPrefix = new HashSet<>();
        for (int num : arr1) {
            while (!allPrefix.contains(num) && num > 0) {
                allPrefix.add(num);
                num /= 10;
            }
        }

        int maxLength = Integer.MIN_VALUE;
        for (int num : arr2) {
            while (num > 0) {
                if (allPrefix.contains(num)) {
                    // Short formula for calculating length of any digit
                    // since we keep dividing number by 10
                    // 1000
                    // 1000/10 => 100/10 == 10/10 == 1/10 = 0.1, so this is nothing but logBase10(num) = 3 and +1 because of decimal
                    maxLength = Math.max(maxLength, (int) Math.log10(num) + 1); // logBase10(num) + 1;
                    break;
                }
                num /= 10;
            }
        }
        return maxLength;
    }

    public int longestCommonPrefixUsingTrie(int[] arr1, int[] arr2) {
        TrieNode root = new TrieNode();
        for (int num : arr1) {
            root.insert(num);
        }

        int maxLength = 0;
        for (int num : arr2) {
            maxLength = Math.max(maxLength, findLongestCommonPrefix(root, num));
        }
        return maxLength;
    }

    private int findLongestCommonPrefix(TrieNode root, int num) {
        TrieNode crawler = root;
        String numStr = String.valueOf(num);
        int maxLength = 0;
        char[] charArray = numStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            int nextIndex = c - '0';
            if (crawler.children[nextIndex] == null) {
                break;
            }
            crawler = crawler.children[nextIndex];
            maxLength = Math.max(maxLength, i + 1);

        }
        return maxLength;
    }

    static class TrieNode {
        TrieNode[] children; // Only 0 to 9
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[10];
        }

        public void insert(int number) {
            String num = String.valueOf(number);
            TrieNode crawler = this;
            // Why since the first digit of number is added in the end of list
            for (char c : num.toCharArray()) {
                int nextIndex = c - '0';
                if (crawler.children[nextIndex] == null) {
                    crawler.children[nextIndex] = new TrieNode();
                }
                crawler = crawler.children[nextIndex];
            }
            crawler.isEnd = true;
        }
    }
}
