package com.util;

/**
 * @author neeraj on 2019-05-04
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LogUtil {

    public static void logInSingleLine(String msg) {
        System.out.print(msg + "\t");
    }

    public static void logInSingleLine(int entry) {
        System.out.print(entry + "\t");
    }

    public static void logIt(String msg) {
        System.out.println(msg);
    }

    public static void newLine() {
        System.out.println();
    }

    public static void logIt(String str, Boolean shouldAddSeparator) {
        System.out.println((shouldAddSeparator == true ? "============== " : "") + str + (shouldAddSeparator == true ? " ==============" : ""));
    }

    public static void printMultiDimensionArray(int[][] arr) {
        logIt("Start of Array", true);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                logInSingleLine(arr[i][j]);
            }
            newLine();
        }
        logIt("End of Array", true);
    }

    public static void printArray(int[] arr) {
        logIt("Start of Array", true);
        for (int i = 0; i < arr.length; i++) {
            logInSingleLine(arr[i]);
        }
        newLine();
        logIt("Start of Array", true);
    }

    public static void printMultiDimensionArray(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            newLine();
        }
    }
}
