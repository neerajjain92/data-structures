package com.leetcode.year_2020.stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * You are provided with an absolute path representing the current working directory
 * cwd in a Unix-style file system. Additionally, you are given a relative path, denoted as cd, which
 * instructs a change to the current working directory. Your task is to determine and output
 * the simplified canonical path that results from applying the relative path, cd, to the
 * initial cwd.
 * Return the simplified canonical path.
 * <p>
 * Example 1:
 * Input: cwd = "/a/b/c", cd = "/d/./e"
 * Output: "/d/e"
 * Example 2:
 * Input: cwd = "", cd = "/d/./e"
 * Output: "/d/e"
 * Example 3:
 * Input: cwd = "/a/b/c", cd = ""
 * Output: "/a/b/c"
 * Example 4:
 * Input: cwd = "/a/b", cd = ".//c/../../d/f"
 * Output:"/a/d/f"
 */
public class SimplifyPath2 {

    public static void main(String[] args) {
        SimplifyPath2 simplifyPath2 = new SimplifyPath2();
        simplifyPath2.simplifyPath("/a/b/c", "/d/./e");
        simplifyPath2.simplifyPath("", "/d/./e");
        simplifyPath2.simplifyPath("/a/b/c", "");
        simplifyPath2.simplifyPath("/a/b", ".//c/../../d/f");
    }

    /**
     * So basically this is an extension to {@link SimplifyPath}
     * here when we are in a current working directory on linux assume
     * pwd = /Users/jain.neeraj/Projects/github/data-structures
     * and now you want to change the directory
     * cd /Users/jain.neeraj/Desktop
     * <p>
     * So if you notice whenever we are starting with '/' in cd that means we are starting from root
     * so at that point in time our cwd can be just ""
     */
    public String simplifyPath(String cwd, String cd) {
        Stack<String> stack = new Stack<>();
        if (cd != null && cd.startsWith("/")) {
            cwd = "";
        }
        Set<String> charsToSkip = Set.of("..", ".", "");
        // This piece is the same as SimplifyPath1 a problem
        for (String path : cwd.split("/")) {
            if (path.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!charsToSkip.contains(path)) {
                stack.push(path);
            }
        }

        // Now we should also consider cd
        if (cd != null) {
            for (String path : cd.split("/")) {
                if (path.equals("..") && !stack.isEmpty()) {
                    stack.pop();
                } else if (!charsToSkip.contains(path)) {
                    stack.push(path);
                }
            }
        }

        // Now my stack contains the answer
        String[] splitPath = new String[stack.size()];
        while (!stack.isEmpty()) {
            splitPath[stack.size() - 1] = stack.pop();
        }
        // Now let's insert forward slash
        StringBuilder sb = new StringBuilder();
        for (String s : splitPath) {
            sb.append("/").append(s);
        }
        String ans = sb.isEmpty() ? "/" : sb.toString();
        System.out.println(ans);
        return ans;
    }
}
