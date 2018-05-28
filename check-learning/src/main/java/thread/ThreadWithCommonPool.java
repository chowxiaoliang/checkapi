package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.CommonThreadPoolExecutor;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
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
        ThreadPoolExecutor threadPoolExecutor = CommonThreadPoolExecutor.newCPUPool("common-thread");
        NumberWrapper numberWrapper = new NumberWrapper();
        Future future = null;
        for(int i=0;i<10;i++){
            future = threadPoolExecutor.submit(()->{
                synchronized (numberWrapper){
                    LOGGER.info("the num is => {}", numberWrapper.num);
                    numberWrapper.num++;
                }
            });
        }

        threadPoolExecutor.execute(()->{
            int a = 9;
            int b = 0;
            int c = a/b;
            System.out.println(c);
        });

        for(int i=0;i<1;i++){
            future = threadPoolExecutor.submit(()->{
                while(true){
                    TimeUnit.SECONDS.sleep(5);
                    LOGGER.info("taskOne is executing!");
                }
            });
        }

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
            if(taskCount == completedTaskCount + 1){
                long innerStart = System.currentTimeMillis();
                long innerEnd ;
                do{
                    //等待一分钟未执行完成则终止线程
                    TimeUnit.SECONDS.sleep(1);
                    taskCount = threadPoolExecutor.getTaskCount();
                    completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
                    innerEnd = System.currentTimeMillis();
                }while (taskCount!=completedTaskCount && (innerEnd-innerStart)/(1000*60)<1);
                //future.cancel()取消线程的执行可能会失败
//                future.cancel(true);
                threadPoolExecutor.shutdownNow();
                LOGGER.info("current thread name is => {}", Thread.currentThread().getName());
                LOGGER.info("interrupted！");
            }
        }while (taskCount!=completedTaskCount);

        LOGGER.info("the final task is finished!");

    }
}
