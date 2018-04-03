package thread.threadwithreentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(ReentrantTest.class);
    private ReentrantLock reentrantLock;
    private String certNo;
    private String name;
    private String mobile;
    public ReentrantTest(String certNo, String name, String mobile, ReentrantLock reentrantLock){
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
