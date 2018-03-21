package Test;

public class StaticCodeTest {
    static{
        System.out.println("this is static code area");
    }
    public StaticCodeTest(){
        System.out.println("this is contructor area");
    }
    public static void sayMessage(){
        System.out.println("this is say message");
    }
    public static void main(String[] args){
        StaticCodeTest.sayMessage();
    }
}
