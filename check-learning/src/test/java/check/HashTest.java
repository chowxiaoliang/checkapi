package check;

public class HashTest {
    public static void main(String[] args) {
        People people = new People();
        people.setName("zhou");

        People people1 = new People();
        people1.setName("zhou");
        System.out.println(people.hashCode());
        System.out.println(people1.hashCode());
    }
}
