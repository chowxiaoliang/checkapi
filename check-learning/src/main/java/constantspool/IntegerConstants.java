package constantspool;

/**
 * @author zhouliang
 * @since 2019-03-19 15:57
 */
public class IntegerConstants {
    public static void main(String[] args) {
        Integer integer = 20;
        Integer integer1 = 20;
        Integer integer2 = new Integer(20);
        int a = 20;
        Integer integer3 = 200;
        Integer integer4 = 200;
        int b = 200;
        int c = 200;
        System.out.println(integer == integer1);
        System.out.println(integer == integer2);
        System.out.println(integer == a);
        System.out.println(integer3 == integer4);
        System.out.println(b == c);

        Integer integer5 = 0;
        System.out.println(integer3 == integer3 + integer5);
    }
}
