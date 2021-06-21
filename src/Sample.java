/**
 * @author neeraj on 08/09/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Sample {

    public static void main(String[] args) {
        System.out.println("Is My Number Palindrome " + isPalindrome(123));
        System.out.println("Is My Number Palindrome " + isPalindrome(1221));
        System.out.println("Is My Number Palindrome " + isPalindrome(1234));
        System.out.println("Is My Number Palindrome " + isPalindrome(5555));
    }

    private static Boolean isPalindrome(int number) {
        int reverseNumber = reverse(number);
        return number == reverseNumber;
    }

    public static int reverse(int num) {
        int remainder = 0;
        int reverseNumber = 0;
        while (num > 0) {
            remainder = num % 10; // Remainder
            num = num / 10; // Quotient

            reverseNumber = reverseNumber * 10 + remainder;

        }
        return reverseNumber;
    }
}
