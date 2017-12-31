import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    static void printHello() {
        System.out.println("Hello from printHello");
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Hello World!");

        Class<?> MainClass = Class.forName("Main");

        Method initMethod = MainClass.getDeclaredMethod("printHello");

        initMethod.invoke(null,new Object[]{});
    }
}
