package thread.waitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

/**
 * @author lenovo
 * @desc 线程交替执行synchronized结合wait和notify
 */
public class WaitAndNotify {
    private final static Logger logger = LoggerFactory.getLogger(WaitAndNotify.class);
    public static void main(String[] args){
        Object object = new Object();
        ExecutorService executorService = ThreadPoolUtil.getCommonPoolUtil();
        executorService.execute(()->{
            synchronized (object){
                logger.info("print step one");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("print step two");
                logger.info("print step three");
            }
        });

        executorService.execute(()->{
            synchronized (object){
                logger.info("print step four");
                object.notify();
            }
        });
    }

}
