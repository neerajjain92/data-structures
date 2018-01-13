package com.geeksforgeeks.array;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * ABC
 * <p>
 * generates below permutation
 * ABC ACB BAC BCA CBA CAB
 */
public class StringPermutation {

    public static void main(String[] args) {

        String input = "1234";
        char[] result = new char[input.length()];
        int level = 0;
        List<String> allPermutations = new LinkedList<>();
        permuteUtil(input.toCharArray(), getFrequency(input), result, level, allPermutations);
        allPermutations.forEach(s -> System.out.println(s));
    }

    private static void permuteUtil(char[] input, Map<Character, Long> frequency, char[] result, int level, List<String> allPermutations) {

        if (level == result.length) {
            allPermutations.add(String.valueOf(result));
            return;
        }

        // Let's start from left to right
        for(int i=0;i<input.length;i++) {

            if(frequency.get(input[i]) == 0) { // No available characters at this position
                continue;
            }
            result[level] = input[i]; // Let's include the available character

            frequency.put(input[i], frequency.get(input[i]) - 1); // Since this character is used so in next level this
                                                                 // frequency is reduced by 1

            permuteUtil(input, frequency, result, level+1, allPermutations); // Let's get down till the level where all frequency is 0

            frequency.put(input[i], frequency.get(input[i]) + 1); // Since this level is done, so let's backtrack
        }
    }

    public static void print(char[] result) {
        for (char c : result) {
            System.out.print(c + " ");
        }
        System.out.println();
    }


    public static Map<Character, Long> getFrequency(String input) {
        Map<Character, Long> frequencyMap = input.chars() // Convert String to IntStream
                                                .mapToObj(c -> (char) c) // Convert IntStream to Stream<Character>
                                                .collect(groupingBy(c -> c, counting())); // Create Map<Character, Long>
                                                                                         // where Long is the frequency of that character

        return frequencyMap;
    }
}
