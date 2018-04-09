package thread.threadwithreentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest {
    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock();
        InnerReentrant innerReentrant = new InnerReentrant("123", "json", "13227136694", lock);
        InnerReentrant innerReentrant1 = new InnerReentrant("123", "wangwenqi", "13227136694", lock);
        Thread thread = new Thread(innerReentrant);
        Thread thread1 = new Thread(innerReentrant1);
        thread.start();
        thread1.start();
    }

    static class InnerReentrant implements Runnable {
        private final Logger logger = LoggerFactory.getLogger(ReentrantTest.class);
        private ReentrantLock reentrantLock;
        private String certNo;
        private String name;
        private String mobile;
        public InnerReentrant(String certNo, String name, String mobile, ReentrantLock reentrantLock){
            this.certNo = certNo;
            this.name = name;
            this.mobile = mobile;
            this.reentrantLock = reentrantLock;
        }

        @Override
        public void run() {
            reentrantLock.lock();
            for(int i=0;i<5;i++){
                logger.info("{} execute第 {} 次", name, i);
            }
            reentrantLock.unlock();
        }
    }
}
