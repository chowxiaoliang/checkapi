package check;

public class StringTest {
    public static void main(String[] args) {
        String a = "zhouliang";
        String b = new String("zhouliang");
        String c = new String("zhouliang");
        String d = new String("zhouliang");
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
    }
}
