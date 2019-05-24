package classloader;

import commonbeans.People;

import java.lang.reflect.Field;

public class ClassTest {
    public static void main(String[] args) {
//        java.lang.ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        Class<?> clazz = null;
//        try {
//            clazz = classLoader.loadClass("commonbeans.People");
//            Field[] fields = clazz.getDeclaredFields();
//            for(Field field : fields){
//                System.out.println(field.getName());
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


        try {
            Class<?> clazz = Class.forName("commonbeans.People");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}
