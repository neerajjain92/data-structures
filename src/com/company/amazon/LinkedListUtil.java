package com.company.amazon;

public class LinkedListUtil {

    public Node head;

    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public void append(int data) {
        Node temp = head;
        Node newNode = new Node(data);
        if (temp == null) {
            this.head = newNode;
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " , ");
            temp = temp.next;
        }
        System.out.println();
    }

    public Node reverse(Node head) {
        Node temp = head;
        Node tempNext = null;
        Node prev = null;

        while (temp != null) {
            tempNext = temp.next;
            temp.next = prev;
            prev = temp;
            temp = tempNext;
        }

        head = prev;
        return head;
    }

    public Node getMiddleNode(Node head) {
        Node slowPointer = head;
        Node fastPointer = head;

        while (fastPointer != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (fastPointer != null && fastPointer.next == null) {
                fastPointer = fastPointer.next;
            }
        }
        return slowPointer;
    }

    public static boolean isEvenList(Node head) {
        int counter = 0;
        Node temp = head;
        while (temp != null) {
            counter++;
            temp = temp.next;
        }
        return counter % 2 == 0 ? true : false;
    }

    /**
     * 1) Find Middle of the List
     * 2) Reverse the Second Half
     * 3) Compare both 1st half and 2nd Half
     * 4) Re-Reverse the Original List
     *
     * @param head
     * @return
     */
    public boolean isListPalindrome(Node head) {
        Node middleNode = getMiddleNode(head);
        Node secondHalfReversedHead = reverse(middleNode);
        printList(secondHalfReversedHead);

        // This can be calculated as a part of finding the middle node
        Boolean isEvenList = isEvenList(head);
        Node temp = head;
        return compareList(temp, middleNode, secondHalfReversedHead, middleNode, isEvenList);
    }

    public boolean compareList(Node firstList, Node firstListEnd, Node secondList, Node secondListEnd, Boolean isEvenList) {
        Node tempA = firstList;
        Node tempB = secondList;

        while (tempA != firstListEnd && tempB != null) {
            if (tempA.data != tempB.data) {
                return false;
            }
            tempA = tempA.next;
            tempB = tempB.next;
            if (isEvenList) {
                if (tempA == firstListEnd) {
                    break;
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
        LinkedListUtil util = new LinkedListUtil();

        util.append(1);
        util.append(2);
        util.append(3);
        util.append(2);
        util.append(1);

        util.printList(util.head);

//        util.head = util.reverse(util.head);

//        util.printList();

        System.out.println("Is List Palindrome ? " + util.isListPalindrome(util.head));
    }
}
