package thread.threadlocal;

public class TwoThreadLocal {

    public static void main(String[] args) {

        // ThreadLocalMap存储当前threadLocal对象的弱引用，value即为set的值
        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

        threadLocal1.set("zhou");
        threadLocal2.set("liang");

        String result1 = threadLocal1.get();
        String result2 = threadLocal2.get();
        System.out.println(result1);
        System.out.println(result2);

    }
}
