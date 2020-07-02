package com.leetcode.year_2020.sliding_window;

import com.util.LogUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author neeraj on 01/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestSubStringWithMUniqueCharacters {

    public static void main(String[] args) {
        longestSubStringWithMUniqueCharacters("KATAPPA", 2);
        longestSubStringWithMUniqueCharacters("KATAPPA", 3);
    }

    public static int longestSubStringWithMUniqueCharacters(String str, int M) {
        int beg = 0;
        int end = 0;
        int res = 0;
        String LSS = "";
        while (end < str.length()) {
            int unique = unique(str, beg, end, M);
            if (unique <= M) {
                if (unique == M) {
                    if(res < end - beg + 1) {
                        res = end - beg + 1;
                        LSS = str.substring(beg, end+1);
                    }
                }
                end++;
            } else {
                beg++;
            }
        }
        LogUtil.logIt("Total Substring with M Distinct ==> " + res + " and substring is "+ LSS);
        return res;
    }

    public static int unique(String str, int beg, int end, int M) {
        Set<Character> set = new HashSet<>();
        for (int i = beg; i <= end; i++) {
            set.add(str.charAt(i));
        }
        return set.size();
    }
}
