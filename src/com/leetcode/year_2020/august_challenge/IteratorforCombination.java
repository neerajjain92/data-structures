package com.leetcode.year_2020.august_challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author neeraj on 13/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class IteratorforCombination {

    public static void main(String[] args) {
        CombinationIterator combinationIterator =
                new CombinationIterator("abc", 2);

        while (combinationIterator.hasNext()) {
            System.out.println(combinationIterator.next());
        }
    }

    static class CombinationIterator {
        List<String> allCombinations;
        int counter = 0;
        public CombinationIterator(String characters, int combinationLength) {
            allCombinations = new ArrayList<>();
            combinations(characters, 0, "", allCombinations, combinationLength);
        }

        private void combinations(String characters, int pointer, String curr,
                                  List<String> allCombinations, int combinationLength) {
            if (curr.length() == combinationLength) {
                allCombinations.add(curr);
                return;
            }
            for (int i = pointer; i < characters.length(); i++) {
                combinations(characters, i + 1,
                        curr + characters.charAt(i), allCombinations, combinationLength);
            }
        }

        public String next() {
            return allCombinations.get(counter++);
        }

        public boolean hasNext() {
            return counter < allCombinations.size();
        }
    }
}
