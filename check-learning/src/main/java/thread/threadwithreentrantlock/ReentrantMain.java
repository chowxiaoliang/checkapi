package thread.threadwithreentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantMain {
    public static void main(String[] args){
        ReentrantLock reentrantLock = new ReentrantLock();
        ReentrantTest reentrantTest = new ReentrantTest("123", "json", "15872151893", reentrantLock);
        ReentrantTest reentrantTest1 = new ReentrantTest("321", "wangwenqi", "13227136694", reentrantLock);
        reentrantTest.run();
        reentrantTest1.run();
    }
}
