package com.datastructures.mustDoInterviewQuestions.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jaine03 on 27/07/17.
 * <p>
 * Inside Lot :
 * <p>
 * 0 ---> Empty Cell
 * 1 ---> Fresh Orange
 * 2 ---> Rotten Orange
 */
public class RottenOrange {

    static class Orange {
        int x;
        int y;

        public Orange(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int R = 3;
    public static int C = 5;

    /**
     * @param lot
     * @return
     */
    public static Boolean anyFreshOrangeInLot(int[][] lot) {
        for (int i = 0; i < lot.length; i++)
            for (int j = 0; j < lot.length; j++)
                if (lot[i][j] == 1)
                    return true;
        return false;
    }

    public static Boolean isDelim(Orange orange) {
        return orange.x == -1 && orange.y == -1;
    }

    public static Boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < R && y < C;
    }

    /**
     * Check if We can rot all oranges or not
     *
     * @param lot
     * @return
     */
    public static int rotOranges(int[][] lot) {
        Queue<Orange> queue = new LinkedList<>();
        Integer totalPassRequired = 0;
        for (int i = 0; i < lot.length; i++)
            for (int j = 0; j < lot[i].length; j++)
                queue.add(new Orange(i, j));

        // Adding Delimiter
        queue.add(new Orange(-1, -1));

        while (!queue.isEmpty()) {
            Boolean flag = false;

            while (!isDelim(queue.peek())) {

                Orange temp = queue.peek();

                // Checking the Bottom side of Element
                if (isValid(temp.x + 1, temp.y) && lot[temp.x + 1][temp.y] == 1) {
                    if (!flag) {
                        totalPassRequired++;
                        flag = true;
                    }
                    //Making fresh orange as rotten.
                    lot[temp.x + 1][temp.y] = 2;
                    queue.add(new Orange(temp.x + 1, temp.y));
                }
                // Checking the Top side of Element
                if (isValid(temp.x - 1, temp.y) && lot[temp.x - 1][temp.y] == 1) {
                    if (!flag) {
                        totalPassRequired++;
                        flag = true;
                    }
                    //Making fresh orange as rotten.
                    lot[temp.x - 1][temp.y] = 2;
                    queue.add(new Orange(temp.x - 1, temp.y));
                }
                // Checking the Right side of Element
                if (isValid(temp.x, temp.y + 1) && lot[temp.x][temp.y + 1] == 1) {
                    if (!flag) {
                        totalPassRequired++;
                        flag = true;
                    }
                    //Making fresh orange as rotten.
                    lot[temp.x][temp.y + 1] = 2;
                    queue.add(new Orange(temp.x, temp.y + 1));
                }
                // Checking the Left side of Element
                if (isValid(temp.x, temp.y - 1) && lot[temp.x][temp.y - 1] == 1) {
                    if (!flag) {
                        totalPassRequired++;
                        flag = true;
                    }
                    //Making fresh orange as rotten.
                    lot[temp.x][temp.y - 1] = 2;
                    queue.add(new Orange(temp.x, temp.y - 1));
                }
                queue.remove();
            }
            queue.remove();

            if(!queue.isEmpty()){
                queue.add(new Orange(-1,-1));
            }
        }
        if(anyFreshOrangeInLot(lot))
            return -1;
        return totalPassRequired;
    }


    public static void main(String[] args) {
        int arr[][] = {{2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};
        int answer = rotOranges(arr);
        System.out.println(answer);
    }
}
