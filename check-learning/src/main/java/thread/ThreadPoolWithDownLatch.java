package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouliang
 */
public class ThreadPoolWithDownLatch {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolWithDownLatch.class);

    public static void main(String[] args){
        CountDownLatch countDownLatch = new CountDownLatch(2);

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.newCPUPool("countTest");

        Future<String> future1 = threadPoolExecutor.submit(() ->
        {
            String str = sayMessage("zhouliang");
            countDownLatch.countDown();
            return str;
        });
        Future<String> future2 = threadPoolExecutor.submit(() ->
        {
            String str = sayMessage("yangxiaoxiao");
            countDownLatch.countDown();
            return str;
        });
        try{
            logger.debug("the size of pool is=>{}",threadPoolExecutor.getPoolSize());
            countDownLatch.await();
            //最后关闭线程池，但执行之前提交的任务，不接受新任务
            threadPoolExecutor.shutdown();
            logger.debug("main thread is finished!");
            String fstr1 = future1.get();
            String fstr2 = future2.get();
            logger.debug("the result of future1 is=>"+ fstr1);
            logger.debug("the result of future2 is=>"+ fstr2);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private static String sayMessage(String message){
        logger.debug("this message is saying...=>"+ message);
        return message;
    }
}
