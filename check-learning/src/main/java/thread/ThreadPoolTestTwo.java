package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouliang
 * @since 2018-05-14 10:49
 **/
public class ThreadPoolTestTwo {

    private final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    private final static Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTestTwo.class);

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getCommonPoolUtil();
        for(int i=0;i<20000;i++){
            threadPoolExecutor.execute(()->{
                int result = ATOMIC_INTEGER.incrementAndGet();
                LOGGER.info("current value is => {}", result);
            });
        }

        threadPoolExecutor.shutdown();
        do{
            LOGGER.info("current queue size is => {}", threadPoolExecutor.getQueue().size());
            TimeUnit.MILLISECONDS.sleep(5);
        }while (threadPoolExecutor.getQueue().size()>0);

        long taskCount ;
        long completedTaskCount ;
        do{
            taskCount = threadPoolExecutor.getTaskCount();
            completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            TimeUnit.MILLISECONDS.sleep(5);
        }while (taskCount!=completedTaskCount);

        LOGGER.info("task is finished!");

        int finalResult = ATOMIC_INTEGER.get();
        LOGGER.info("final result is => {}", finalResult);
        long endTime = System.currentTimeMillis();
        LOGGER.info("total time is => {}ms", endTime-startTime);
    }
}
