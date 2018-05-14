package synchronizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @since 2018-05-14 17:26
 **/
public class MutexTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(MutexTest.class);

    private volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {

        MutexTest mutexTest = new MutexTest();

        MutexCopy mutexCopy = new MutexCopy();

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getCommonPoolUtil();

        for(int i=0;i<200000;i++){
            threadPoolExecutor.execute(()->{
                try{
                    mutexCopy.lock();
                    LOGGER.info("current num is => {}", mutexTest.num);
                    mutexTest.num++;
                }finally {
                    mutexCopy.unlock();
                }
            });
        }

        threadPoolExecutor.shutdown();

        do{
            LOGGER.info("current pool queue size is => {}", threadPoolExecutor.getQueue().size());
            TimeUnit.MILLISECONDS.sleep(5);
        }while (threadPoolExecutor.getQueue().size()>0);

        long taskCount ;
        long completedTaskCount ;
        do{
            taskCount = threadPoolExecutor.getTaskCount();
            completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            TimeUnit.MILLISECONDS.sleep(5);
        }while (taskCount!=completedTaskCount);

        LOGGER.info("final result is => {}", mutexTest.num);
        LOGGER.info("task is finished!");
    }
}
