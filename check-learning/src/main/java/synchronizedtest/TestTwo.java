package synchronizedtest;

import java.util.concurrent.locks.ReentrantLock;

public class TestTwo {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        ThreadTestTwo threadTestTwo = new ThreadTestTwo("zhouliang",reentrantLock);
        ThreadTestTwo threadTestTwo1 = new ThreadTestTwo("yangxiaoxiao",reentrantLock);
        Thread thread = new Thread(threadTestTwo);
        Thread thread1 = new Thread(threadTestTwo1);
        thread.start();
        thread1.start();
    }
}
