package check;

public class NullTest {
    public static void main(String[] args) {
        People people = (People)null;
        System.out.println(people);

        Object object = new Zhouliang();
        Zhouliang people1 = (Zhouliang)object;
        System.out.println(people1);
    }
}
