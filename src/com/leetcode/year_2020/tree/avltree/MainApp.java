package com.leetcode.year_2020.tree.avltree;

public class MainApp {

    public static void main(String[] args) {
        Tree<Integer> tree = new AVLTree<>();
        /**
         *
         *         10
         *        /
         *       6
         *      / \
         *     4   9
         */
        tree.insert(10).insert(6).insert(4).insert(9);

        tree.traverse();

        // Add Node 5 on the right of 4
        tree.insert(5);
        tree.traverse();

        // Delete Node 6
        tree.delete(6);
        tree.traverse();

        tree = new AVLTree<>();
        /**
         *     10
         *       \
         *        20
         *         \
         *          30        ================>           30
         *           \                                  /   \
         *            40                              20    40
         *              \                            /       \
         *               50                         10       50
         */
        tree.insert(10).insert(20).insert(30).insert(40).insert(50);
        tree.traverse();

    }
}
