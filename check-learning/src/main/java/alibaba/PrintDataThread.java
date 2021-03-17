package alibaba;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印1-100的整数，一个打印奇数，一个打印偶数，要求输出结果有序
 */
public class PrintDataThread {

    private volatile static int NUM = 0;

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private static final Object object = new Object();

    public static void main(String[] args) {
        OddNumThread oddNumThread = new OddNumThread();
        EvenNumThread evenNumThread = new EvenNumThread();
        Thread thread = new Thread(oddNumThread);
        Thread thread1 = new Thread(evenNumThread);
        thread.start();
        thread1.start();
    }

    static class OddNumThread implements Runnable{

        @Override
        public void run() {
            while (atomicInteger.get() <100){
                synchronized (object){
                    if (atomicInteger.get() %2 > 0){
                        System.out.println("打印奇数="+atomicInteger.get());
                        atomicInteger.getAndIncrement();
                    }
                    object.notify();
                }
            }
        }
    }

    static class EvenNumThread implements Runnable{

        @Override
        public void run() {
            while (atomicInteger.get() <= 100){
                synchronized (object){
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (atomicInteger.get() %2 == 0){
                        System.out.println("打印偶数="+atomicInteger.get());
                        atomicInteger.getAndIncrement();
                    }
                }
            }
        }
    }
}
