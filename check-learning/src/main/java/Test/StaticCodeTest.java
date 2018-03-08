package Test;

public class StaticCodeTest {
    static{
        System.out.println("this is static code area");
    }
    public StaticCodeTest(){
        System.out.println("this is contructor area");
    }
    public static void main(String[] args){
        StaticCodeTest staticCodeTest = new StaticCodeTest();
    }
}
