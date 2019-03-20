package check;

public class IntegerTest {

    volatile People people = new People();

    static volatile String b = "zhouliang";

    public static void main(String[] args) {
        IntegerTest integerTest = new IntegerTest();
         String b = "zhouliang";
        System.out.println(b == integerTest.b);
    }

}
