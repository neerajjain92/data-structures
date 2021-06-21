import java.util.Scanner;

/**
 * @author neeraj on 27/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FibonaciPractical {

    public static void main(String[] args) {
        System.out.println("Enter the number upto which Fibonaci Series is required and should be between 5 to 20");
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        if (num < 5 || num > 20) {
            System.out.println("Invalid Input");
            return;
        }
        printFibonaci(num);
    }

    private static void printFibonaci(int num) {
        /**
         * Series will look like this : 1 1 2 3 5 8 13
         */
        int[] fib = new int[num];
        fib[0] = 1; // Since 1st 2 numbers will always be 1
        fib[1] = 1;

        for (int i = 2; i < num; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        print(fib);
    }

    private static void print(int[] fib) {
        int even = 0;
        for (int i : fib) {
            if (i % 2 == 0) even++;
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println("Total Even No" + even);
        System.out.println("Total Odd No" + (fib.length - even));
    }
}
