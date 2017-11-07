package lambdaFirstTest;

/**
 * Created by lenovo on 2017/10/12.
 */
@FunctionalInterface
public interface Animal<R> {

    public R say(R str);

    default public R test(R str){
        System.out.println(str);
        return str;
    }
}
