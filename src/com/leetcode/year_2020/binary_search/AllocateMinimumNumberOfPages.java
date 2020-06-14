package com.leetcode.year_2020.binary_search;

import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/allocate-minimum-number-pages/
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 * https://leetcode.com/problems/split-array-largest-sum/
 *
 * @author neeraj on 12/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AllocateMinimumNumberOfPages {

    public static void main(String[] args) {
        int x = 3;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            if (Math.abs(b - x) == Math.abs(a - x)) {
                return b - a;
            } else {
                return Math.abs(b - x) - Math.abs(a - x);
            }
        });
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            maxHeap.add(i);
            if (maxHeap.size() > 4) {
                maxHeap.poll();
            }
        }

        System.out.println(maxHeap);

//        System.out.println(findMinimumNumberOfMaximumPagesStudentCanRead(new int[]{10, 20, 30, 40}, 2));
//        System.out.println(findMinimumNumberOfMaximumPagesStudentCanRead(new int[]{12, 34, 67, 90}, 2));
//        System.out.println(findMinimumNumberOfMaximumPagesStudentCanRead(new int[]{7, 2, 5, 10, 8}, 2));
//        System.out.println(findMinimumNumberOfMaximumPagesStudentCanRead(new int[]{1, 4, 4}, 3));
    }

    public static int findMinimumNumberOfMaximumPagesStudentCanRead(int[] pages, int students) {
        /**
         * We can also solve
         * Leetcode : . Split Array Largest Sum problem with the same approach. https://leetcode.com/problems/split-array-largest-sum/
         * Leetcode : Capacity To Ship Packages Within D Days https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/submissions/
         *
         * The task is to assign books in such a way that the maximum number
         * of pages assigned to a student is minimum.
         * Constraint:
         * 1) All students will read at-least 1 books.
         * 2) You cannot divided pages from the same book (since no 2 student can share the book at the same time).
         * 3) Student will read the book in continuous manner.
         * Assume we have 4 books with following number of pages
         * [10 20 30 40]
         *
         * So S1 can't read book with 10 pages first and then book with 30 pages, he has to read 20 one first.
         *
         * Now we know a maximum pages which someone can read Sum(Books(pages)) = 100 (10+20+30+40).
         * and minimum 0.
         *
         * Now we have a number line of pages
         *
         * |--------------------------------------------|
         * 0                                            100
         *
         * Now we will use binary search and try to figure out a minimum value of pages which satisfies the requirement
         * that every student can read the book
         */

        int low = 0, high;
        int totalPages = 0;
        for (int page : pages) {
            totalPages += page;
        }
        high = totalPages;
        int MinimumOfMaximumPagesAStudentCanRead = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isThisMaximumPageAStudentCanRead(mid, pages, students)) {
                MinimumOfMaximumPagesAStudentCanRead = mid;

                // We know that student can easily read these many pages
                // so lets try to minimize this window more.
                /**
                 *           |----------------------MID 50----------------------|
                 *            0                       |                    100
                 *
                 *            when we tried to validate that maximum pages student can read can't cross 50 pages
                 *
                 *            Student 1 ==> 10 + 20 (30 pages) [If i add book with 30 pages it will cross the thresh-hold
                 *            Student2 ==> 30
                 *            Student3 ==> 40
                 *
                 *    If i use 50 as a thresh-hold we need 3 student where as we only have 2 student, so these pages are
                 *    not sufficient enough, hence we need to expand the window.
                 *
                 *      |--------------------------------MID 75-----------|
                 *      0                      50         |          100
                 *
                 *      Now we know 75 is enough for 2 student but can we shrink this window more. lets check
                 */
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return MinimumOfMaximumPagesAStudentCanRead;
    }

    private static boolean isThisMaximumPageAStudentCanRead(int maximumPagesAStudentCanRead, int[] pages, int students) {
        int totalPagesStudentHaveRead = 0;
        int studentRequired = 1;
        for (int pageInBook : pages) {
            if (pageInBook > maximumPagesAStudentCanRead) return false;
            totalPagesStudentHaveRead += pageInBook;
            if (totalPagesStudentHaveRead > maximumPagesAStudentCanRead) {
                studentRequired++; // 1 student's limit is exhausted.
                totalPagesStudentHaveRead = pageInBook; // Resetting it to the current book pages.
            }

            if (studentRequired > students) return false;
        }
        return true;
    }
}
