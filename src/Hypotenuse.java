import java.util.Scanner;

/**
 * @author neeraj on 26/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Hypotenuse {

    public static void main(String[] args) {
        /**
         * We will use pythagoras theorem for this.
         * a^2+b^2 = c^2;
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter base and height of the triangle in next 2 lines");
        int base = scanner.nextInt();
        int height = scanner.nextInt();

        System.out.println("Hypotenuse of the triangle is " + calculateHypotenuse(base, height) + "cm");
    }

    private static double calculateHypotenuse(int base, int height) {
        return Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2));
    }
}
