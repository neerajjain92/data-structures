package com.leetcode.year_2020.heap.coding_with_mik;

public class KthLargestElementInArray {

    public static void main(String[] args) {
        KthLargestElementInArray obj = new KthLargestElementInArray();
//        System.out.println(obj.findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3));
//        System.out.println(obj.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(obj.findKthLargest(new int[]{1}, 1));
    }

    /**
     * We can sort it or use minHeap to solve this, A more optimized approach is to use QuickSelect (in QuickSort)
     * where we choose a pivot and make sure all the elements > than pivot are on the left and all the elements < pivot
     * are to the right
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int L = 0, R = n - 1;
        int pivot_index = 0;

        while (true) {
            pivot_index = partition_algo(nums, L, R);
            if (pivot_index == k - 1) {
                break; // we are on kth largest element since all the elements on left are greater than this
            } else if (pivot_index > k - 1) { // we wanted 2nd largest but found 4th largest since we keep greater on left side
                // so we should move R
                R = pivot_index - 1;
            } else {
                L = pivot_index + 1;
            }
        }
        return nums[pivot_index];
    }

    private int partition_algo(int[] nums, int l, int r) {
        int pivot = nums[l];
        int i = l+1, j = r;
        while (i <= j) {
            if (nums[i] < pivot && nums[j] > pivot) {
                swap(nums, i, j);
                i++;
                j--;
            }

            if (nums[i] >= pivot) {
                i++;
            }
            if (nums[j] <= pivot) {
                j--;
            }
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
