package com.leetcode.year_2020.Greedy.interval_scheduling;


import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-iii/
 * Best intuition https://claude.site/artifacts/48a519a1-03bc-4d86-bdc7-59ad1ae0c45b
 * Simple Intuition for My Calendar III
 * Think of the problem this way:
 * <p>
 * Every time an event starts, one more room is occupied
 * Every time an event ends, one room becomes free
 * We need to know the maximum number of rooms ever needed simultaneously
 * <p>
 * The approach is like tracking people entering and leaving a building:
 * <p>
 * Someone enters: add 1 to count
 * Someone leaves: subtract 1 from count
 * The question asks for the maximum number of people ever in the building at once
 * <p>
 * How to implement this:
 * <p>
 * For each booking (start, end):
 * <p>
 * Mark "+1" at the start time
 * Mark "-1" at the end time
 * <p>
 * <p>
 * Then sort all these time points and walk through them in order:
 * <p>
 * Keep a running count
 * When you see "+1", increase your count
 * When you see "-1", decrease your count
 * Track the maximum count you ever see
 * <p>
 * <p>
 * <p>
 * The TreeMap is just used because it automatically keeps times sorted for us, but the core idea is this counting of entrances and exits.
 */
public class MyCalendar_3 {

    public static void main(String[] args) {
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        System.out.println(myCalendarThree.book(10, 40));
        System.out.println(myCalendarThree.book(20, 50));
        System.out.println(myCalendarThree.book(30, 60));
    }

    /**
     * https://claude.site/artifacts/48a519a1-03bc-4d86-bdc7-59ad1ae0c45b
     * Explanation https://claude.ai/share/bbbb708e-3a76-4aa6-93e5-e2195e1f4782
     */
    static class MyCalendarThree {

        private TreeMap<Integer, Integer> timeline;

        public MyCalendarThree() {
            timeline = new TreeMap<>();
        }

        public int book(int startTime, int endTime) {
            // Add +1 at start time
            timeline.put(startTime, timeline.getOrDefault(startTime, 0) + 1);

            // Add -1 at end time
            timeline.put(endTime, timeline.getOrDefault(endTime, 0) - 1);

            // Track maximum bookings
            int maxBookings = 0;
            int currentOverlapps = 0;
            for (int count : timeline.values()) {
                currentOverlapps += count;
                maxBookings = Math.max(maxBookings, currentOverlapps);
            }
            return maxBookings;
        }
    }
}
