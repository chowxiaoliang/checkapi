package lambdaFirstTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2017/10/12.
 */
public class DogLambda {

    public static void main(String[] args){
        List<String> strList = Arrays.asList("yangxiaoxiao", "wuke", "liyuting", "luotingting");
        strList.forEach(System.out::println);

        Animal animal = (message) -> {System.out.print("test"+message);return message;};
        animal.say("zhouliang");
        animal.test("yangxiaoxiao");

    }
}
