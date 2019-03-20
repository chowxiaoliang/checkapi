package check;

public class SomeThing {

    private int age;

    private String name;

    public SomeThing(int age, String name){
        this.age = age;
        this.name = name;
    }

    public SomeThing(String name){
        this.name = name;
        System.out.println("constructor is printed!");
    }

    public static String say(String message){
        System.out.println("this is TestMain message!" + message);
        return "success";
    }

    public String printMessage(String message){
        System.out.println("print message is => " + message);
        return message;
    }
}
