import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    static void printHello() {
        System.out.println("Hello from printHello");
    }

    public static void printPattern() {
        /**
         * # # # # #
         * # #   # #
         * #   #   #
         * # #   # #
         * # # # # #
         */
        for (int i = 0; i < 5; i++) { // Rows
            for (int j = 0; j < 5; j++) {
                switch (i) {
                    case 1:
                    case 3:
                        if (j == 2) System.out.print(" ");
                        else System.out.print("*");
                        break;
                    case 2:
                        if (j == 1 || j == 3) System.out.print(" ");
                        else System.out.print("*");
                        break;
                    default:
                        System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    public static void decimalToBinary1(int number) {
        int remainder, temp = number;
        int bin = 1;
        while (number > 0) {
            remainder = number % 2;
            bin = bin * 10 + remainder;
            number /= 2;
        }

        System.out.println("Binary before reversal in version 1 is " + bin);
        int reverse = 0;
        while (bin > 1) {
            reverse = reverse * 10 + bin % 10;
            bin /= 10;
        }
        System.out.println("Binary of " + temp + " in version 1 is " + reverse);
    }

    public static void decimalToBinary(int number) {
        int remainder, multiple = 1, binary = 0, temp = number, power = 0;
        while (number != 0) {
            remainder = number % 2;
            binary = (int) (binary + remainder * Math.pow(10, power));
            power++;
            number = number / 2;
        }
        System.out.println("Binary of " + temp + " is " + binary);
    }

    public static void print() {
        /**
         *         1 2 3 4 5 6 7 6 5 4 3 2 1
         *         1 2 3 4 5 6	 6 5 4 3 2 1
         *         1 2 3 4 5	   5 4 3 2 1
         *         1 2 3 4			 4 3 2 1
         *         1 2 3			   3 2 1
         *         1 2					 2 1
         *         1					   1
         */

        //2*i 0
        //2*1 2
        //2*2 4
        //2*3 6
        //2*4 8
        int leftLimit = 7;
        int rightLimit = 7;
        for (int i = 0; i < 7; i++) {

            for (int j = 1; j <= leftLimit; j++) {
                System.out.print(j + " ");
            }
            for (int k = 0; k < 2 * i; k++) {
                System.out.print(" ");
            }
            if (i > 1) {
                for (int k = 0; k < i - 1; k++) {
                    System.out.print("  ");
                }
            }
            for (int l = rightLimit; l > 0; l--) {
                if (i == 0 && l == rightLimit) continue;
                System.out.print(l + " ");
            }
            leftLimit--;
            rightLimit--;
            System.out.println();
        }
    }

    public static void printStar() {
        /**
         * * * * * * * * * * * * * *
         * * * * * * *   * * * * * *
         * * * * * *       * * * * *
         * * * * *           * * * *
         * * * *               * * *
         * * *                   * *
         * *                       *
         */
        int leftLimit = 7;
        int rightLimit = 7;
        for (int i = 0; i < 7; i++) {

            // Left Portion
            for (int j = 1; j <= leftLimit; j++) {
                System.out.print("* ");
            }
            // Middle Space
            for (int k = 0; k < 2 * i - 1; k++) {
                System.out.print(" ");
            }
            // Right Portion
            for (int l = rightLimit; l > 0; l--) {
                if (i == 0 && l == rightLimit) continue;
                if (i == 0 && l == rightLimit - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" *");
                }
            }
            leftLimit--;
            rightLimit--;
            System.out.println();
        }
        /**
         * *************
         * ****** ******
         * *****   *****
         * ****     ****
         * ***       ***
         * **         **
         * *           *
         */
    }

    public static void printBakshi() {
        int n = 5;
        int t = n - 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= t; j++) {
                System.out.print("*");
            }
            t = n - 1;
            t = t + 1 - i;
            System.out.println();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        print();
        printStar();
        printBakshi();

//        printPattern();
//        decimalToBinary(216);
//        decimalToBinary1(216);

//        decimalToBinary(7);
//        decimalToBinary1(7);
//        decimalToBinary(8);
//        decimalToBinary1(8);
//        decimalToBinary(15);
//        decimalToBinary1(15);
//        decimalToBinary(13);
//        decimalToBinary1(13);

//        Class<?> MainClass = Class.forName("Main");
//
//        Method initMethod = MainClass.getDeclaredMethod("printHello");
//
//        initMethod.invoke(null,new Object[]{});

        final String secretKey = "Bar12345Bar12345";

        String originalString = "Data Security By Iv";

        SecureRandom secRandom = new SecureRandom();
        byte[] byteArr = secRandom.generateSeed(16);

        String encryptedString = AES.encrypt(originalString, secretKey, byteArr);
        String decryptedString = AES.decrypt(encryptedString, secretKey, byteArr);

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}


class AES {

    public static String encrypt(String strToEncrypt, String secret, byte[] byteArr) {
        try {

            IvParameterSpec randomIvSpec = new IvParameterSpec(byteArr);
            SecretKeySpec skeySpec = new SecretKeySpec(secret.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, randomIvSpec, new SecureRandom());
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret, byte[] byteArr) {
        try {

            IvParameterSpec randomIvSpec = new IvParameterSpec(byteArr);
            SecretKeySpec skeySpec = new SecretKeySpec(secret.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, randomIvSpec, new SecureRandom());
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
