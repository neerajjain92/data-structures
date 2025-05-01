package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.*;

public class RobotCollision {

    public static void main(String[] args) {
        RobotCollision obj = new RobotCollision();
        System.out.println(obj.survivedRobotsHealths(new int[]{5, 4, 3, 2, 1}, new int[]{2, 17, 9, 15, 10}, "RRRRR"));
        System.out.println(obj.survivedRobotsHealths(new int[]{3, 5, 2, 6}, new int[]{10, 10, 15, 12}, "RLRL"));
        System.out.println(obj.survivedRobotsHealths(new int[]{1, 2, 5, 6}, new int[]{10, 10, 11, 11}, "RLRL"));
        System.out.println(obj.survivedRobotsHealths(new int[]{1, 40}, new int[]{10, 11}, "RL"));
        System.out.println(obj.survivedRobotsHealths(new int[]{3, 40}, new int[]{49, 11}, "LL"));
        System.out.println(obj.survivedRobotsHealths(new int[]{3, 2, 30, 24, 38, 7}, new int[]{47, 12, 49, 11, 47, 38}, "RRLRRR"));
    }

    /**
     * This problem is exactly similar to {@link AsteroidCollision}
     * with an addition of health here, there health was present in the array itself
     * here health is a separate array
     * Also positions is useless once you sort the items and maintain key information
     * <p>
     * Lastly use direction to simply put + or - sign, then it becomes input of {@link AsteroidCollision}
     */
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        List<Robot> robots = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            robots.add(new Robot(positions[i], healths[i], directions.charAt(i)));
        }
        // Step 2: Sort robots based on position for collision simulation
        robots.sort(Comparator.comparingInt(t -> t.position));

        Stack<Robot> stack = new Stack<>();
        for (Robot currentRobot : robots) {
            boolean destroyed = false;

            while (!stack.isEmpty()
                    && currentRobot.direction == 'L'
                    && stack.peek().direction == 'R') { // Basically robots will cross

                Robot robotAtStackTop = stack.peek();

                if (robotAtStackTop.health < currentRobot.health) {
                    stack.pop();
                    currentRobot.health -= 1;
                } else if (robotAtStackTop.health == currentRobot.health) {
                    destroyed = true;
                    stack.pop();
                    break;
                } else {
                    robotAtStackTop.health -= 1;
                    destroyed = true;
                    break;
                }
            }

            if (!destroyed) {
                stack.push(currentRobot);
            }
        }
        Map<Integer, Integer> positionHealthMap = new HashMap<>();
        while (!stack.isEmpty()) {
            Robot robot = stack.pop();
            positionHealthMap.put(robot.position, Math.abs(robot.health));
        }
        List<Integer> result = new ArrayList<>();
        for (int position : positions) {
            if (positionHealthMap.containsKey(position)) {
                result.add(positionHealthMap.get(position));
            }
        }
        return result;
    }

    static class Robot {
        int position;
        int health;
        char direction;

        public Robot(int position, int health, char direction) {
            this.position = position;
            this.health = health;
            this.direction = direction;
        }
    }
}
