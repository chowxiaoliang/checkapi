package commonbeans;

/**
 * @author zhouliang
 * @since 2018-06-12 14:45
 **/
public class ObjectTest {
    public static void main(String[] args) {
        Object object = new Object();
        People people = new People();
        People people1 = new People();
        People people2 = people;

        System.out.println(people2==people);
        System.out.println(people.equals(people2));
    }
}
