package constantspool;

/**
 * @author zhouliang
 * @since 2019-03-19 16:06
 */
public class StringConstantsTwo {
    public static final String a = "zhou";
    public static final String b = "liang";

    public static void main(String[] args) {
        String c = a + b;
        String d = "zhouliang";
        System.out.println(c == d);
    }
}
