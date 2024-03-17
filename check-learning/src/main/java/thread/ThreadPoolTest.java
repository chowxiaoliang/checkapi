package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lenovo
 */
public class ThreadPoolTest {
    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);
    private static class NumberWraper{
        private volatile int value = 0;
    }
    public static void main(String[] args) throws InterruptedException{
        long startTime = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.newIOPool("应用层.txt-pool");
        NumberWraper numberWraper = new NumberWraper();
        for(int i=0;i<20000;i++){
            threadPoolExecutor.execute(()->{
                synchronized (numberWraper){
                    numberWraper.value++;
                }
                logger.info("thread {} executing 第{}次", Thread.currentThread().getName(), numberWraper.value);
            });
        }
        //关闭接收任务，但队列里面的任务会继续处理
        threadPoolExecutor.shutdown();
        do{
            logger.info("waiting thread finished, the size of queue is => {}", threadPoolExecutor.getQueue().size());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (threadPoolExecutor.getQueue().size()>0);

        //等待线程池里面的任务全部处理完成
        long taskCount ;
        long taskCompletedCount;

        do{
            taskCount = threadPoolExecutor.getTaskCount();
            taskCompletedCount = threadPoolExecutor.getCompletedTaskCount();
            TimeUnit.MILLISECONDS.sleep(5);
        }while (taskCount!=taskCompletedCount);

        logger.info("all of threads is finished");
        long endTime = System.currentTimeMillis();
        logger.info("total time is => {}ms", endTime-startTime);
    }
}
