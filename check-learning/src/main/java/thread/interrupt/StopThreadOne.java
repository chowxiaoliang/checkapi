package thread.interrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @since 2018-06-13 11:14
 * @desc 通过共享变量的方式结束线程
 * 实例方法thread.isInterrupted()不会清楚中断标志
 * 静态方法Thread.interrupted()会清楚中断标志
 * 实例方法thread.interrupt()会设置中断标志
 * sleep()方法响应中断后会清除中断标志
 **/
public class StopThreadOne {
    private final static Logger LOGGER = LoggerFactory.getLogger(StopThreadOne.class);
    public static void main(String[] args) {
        InnerThread innerThread = new InnerThread();
        Thread thread = new Thread(innerThread);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        if(thread.isInterrupted()){
            LOGGER.info("current thread is interrupted! thread is going to stop");
            innerThread.flag = false;
        }
        LOGGER.info("main thread is stop!");
    }

    static class InnerThread implements Runnable{
        volatile boolean flag = true;
        int num = 0;
        @Override
        public void run() {
            while(flag){
                LOGGER.info("current num is => {}", num);
                num ++ ;
            }
        }
    }
}
