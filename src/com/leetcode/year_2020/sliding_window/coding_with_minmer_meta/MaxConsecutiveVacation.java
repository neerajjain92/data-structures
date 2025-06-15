package com.leetcode.year_2020.sliding_window.coding_with_minmer_meta;

/**
 * https://www.youtube.com/watch?v=moTN6h5QkP8&list=PLtVcREFQDb6ZykTpgFhkQiPjLyrX1Y96D&index=7&ab_channel=CodingwithMinmer
 * <p>
 * Similar to https://leetcode.com/problems/max-consecutive-ones-iii/description/
 * MaxConsecutiveOnes _ 3
 */
public class MaxConsecutiveVacation {

    public static void main(String[] args) {
        MaxConsecutiveVacation obj = new MaxConsecutiveVacation();
        // ✅ Test Case 1: Basic with 2 PTOs
        System.out.println(obj.maxVacation(new char[]{'W', 'W', 'W', 'H', 'W', 'W', 'W', 'H', 'H'}, 2)); // Expected: 6

        // ✅ Test Case 2: All holidays, no PTO needed
        System.out.println(obj.maxVacation(new char[]{'H', 'H', 'H', 'H', 'H'}, 0)); // Expected: 5

        // ✅ Test Case 3: All workdays, enough PTO to convert all
        System.out.println(obj.maxVacation(new char[]{'W', 'W', 'W'}, 3)); // Expected: 3

        // ✅ Test Case 4: Not enough PTO to convert all workdays
        System.out.println(obj.maxVacation(new char[]{'W', 'H', 'W', 'W', 'H', 'W'}, 1)); // Expected: 3

        // ✅ Test Case 5: Edge case - Empty array
        System.out.println(obj.maxVacation(new char[]{}, 2)); // Expected: 0


        char[][] days1 = {
                {'W', 'W', 'W'},
                {'H', 'W', 'W'},
                {'W', 'H', 'H'}
        };
        int pto1 = 2;

        char[][] days2 = {
                {'H', 'H'},
                {'H', 'H'}
        };
        int pto2 = 0;

        char[][] days3 = {
                {'W', 'W'},
                {'W', 'W'}
        };
        int pto3 = 4;

        char[][] days4 = {
                {'W', 'W', 'W'},
                {'W', 'W', 'W'},
        };
        int pto4 = 2;

        char[][] days5 = {};
        int pto5 = 3;

        char[][] days6 = {
                {'W', 'H', 'W', 'H', 'H'}
        };
        int pto6 = 1;


        char[][] days7 = {
                {'W'},
                {'H'},
                {'W'},
                {'W'}
        };
        int pto7 = 2;


        System.out.println("Now 2D matrix");
        System.out.println(obj.maxVacationIn2DArray(days1, pto1));
        System.out.println(obj.maxVacationIn2DArray(days2, pto2));
        System.out.println(obj.maxVacationIn2DArray(days3, pto3));
        System.out.println(obj.maxVacationIn2DArray(days4, pto4));
        System.out.println(obj.maxVacationIn2DArray(days5, pto5));
        System.out.println(obj.maxVacationIn2DArray(days6, pto6));
        System.out.println(obj.maxVacationIn2DArray(days7, pto7));


        System.out.println("Now Fractional PTO Days brooooooo");

        System.out.println(obj.maxVacationFractionPTO(new char[]{'H', 'H', 'W', 'W', 'W', 'H'}, 2.5));
        System.out.println(obj.maxVacationFractionPTO(new char[]{'W', 'W', 'W', 'H', 'H', 'W'}, 2.5));
        System.out.println(obj.maxVacationFractionPTO(new char[]{'H', 'H', 'H', 'H'}, 2.5));
        System.out.println(obj.maxVacationFractionPTO(new char[]{'W', 'W', 'W', 'W'}, 2.5));
        System.out.println(obj.maxVacationFractionPTO(new char[]{'W', 'H', 'W', 'H', 'W', 'W'}, 2.5));


    }


    public int maxVacation(char[] days, int ptoDays) {
        /*
         * 'W' --> WorkDay
         * 'H' --> Holiday
         */
        int left = 0, maxVacation = 0;
        for (int right = 0; right < days.length; right++) {
            if (days[right] == 'W') {
                ptoDays--;
            }

            while (ptoDays < 0) {
                if (days[left] == 'W') {
                    ptoDays++;
                }
                left++;
            }
            maxVacation = Math.max(maxVacation, right - left + 1);
        }
        return maxVacation;
    }

    public int maxVacationIn2DArray(char[][] days, int ptoDays) {
        if (days == null || days.length == 0 || days[0].length == 0) return 0;
        /*
         * This looks to me like a calendar
         * 1   2   3   4
         * 5   6   7   8
         * 9   10  11  12
         *
         * So days are laid out horizontally and then you jump into next row
         * Simple hai bhai isko proper 0-n tk hi believe kr, bas jab value retrieve
         * kr rha hai make
         */
        int left = 0, maxVacation = 0;

        for (int right = 0; right < days.length * days[0].length; right++) {
            if (getDayValue(right, days) == 'W') {
                ptoDays--;
            }

            while (ptoDays < 0) {
                if (getDayValue(left, days) == 'W') {
                    ptoDays++;
                }
                left++;
            }
            maxVacation = Math.max(maxVacation, right - left + 1);
        }
        return maxVacation;
    }


    public double maxVacationFractionPTO(char[] days, double ptoDays) {
        double wholePTODays = Math.floor(ptoDays);
        double fractionPTODays = ptoDays - wholePTODays;
        double maxVacation = 0.0;
        /*
         * 'W' --> WorkDay
         * 'H' --> Holiday
         */
        int left = 0;
        for (int right = 0; right < days.length; right++) {
            if (days[right] == 'W') {
                wholePTODays--;
            }

            while (wholePTODays < 0) {
                if (days[left] == 'W') {
                    wholePTODays++;
                }
                left++;
            }

            double extension = 0.0;
            if ((left > 0 && days[left-1] == 'W') || (right < days.length - 1 && days[right+1] == 'W')) {
                extension += fractionPTODays;
            }

            maxVacation = Math.max(maxVacation, right - left + 1 + extension);
        }
        return maxVacation;
    }

    private char getDayValue(int pointer, char[][] days) {
        return days[pointer / days[0].length][pointer % days[0].length];
    }
}
