package thread;

/**
 * @author zhouliang
 * @desc
 * static 只是声明变量在主存上的唯一性，不能保证工作区与主存区变量值的一致性；
 * 除非变量的值是不可变的，即再加上final的修饰符，否则static声明的变量，不是线程安全的。
 * 下面摘自Java语言规范(Java Language Specification)的官方解释：
    1) If a field is declared static, there exists exactly one incarnation of the field,
        no matter how many instances (possibly zero) of the class may eventually be created.
    2) A field may be declared volatile, in which case the Java Memory Model ensures that all threads see a consistent value for the variable。
 * @since 2018-05-16 11:25
 **/
public class TestWithStatic {

    private static int NUM = 0;

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();


        TestWithStatic.NUM ++;
        System.out.println(TestWithStatic.NUM);

        new Thread(()->{
            TestWithStatic.NUM ++;
            System.out.println(TestWithStatic.NUM);
        }).start();

        System.out.println(TestWithStatic.NUM);
    }

    private static class InnerClass{

        private String name;

        private static volatile int num = 0;

    }
}
