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

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Hello World!");

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
