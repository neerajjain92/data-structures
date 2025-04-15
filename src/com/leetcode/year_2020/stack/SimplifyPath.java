package com.leetcode.year_2020.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode.com/problems/simplify-path/description/
 * 71. Simplify Path
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
@SuppressWarnings({"JavadocLinkAsPlainText", "UnusedReturnValue"})
public class SimplifyPath {

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        simplifyPath("/home/");
        simplifyPath("/home//foo/");
        simplifyPath("/home/user/Documents/../Pictures");
        simplifyPath("/../");
        simplifyPath("/.../a/../b/c/../d/./");
        simplifyPath("/.../a/../b/c/../d/./");
        simplifyPath("..");
    }

    public static String simplifyPath(String path) {
        final Set<String> skipThese = Set.of("..", ".", "");
        final Stack<String> stack = new Stack<>();
        for (String dir : path.split("/")) { // Now the dir only contains everything except the '/'
            // Hence when we get to '..' and we have assumed this path to simplify '/home/user/Documents/../Pictures'
            // when we reach to '..'
            // Our stack looks like this
            //      |           |
            //      | documents |  <====== Top when you encountered '..', ideally we should remove 2 entries from stack but since we already splitted the String with '/' we just have to remove one
            //      | users     |
            //      | home      |
            //      -------------
            // So after finishing you have
            //
            //      | pictures |
            //      | users    |
            //      | home     |
            //      ============
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!skipThese.contains(dir)) {
                stack.push(dir);
            }
        }

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
