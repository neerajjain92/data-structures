package com.leetcode.year_2020.july_challenge.week1;

/**
 * @author neeraj on 14/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AngleBetweenHandsOfClock {

    public static void main(String[] args) {
        System.out.println(angleClock(3, 30));
        System.out.println(angleClock(6, 0));
        System.out.println(angleClock(12, 30));
    }

    public static double angleClock(int hour, int minutes) {

        /**
         * What do i know :
         *
         * Total Degrees = 360. For 12 hours
         * In 1 hour = 360/12 = 30 degrees.
         * In 1 hour(60 minutes) = 30 degree, So 1 minute = 30/60 ==> 1/2;
         *
         * Now how much hour has moved in corresponding to minute.
         * HourDegree = hour * 30 + (minutes/60) * 30;
         *
         * Or we can also write as
         * HourDegree = hour * 30 + minute/2
         *
         * For Minutes it's simple :
         * In 1 minute = 360/60 ==> 6 degree.
         *
         * So for given minutes
         * MinutesDegree = minutes * 6;
         *
         * Edge case if someone has given time 12:30....we have to consider time from 0 to 11... else 12 * 30 will overshoot
         */
        double hourDegree = hour * 30 + (minutes / 2d);
        double minuteDegree = minutes * 6;

        double absoluteDifferenceInDegree = Math.abs(hourDegree - minuteDegree);

        return absoluteDifferenceInDegree > 180 ? 360 - absoluteDifferenceInDegree : absoluteDifferenceInDegree;
    }
}
