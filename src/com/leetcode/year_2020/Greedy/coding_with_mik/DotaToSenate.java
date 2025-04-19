package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.*;

/**
 * https://leetcode.com/problems/dota2-senate/description/
 * 649. Dota2 Senate
 */
public class DotaToSenate {

    public static void main(String[] args) {
        System.out.println(predictPartyVictory("RD"));
        System.out.println(predictPartyVictory("RDD"));
        System.out.println(predictPartyVictory("RDRD"));
        System.out.println(predictPartyVictory("DRDRDR"));
        System.out.println(predictPartyVictory("RDDRRD"));
        System.out.println(predictPartyVictory("RDRRDDDR"));
        System.out.println(predictPartyVictory("RRRDD"));
        System.out.println(predictPartyVictory("RRRRDDDD"));
        System.out.println(predictPartyVictory("RRRDDDDD"));
        System.out.println(predictPartyVictory("RRRRRRD"));
    }

    public static String predictPartyVictory(String senate) {
        final String RADIANT = "Radiant";
        final String DIRE = "Dire";
        Queue<Integer> radiantQueue = new LinkedList<>();
        Queue<Integer> direQueue = new LinkedList<>();
        int index = 0;
        for (char c : senate.toCharArray()) {
            if (c == 'R') {
                radiantQueue.add(index++);
            } else {
                direQueue.add(index++);
            }
        }

        // In such elections, if a person bans someone he will again gets chance to vote again
        // when everyone from his government has voted, so we add the person to the end of queue again
        int count = senate.length();
        while (!radiantQueue.isEmpty() && !direQueue.isEmpty()) {
            int radiant = radiantQueue.remove();
            int dire = direQueue.remove();
            if (radiant < dire) { // We can ban 'DIRE', as 'Radiant' came early
                radiantQueue.add(count++);
            } else {
                direQueue.add(count++);
            }
        }
        return radiantQueue.isEmpty() ? DIRE : RADIANT;
    }
}
