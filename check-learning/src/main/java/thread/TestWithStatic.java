package thread;

/**
 * @author zhouliang
 * @since 2018-05-16 11:25
 **/
public class TestWithStatic {

    private static int NUM = 0;

    public static void main(String[] args) {
        TestWithStatic.NUM = 1;
        System.out.println(TestWithStatic.NUM);

        new Thread(()->{
            System.out.println(TestWithStatic.NUM);
        }).start();

        System.out.println(TestWithStatic.NUM);
    }
}
