package com.datastructures.mustDoInterviewQuestions.string;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaine03 on 24/07/17.
 */
public class Anagram {

    public static void main(String[] args) {
        String first = "listen";
        String second = "silent";
        System.out.println("Are " + first + " and " + second + " anagram " + isAnagram(first, second));
    }

    private static int[] numofLetterNeedtoChange(String a[], String b[]) {
        int[] answers = new int[a.length];

        //Assuming Length of A and B is same
        for(int i=0;i<a.length && i<b.length;i++){
            String first = a[i];
            String second = b[i];

            if(first.length() != second.length()){
                answers[i] = -1;
            }
        }
        return null;
    }

    public static Boolean isAnagram(String first, String second) {
        Map<Character, Integer> frequencyCountMap1 = new HashMap<>();
        Map<Character, Integer> frequencyCountMap2 = new HashMap<>();

        for (char c : first.toCharArray()) {
            if (frequencyCountMap1.containsKey(c)) {
                frequencyCountMap1.put(c, frequencyCountMap1.get(c) + 1);
            } else {
                frequencyCountMap1.put(c, 1);
            }
        }

        for (char c : second.toCharArray()) {
            if (frequencyCountMap2.containsKey(c)) {
                frequencyCountMap2.put(c, frequencyCountMap2.get(c) + 1);
            } else {
                frequencyCountMap2.put(c, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : frequencyCountMap1.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if(frequencyCountMap2.get(key) != value){
                return false;
            }
        }
        return true;
    }
}
