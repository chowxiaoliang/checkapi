package check;

public class ClassTest {

    private <T> String sayMessage(T message){
        System.out.println(message);
        return "success";
    }

    public static void main(String[] args) {
        ClassTest classTest = new ClassTest();
        classTest.sayMessage(23);
    }
}
