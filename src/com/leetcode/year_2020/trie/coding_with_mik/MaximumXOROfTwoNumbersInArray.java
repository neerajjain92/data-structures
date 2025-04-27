package com.leetcode.year_2020.trie.coding_with_mik;

/**
 * https://www.youtube.com/watch?v=JS48Hp2_Z4I
 */
public class MaximumXOROfTwoNumbersInArray {

    BitTrie root;

    public static void main(String[] args) {
        MaximumXOROfTwoNumbersInArray obj = new MaximumXOROfTwoNumbersInArray();
        System.out.println(obj.findMaximumXORUsingBitTrie(new int[]{3, 5, 2, 7}));
        System.out.println(obj.findMaximumXORUsingBitTrie(new int[]{3, 10, 5, 25, 2, 8}));

    }

    public int findMaximumXOR(int[] nums) {
        root = new BitTrie();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }

    /**
     * The Most optimal approach
     */
    public int findMaximumXORUsingBitTrie(int[] nums) {
        root = new BitTrie();
        for (int num : nums) {
            root.insert(num);
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, maxXOR(root, num));
        }

        return max;
    }

    private int maxXOR(BitTrie root, int num) {
        int maxXOR = 0;
        BitTrie crawlPointer = root;
        for (int i = 31; i >= 0; i--) {
            int ithBit = (num >>> i) & 1;
            if (ithBit == 1) {
                // Move to left because we want 0 to make 1 XOR 0 as 1
                if (crawlPointer.left != null) {
                    // if we have found 0 in the left so XOR will be 1, hence we use maxOR calculation here
                    maxXOR += (int) (Math.pow(2, i));
                    crawlPointer = crawlPointer.left;
                } else {
                    crawlPointer = crawlPointer.right;
                }
            } else {
                // We need 1 to get maximum (because 0 XOR 1 is 1)
                if (crawlPointer.right != null) {
                    maxXOR += (int) (Math.pow(2, i));
                    crawlPointer = crawlPointer.right;
                } else {
                    crawlPointer = crawlPointer.left;
                }
            }
        }
        return maxXOR;
    }

    /**
     * In BitTrie we go to left for representing 0 (i.e left Node)
     * and right for 1
     */
    static class BitTrie {
        BitTrie left; // 0
        BitTrie right; // 1

        public void insert(int num) {
            BitTrie crawlPointer = this;
            // Since it's 32 bit integer
            for (int i = 31; i >= 0; i--) {
                // Formula for finding ith bit of any number
                int bitAtI = (num >>> i) & 1; // Right Shit upto i and then AND with 1
                if (bitAtI == 0) {
                    if (crawlPointer.left == null) {
                        crawlPointer.left = new BitTrie();
                    }
                    crawlPointer = crawlPointer.left;
                } else {
                    if (crawlPointer.right == null) {
                        crawlPointer.right = new BitTrie();
                    }
                    crawlPointer = crawlPointer.right;
                }
            }
        }
    }
}
