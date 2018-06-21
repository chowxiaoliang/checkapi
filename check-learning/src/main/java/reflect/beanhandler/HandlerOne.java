package reflect.beanhandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhouliang
 * @since 2018-06-20 11:30
 **/
public class HandlerOne {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("reflect.beaninfo.Person");

            Field[] fields = clazz.getDeclaredFields();
            Method[] methods = clazz.getDeclaredMethods();
            for(Field field : fields){
                System.out.println("field:"+field);
                field.setAccessible(true);

                System.out.println();
            }
            for(Method method : methods){
                System.out.println("method:"+method.getName());
                System.out.println(method.getModifiers());
            }
            System.out.println(clazz.getAnnotations()[0].annotationType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
