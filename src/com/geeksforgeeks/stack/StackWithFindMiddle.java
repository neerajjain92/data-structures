package com.geeksforgeeks.stack;

public class StackWithFindMiddle {

    DLLNode top;
    DLLNode middle;
    int size;

    public StackWithFindMiddle() {
        size = 0;
    }

    // Helper class for Stack with Find Middle and Delete Middle operation
    class  DLLNode {
        DLLNode prev;
        DLLNode next;
        int data;

        public DLLNode(int data) {
            this.data = data;
        }
    }

    /**
     * 1) Simple Rule for Pushing , the first element entered will be out last so for the first element there is no nextElement
     * but only previous element, think of this as a Stack of Trays placed, the last plate has no next and first plate has no previous
     * <p>
     * |  |
     * |77| <--- = Top Element
     * |66|
     * |55|
     * |44|
     * |33|
     * |22|
     * |11| <---- = First Element Inserted
     * ----
     * <p>
     * So how we will push after first element.
     *
     * @param data
     */
    public void push(int data) {
        DLLNode newNode = new DLLNode(data);

        if (top == null) {
            top = newNode;  // Since this is the first element so the TOP point to it.
            size++;
            middle = top; // If only 1 element hence middle element point to it.
        } else {
            DLLNode temp = top;
            temp.prev = newNode;
            temp.prev.next = temp;
            top = temp.prev;
            size++;
        }
        if (size % 2 == 0) {
            middle = middle.prev;
        }
    }

    public int pop() {

        // If No Item in stack
        if (top == null) {
            System.out.println("Stack Underflow");
            return 0;
        }

        // If only 1 item in stack
        if (top.next == null) {
            DLLNode temp = top;
            top = middle = null;
            --size;
            return temp.data;
        }

        DLLNode temp = top;
        temp.next.prev = null;
        size--;
        top = temp.next;
        if (size % 2 == 1) {
            middle = middle.next;
        }
        return temp.data;
    }

    public DLLNode findMiddle() {
        return middle;
    }

    public static void main(String[] args) {
        StackWithFindMiddle stack = new StackWithFindMiddle();

        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        stack.push(55);
        stack.push(66);
        stack.push(77);

        System.out.println("Item popped is " + stack.pop());
        System.out.println("Item popped is " + stack.pop());
        System.out.println("Middle Element is " + stack.findMiddle().data);
    }
}
