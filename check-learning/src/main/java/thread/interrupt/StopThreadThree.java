package thread.interrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @since 2018-06-13 17:18
 **/
public class StopThreadThree {
    private final static Logger LOGGER = LoggerFactory.getLogger(StopThreadThree.class);
    public static void main(String[] args) {
        InnerThread innerThread = new InnerThread();
        Thread thread = new Thread(innerThread);
        thread.start();
        LOGGER.info("wait for a moment！");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        if(thread.isInterrupted()){
            LOGGER.info("thread is interrupted! current num is => {}", innerThread.num);
        }
        LOGGER.info("main thread is going to finish!");
    }
    static class InnerThread implements Runnable{
        volatile int num = 0;
        @Override
        public void run() {
            try{
                while(true){
                    LOGGER.info("current num is => {}", num);
                    try {
                        //sleep会响应中断
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new InterruptedException("sleep is interrupted!");
                    }
                }
            }catch (Exception e){
                LOGGER.error("current thread is interrupted!");
                //sleep响应中断后会置中断标志位为false
                //重新中断(最合理的处理方式)
                Thread.currentThread().interrupt();
            }
        }
    }
}
