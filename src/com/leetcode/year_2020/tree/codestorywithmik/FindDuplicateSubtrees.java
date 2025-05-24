package com.leetcode.year_2020.tree.codestorywithmik;

import com.leetcode.year_2020.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees {

    public static void main(String[] args) {

    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> subtreeSerializedCount = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();
        getSubTreeString(root, subtreeSerializedCount, result);
        return result;
    }

    private String getSubTreeString(TreeNode root, Map<String, Integer> subtreeSerializedCount, List<TreeNode> result) {
        if (root == null) return "NULL";
        String left = getSubTreeString(root.left, subtreeSerializedCount, result);
        String right = getSubTreeString(root.right, subtreeSerializedCount, result);

        String serializedKey = String.format("%s#%s#%s", root.val, left, right);
        subtreeSerializedCount.put(serializedKey, subtreeSerializedCount.getOrDefault(serializedKey, 0) + 1);
        if (subtreeSerializedCount.get(serializedKey) == 2) {
            result.add(root);
        }
        return serializedKey;
    }
}
