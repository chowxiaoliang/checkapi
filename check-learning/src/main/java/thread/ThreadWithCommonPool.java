package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.CommonThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @desc 终止掉其中的一个无法停止的线程
 * @since 2018-05-11 14:23
 **/
public class ThreadWithCommonPool {
    private final static Logger LOGGER = LoggerFactory.getLogger(ThreadWithCommonPool.class);
    private static class NumberWrapper{
        private volatile int num = 0 ;
    }

    public static void main(String[] args) throws InterruptedException {
        CommonThreadPoolExecutor threadPoolExecutor = CommonThreadPoolExecutor.newCPUPool("common-thread");
        NumberWrapper numberWrapper = new NumberWrapper();
//        for(int i=0;i<10;i++){
//            threadPoolExecutor.submit(()->{
//                synchronized (numberWrapper){
//                    LOGGER.info("the num is => {}", numberWrapper.num);
//                    numberWrapper.num++;
//                }
//            });
//        }
        String result = null;
        threadPoolExecutor.submit(()->{
            int a = 3;
            int b = 0;
            int c = a/b;
            System.out.println(c);
            return "success";
        });

        threadPoolExecutor.shutdown();
        do{
            TimeUnit.SECONDS.sleep(1);
            LOGGER.info("the size of queue is => {}", threadPoolExecutor.getQueue().size());
        }while (threadPoolExecutor.getQueue().size()!=0);

        long taskCount ;
        long activeCount ;
        long completedTaskCount ;
        do{
            TimeUnit.SECONDS.sleep(1);
            taskCount = threadPoolExecutor.getTaskCount();
            activeCount = threadPoolExecutor.getActiveCount();
            completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            LOGGER.info("the task count is => {}", taskCount);
            LOGGER.info("active count is => {}", activeCount);
            LOGGER.info("the completed task count is => {}", completedTaskCount);
        }while (taskCount!=completedTaskCount);

        LOGGER.info("the final task is finished!");

    }
}
