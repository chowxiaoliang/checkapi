package thread.waitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadOne{
    private final static Logger logger = LoggerFactory.getLogger(ThreadOne.class);
    public static void main(String[] args){
        Object object = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (object){
                logger.info("step into one");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("step into two");
                logger.info("step into three");
            }
        });

        Thread thread2 = new Thread(()->{
           synchronized (object){
               logger.info("step into four");
               object.notify();
           }
        });
        thread1.start();
        thread2.start();
    }
}
