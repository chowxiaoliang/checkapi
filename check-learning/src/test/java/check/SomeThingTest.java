package check;

import java.util.function.Function;

public class SomeThingTest {

    public void test(){

        SomeThing someThing1 = new SomeThing("zhou");

        Function<String,String> function = SomeThing::say;
        String result = function.apply("zhouliang");
        System.out.println(result);

        Function<String, String> function1 = someThing1::printMessage;
        System.out.println(function1.apply("print message"));

        Function<String, SomeThing> function2 = SomeThing::new;
        System.out.println(function2.apply("new TestMain"));

    }

    public static void main(String[] args) {
        SomeThingTest someThingTest = new SomeThingTest();
        someThingTest.test();
    }
}
