package com.leetcode.year_2020.DP.dynamic_programming_on_trees;

import java.util.*;

/**
 * Diameter of N-Ary Tree
 * Leetcode 1522
 * https://leetcode.com/problems/diameter-of-n-ary-tree/description/
 * https://leetcode.ca/all/1522.html
 * <p>
 * // Definition for a NArrayNode.
 * class NArrayNode {
 * public int val;
 * public List<NArrayNode> children;
 * <p>
 * <p>
 * public NArrayNode() {
 * children = new ArrayList<NArrayNode>();
 * }
 * <p>
 * public NArrayNode(int _val) {
 * val = _val;
 * children = new ArrayList<NArrayNode>();
 * }
 * <p>
 * public NArrayNode(int _val,ArrayList<NArrayNode> _children) {
 * val = _val;
 * children = _children;
 * }
 * };
 */
@SuppressWarnings({"JavadocLinkAsPlainText", "JavadocBlankLines", "ClassEscapesDefinedScope", "DataFlowIssue"})
public class DiameterOfNArrayTree {


    public static void main(String[] args) {
        int[] result = new int[1];
        NArrayNode root = new NArrayNode(1);
        NArrayNode three = new NArrayNode(2);
        NArrayNode two = new NArrayNode(0);
        NArrayNode four = new NArrayNode(0);
        NArrayNode five = new NArrayNode(0);
        NArrayNode six = new NArrayNode(0);

        three.children = List.of(five, six);
        root.children = List.of(three, two, four);

        diameterOfNArrayTree(root, result);
        System.out.println(result[0]);
    }

    public static int diameterOfNArrayTree(NArrayNode root, int[] result) {
        if (root == null) return 0;

        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int i = 0; i < root.children.size(); i++) {
            int diameter = diameterOfNArrayTree(root.children.get(i), result);
            if (diameter > firstMax) {
                secondMax = firstMax;
                firstMax = diameter;
            } else if (diameter > secondMax) {
                secondMax = diameter;
            }
        }
        result[0] = Math.max(result[0], 1 + firstMax + secondMax);
        return 1 + firstMax;
    }

}

// Definition for a NArrayNode.
class NArrayNode {
    public int val;
    public List<NArrayNode> children;


    public NArrayNode() {
        children = new ArrayList<NArrayNode>();
    }

    public NArrayNode(int _val) {
        val = _val;
        children = new ArrayList<NArrayNode>();
    }

    public NArrayNode(int _val, ArrayList<NArrayNode> _children) {
        val = _val;
        children = _children;
    }
};

class TreeBuilder {
    public static NArrayNode buildNArrayTree(List<Integer> data) {
        if (data == null || data.isEmpty()) return null;

        Iterator<Integer> iter = data.iterator();
        Integer rootVal = iter.next();
        if (rootVal == null) return null;

        NArrayNode root = new NArrayNode(rootVal);
        Queue<NArrayNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty() && iter.hasNext()) {
            NArrayNode parent = queue.poll();

            while (iter.hasNext()) {
                Integer val = iter.next();
                if (val == null) break;

                NArrayNode child = new NArrayNode(val);
                parent.children.add(child);
                queue.offer(child);
            }
        }

        return root;
    }
}


