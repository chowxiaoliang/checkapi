package check;

public class ValueTest {
    public static void main(String[] args) {
        int a = 12;
        int b = 12;
        Integer integer = new Integer(12);
        Integer integer1 = new Integer(12);
        System.out.println(integer==integer1);

    }

    private void value(String name){

    }

    private void address(String name){
        name = "liang";
    }
}
