package com.leetcode.year_2020.tree.avltree;

public class AVLTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public Tree<T> insert(T data) {
        root = insert(data, root);
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            return new Node<>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        }
        if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        }

        // Now we have to perform 2 things, increment the height of node
        updateHeight(node);

        // And another is to perform rotation, depending on the balance
        return applyRotation(node);
    }

    int balance(Node node) {
        return getHeight(node.getLeftChild()) - getHeight(node.getRightChild());
    }

    private Node<T> applyRotation(Node<T> node) {
        int balance = balance(node);

        if (balance < -1) {
            // Right Heavy, So we have to perform left rotation
            if (balance(node.getRightChild()) > 0) {
                /**
                 *
                 *           30
                 *             \
                 *              40
                 *             /
                 *           35
                 *
                 *  if Right Subtree balance is (balance<0)
                 *
                 */
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }

        if (balance > 1) {
            // Left heavy, so we have to perform right rotation
            if (balance(node.getLeftChild()) < 0) {
                /**
                 *
                 *          10
                 *        /
                 *       6
                 *        \
                 *         9
                 *  if left Subtree balance is (balance<0)
                 *
                 */
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }

        return node;
    }

    /**
     * *           30                                     40
     * *             \                                   /  \
     * *              40 =====Left Rotation======>      30   45
     * *             /  \                                \
     * *           35   45                                35
     */
    private Node<T> rotateLeft(Node<T> node) {
        Node rightNode = node.getRightChild();
        Node centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(node);
        node.setRightChild(centerNode);
        updateHeight(node);
        updateHeight(rightNode);

        return rightNode;
    }

    /**
     * *          10                                     6
     * *        /                                      /  \
     * *       6     ===Right Rotation==========>    4    10
     * *     /  \                                         /
     * *   4     9                                       9
     */
    private Node<T> rotateRight(Node<T> node) {
        Node leftNode = node.getLeftChild();
        Node centerNode = leftNode.getRightChild();
        node.setLeftChild(centerNode);
        leftNode.setRightChild(node);
        updateHeight(node);
        updateHeight(leftNode);

        return leftNode;
    }

    private void updateHeight(Node<T> node) {
        int maxHeight = Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
        node.setHeight(maxHeight + 1);
    }

    private int getHeight(Node<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    @Override
    public void delete(T data) {
        root = delete(root, data);
    }

    private Node<T> delete(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(node.getLeftChild(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(node.getRightChild(), data));
        } else {
            // This node to be deleted
            // Either one children, or leafNode(No children)
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            // Handle a scenario where both children are present.
            // Find Maximum in Left Subtree
            T maxNode = getMax(node.getLeftChild());
            node.setData(maxNode);
            // Now let's delete the respective maxNode to avoid duplicate
            node.setLeftChild(delete(node.getLeftChild(), maxNode));
        }

        // Same as insertion
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void traverse() {
        System.out.println(">>>>>>>>>>>>>>Inorder Traversal<M<<<<<<<<<<<<<<");
        inorderTraversal(root);
        System.out.println("<<<<<<<<<<<<<<END>>>>>>>>>>>>>>>>>>>>");
    }

    private void inorderTraversal(Node<T> root) {
        // Left-Parent-Right
        if (root == null) {
            return;
        }
        inorderTraversal(root.getLeftChild());
        System.out.println(root.getData());
        inorderTraversal(root.getRightChild());
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
