package com.leetcode.year_2020.august_challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 12/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PascalTriangle {

    public static void main(String[] args) {
        getRow(3);
    }

    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> rows = new ArrayList<>();
        rows.add(Arrays.asList(1));
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> currentRow = new ArrayList<>();
            List<Integer> previousRow = rows.get(i - 1);
            currentRow.add(1);
            for (int j = 1; j <= previousRow.size(); j++) {
                if (j - 1 >= 0 && j < previousRow.size()) {
                    currentRow.add(previousRow.get(j - 1) + previousRow.get(j));
                } else {
                    currentRow.add(1);
                }
            }
            rows.add(currentRow);
        }
        System.out.println(rows);
        return rows.get(rowIndex);
    }
}
