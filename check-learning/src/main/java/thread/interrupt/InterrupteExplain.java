package thread.interrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @since 2018-05-31 11:09
 **/
public class InterrupteExplain {

    private final static Logger LOGGER = LoggerFactory.getLogger(InterrupteExplain.class);

    public static void main(String[] args) {
        InnerThread innerThread = new InnerThread();
        FutureTask<String> futureTask = new FutureTask<>(innerThread);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futureTask.cancel(true);
        if(futureTask.isCancelled()){
            LOGGER.info("current is canceled! current num is => {}", innerThread.num);
        }
        LOGGER.info("main thread is going to finish!");
    }

    static class InnerThread implements Callable<String>{
        volatile int num = 0;

        @Override
        public String call() throws Exception {
            while(true){
                LOGGER.info("current num is => {}", num);
                num ++ ;
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
