package com.leetcode.year_2020.graph.dfs;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 339. Nested List Weight Sum
 * Description
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 * <p>
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 * <p>
 * Return the sum of each integer in nestedList multiplied by its depth.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: nestedList = [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
 * Example 3:
 * <p>
 * Input: nestedList = [0]
 */
public class NestedListWeightSum {

    public static void main(String[] args) {
        List<NestedInteger> input = new ArrayList<>();

        // [1,1]
        NestedInteger list1 = new NestedInteger();
        list1.add(new NestedInteger(1));
        list1.add(new NestedInteger(1));

        // 2
        NestedInteger num2 = new NestedInteger(2);

        // [1,1]
        NestedInteger list2 = new NestedInteger();
        list2.add(new NestedInteger(1));
        list2.add(new NestedInteger(1));

        input.add(list1);
        input.add(num2);
        input.add(list2);

        System.out.println(input); // should print [[1,1],2,[1,1]]

        NestedListWeightSum obj = new NestedListWeightSum();
        System.out.println(obj.depthSum(input));

        // [1, [2, [3, [4, 5]], 6], 7]
        input = new ArrayList<>();

        // 1
        input.add(new NestedInteger(1));

        // [2, [3, [4, 5]], 6]
        NestedInteger level1 = new NestedInteger();
        level1.add(new NestedInteger(2));

        // [3, [4, 5]]
        NestedInteger level2 = new NestedInteger();
        level2.add(new NestedInteger(3));

        // [4, 5]
        NestedInteger level3 = new NestedInteger();
        level3.add(new NestedInteger(4));
        level3.add(new NestedInteger(5));

        // Nest level3 inside level2
        level2.add(level3);

        // Nest level2 inside level1
        level1.add(level2);

        // Add 6 to level1
        level1.add(new NestedInteger(6));

        // Add level1 to input
        input.add(level1);

        // Add 7
        input.add(new NestedInteger(7));

        System.out.println(input);
        System.out.println(obj.depthSum(input));

    }

    public int depthSum(List<NestedInteger> nestedList) {
//        return dfs(nestedList, 1);
        return bfs(nestedList);
    }

    private int bfs(List<NestedInteger> nestedList) {
        Queue<List<NestedInteger>> queue = new LinkedList<>();
        queue.add(nestedList);

        int sum = 0;
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                for(NestedInteger n : queue.poll()) {
                    if (n.isInteger()) {
                        sum += n.getInteger() * level;
                    } else {
                        queue.add(n.getList());
                    }
                }
            }
            level++;
        }
        return sum;
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * depth;
            } else {
                sum += dfs(nestedInteger.getList(), depth + 1);
            }
        }
        return sum;
    }

    static class NestedInteger {
        Integer val;
        @Getter
        List<NestedInteger> list;

        public NestedInteger(int val) {
            this.val = val;
        }

        public NestedInteger() {
            this.list = new ArrayList<>();
        }

        public void add(NestedInteger nestedInteger) {
            list.add(nestedInteger);
        }

        public boolean isInteger() {
            return val != null && (list == null || list.isEmpty());
        }

        public Integer getInteger() {
            return val;
        }

        @Override
        public String toString() {
            if (isInteger()) return String.valueOf(val);
            List<String> parts = new ArrayList<>();
            for (NestedInteger nestedInteger : list) {
                parts.add(nestedInteger.toString());
            }
            return "[" + String.join(",", parts) + "]";
        }
    }
}
