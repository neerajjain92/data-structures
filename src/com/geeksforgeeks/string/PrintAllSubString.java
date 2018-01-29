package com.geeksforgeeks.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrintAllSubString {

    public static void main(String[] args) {
        printAllSubString("1234");
    }

    public static void printAllSubString(String str) {
        List<String> sortedList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                sortedList.add(str.substring(i, j));
            }
        }
        Collections.sort(sortedList, new LengthComparator());
        System.out.println(sortedList);
    }
}

class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }
}
