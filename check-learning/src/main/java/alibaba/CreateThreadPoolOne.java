package alibaba;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 创建一个线程池，提供三种以上方法确保单个jvm在启动过程中只初始化一个线程池(单例模式)
 */
public class CreateThreadPoolOne {

    private CreateThreadPoolOne(){}

    private static ExecutorService executorService = null;

    public ExecutorService getExecutorService(){
        if (executorService == null){
            synchronized (this){
                if (executorService == null){
                    executorService = Executors.newFixedThreadPool(10);
                }
            }
        }
        return executorService;
    }

}
