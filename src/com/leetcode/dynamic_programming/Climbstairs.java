package com.jagjit.Hactoberfest;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/climbing-stairs/ Runtime: 0 ms, faster than
 * 100.00% of Java online submissions for Climbing Stairs. Memory Usage: 33.2
 * MB, less than 5.26% of Java online submissions for Climbing Stairs.
 */
public class Climbstairs {

	// below the function which returns count
	public static int countWays(int n) {
		
		int[] dp = new int[n];
		if (n < 2) {
			return 1;
		}
		dp[0] = 1;
		dp[1] = 2;
		for (int i = 2; i < n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n - 1];
	}
	
	
	public static void main(String[] args) {

		// allocating memory for 5 integers.

		List<Integer> result = new ArrayList<Integer>();
		
		result.add(countWays(3));
		result.add(countWays(7));
		result.add(countWays(10));
		result.add(countWays(26));
		result.add(countWays(44));
		
		
		for (Integer i : result) {
			
			//print the value of values
			System.out.print("the Count result are :" + i + "\n");

		}

	}

}
