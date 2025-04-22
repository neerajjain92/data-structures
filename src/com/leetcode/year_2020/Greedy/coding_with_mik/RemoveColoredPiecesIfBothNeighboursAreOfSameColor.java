package com.leetcode.year_2020.Greedy.coding_with_mik;

public class RemoveColoredPiecesIfBothNeighboursAreOfSameColor {

    public static void main(String[] args) {
        RemoveColoredPiecesIfBothNeighboursAreOfSameColor obj = new RemoveColoredPiecesIfBothNeighboursAreOfSameColor();
        System.out.println(obj.winnerOfGame("AAABABB"));
        System.out.println(obj.winnerOfGame("AA"));
        System.out.println(obj.winnerOfGame("ABBBBBBBAAA"));
        System.out.println(obj.winnerOfGame("AAAABBBB"));
    }

    /**
     * A much simpler just read theory of other method but instead of sliding window and 2 traversal
     * we just do 1 single traversal and calculate count
     *
     * But wtf leetcode shared this is a slower solution than the previous approach
     * WTFFFFFFFF    18 ms | Beats 13.94% so stick to the other one
     */
    public boolean winnerOfGame2(String colors) {
        int alice = 0, bob = 0;
        for (int i = 1; i < colors.length() - 1; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1) && colors.charAt(i) == colors.charAt(i + 1)) {
                if (colors.charAt(i) == 'A') {
                    alice++;
                } else {
                    bob++;
                }
            }
        }
        return alice > bob;
    }


    /**
     * The key observation is that regardless of what other person plays, the other person chances
     * won't get impacted due to that, So the chances are fixed initially
     * <p>
     * try with any combination and it's true
     * A A A B B B A A A B B
     * you can try anything any combination , regardless of who runs first as well, the chanes are limited, A get 2 chances[index 1, 8]
     * and B will get just 1 chance where it has both left and right 'B'
     * <p>
     * So intuition is pretty simple, scan through the array twice one for Alice and next for bob
     * to calculate total moves possible in one shot, we are using sliding window for this
     * For Alice
     * L R  once R reahces either B or end, we calculate (R - L) to get all A's and then -2 to remove 2 boundries of A to get total chances
     * we do it for all groups of A and at the end do the same for B
     * <p>
     * if chances(A) > chances(B) return Alice winner else bob
     * <p>
     * Self Solution: 13 ms Beats 75.70%
     */
    public boolean winnerOfGame(String colors) {
        int left = 0, right = 0, aliceCount = 0, bobCount = 0;

        while (right < colors.length()) {
            if (colors.charAt(right) == 'B') {
                aliceCount += Math.max(0, right - left - 2);
                right++;
                left = right;
            } else {
                right++;
            }
        }
        aliceCount += Math.max(0, right - left - 2);

        if (aliceCount == 0) {
            return false; // early exit, Alice couldn't even play it's first chance
        }

        left = 0;
        right = 0;
        while (right < colors.length()) {
            if (colors.charAt(right) == 'A') {
                bobCount += Math.max(0, right - left - 2);
                if (bobCount > aliceCount) {
                    return false;
                }
                right++;
                left = right;
            } else {
                right++;
            }
        }
        bobCount += Math.max(0, right - left - 2);
        return aliceCount > bobCount;
    }
}
