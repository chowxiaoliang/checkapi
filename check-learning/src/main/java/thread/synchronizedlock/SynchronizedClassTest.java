package thread.synchronizedlock;

import java.util.concurrent.TimeUnit;

public class SynchronizedClassTest {
    static String name = "zhouliang";
    volatile static int num = 0;

    public static void main(String[] args) {
        synchronized (String.class) {
            Mythread mythread1 = new Mythread();
            Mythread mythread2 = new Mythread();
            Thread thread = new Thread(mythread1);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread thread1 = new Thread(mythread2);
            thread1.start();
        }
    }

    public synchronized void test() {
        System.out.println("say something!");
    }

    public synchronized static void testTwo() {
        System.out.println("say something!");
    }

    static class Mythread implements Runnable {
        private final String innerStr = "";

        @Override
        public synchronized void run() {
            synchronized (innerStr) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("current thread is => " + Thread.currentThread().getName() + "," + "current num is => " + num);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num++;
                }
            }
        }
    }
}
