package com.leetcode.year_2020.Greedy.interval_scheduling;

import java.util.*;

/**
 * MeetingRoom3
 * https://leetcode.com/problems/meeting-rooms-iii/description/
 * <p>
 * Inspiration:
 * <p>
 * https://www.youtube.com/watch?v=2VLwjvODQbA
 */
public class MeetingRoom3 {

    public static void main(String[] args) {
        System.out.println(mostBooked(2, new int[][]{{0, 1}, {1, 5}, {2, 7}, {3, 4}}));
        // n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
        System.out.println(mostBooked(3, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}}));
        System.out.println(mostBooked(100, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}}));
        System.out.println(mostBooked(2, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}}));
        System.out.println(mostBooked(4, new int[][]{{18, 19}, {3, 12}, {17, 19}, {2, 13}, {7, 10}}));
    }

    /**
     * We need to keep track of which rooms are available to us for scheduling
     * and which rooms are currently in used.
     * <p>
     * 1. Available heap only stores the room numbers in minHeap fashion
     * 2. Used heap will store the room numbers along with when the meeting is ending.
     * <p>
     * This endTime stored in node of heap will help us identify that whether we should
     * use the same room and find any other available room
     * <p>
     * Also, we keep track of how many meetings the rooms have scheduled inside
     * them via a count array.
     * <p>
     * Based on the problem description, it's pretty clear we want to sort the meetings
     * based on their start time
     */
    public static int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(A -> A[0]));
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        PriorityQueue<MeetingRoom> usedRooms = new PriorityQueue<>(Comparator.comparingInt(MeetingRoom::getEndTime));
        int[] totalMeetings = new int[n];

        // Add all available rooms
        for (int i = 0; i < n; i++) availableRooms.add(i);

        // Now try to schedule meetings into them
        for (int[] meeting : meetings) {
            int endTimeOfCurrentMeeting = meeting[1];
            // Finish the meetings which ended before this current meeting started
            while (!usedRooms.isEmpty() && meeting[0] >= usedRooms.peek().endTime) {
                MeetingRoom roomToBeMarkedAvailable = usedRooms.poll();
                availableRooms.add(roomToBeMarkedAvailable.roomNumber);
            }

            // Now if the room is not available
            if (availableRooms.isEmpty()) {
                MeetingRoom earliestMeetingFinishedRoom = usedRooms.poll();
                endTimeOfCurrentMeeting = earliestMeetingFinishedRoom.endTime + (meeting[1] - meeting[0]);
                availableRooms.add(earliestMeetingFinishedRoom.roomNumber);
            }

            // # Meeting room is available at this stage either normally or we must have freed some used rooms
            int availableRoomAtThisStage = availableRooms.poll();
            // if the room was available, and we didn't add the room from used, then we should use original endTime given without considering duration of original meeting
            usedRooms.add(new MeetingRoom(availableRoomAtThisStage, endTimeOfCurrentMeeting));
            totalMeetings[availableRoomAtThisStage] += 1;
        }
        // Find Max in totalMeetings and the respective index
        int maximumScheduledMeetings = Integer.MIN_VALUE;
        for (int i = 0; i < totalMeetings.length; i++) {
            maximumScheduledMeetings = Math.max(maximumScheduledMeetings, totalMeetings[i]);
        }

        for (int i = 0; i < totalMeetings.length; i++) {
            if (totalMeetings[i] == maximumScheduledMeetings) {
                return i;
            }
        }
        return -1;

    }

    public static class MeetingRoom {
        private int roomNumber;
        private int endTime;

        public MeetingRoom(int roomNumber, int endTime) {
            this.roomNumber = roomNumber;
            this.endTime = endTime;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }
    }


}
