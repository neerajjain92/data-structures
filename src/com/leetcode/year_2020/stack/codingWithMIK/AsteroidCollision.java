package com.leetcode.year_2020.stack.codingWithMIK;

import com.util.LogUtil;

import java.util.Stack;

/**
 * https://leetcode.com/problems/asteroid-collision/
 * 735. Asteroid Collision
 */
public class AsteroidCollision {

    public static void main(String[] args) {
        AsteroidCollision obj = new AsteroidCollision();
        LogUtil.printArray(obj.asteroidCollision(new int[]{5, 10, -5}));
        LogUtil.printArray(obj.asteroidCollision(new int[]{8, -8}));
        LogUtil.printArray(obj.asteroidCollision(new int[]{10, 2, -5}));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        boolean isDestroyed;
        for (int ast : asteroids) {
            isDestroyed = false;

            // If asteroid is moving in left direction (ast < 0)
            while (!stack.isEmpty() && ast < 0 && stack.peek() > 0) {
                int top = stack.peek();
                int absValOfAst = Math.abs(ast);

                if (top < absValOfAst) {
                    stack.pop(); // current top asteroid explodes
                    // keep continuing, you might explode some more
                } else if (top == absValOfAst) {
                    stack.pop();
                    isDestroyed = true; // both asteroid explodes
                    break;
                } else {
                    isDestroyed = true; // smaller asteroid explodes
                    break;
                }
            }

            if (!isDestroyed) {
                stack.push(ast);
            }
        }

        // Convert Stack to array (in correct order)
        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;

    }
}
