package threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtil {

    private static final int THREAD_CPU_NUM = Runtime.getRuntime().availableProcessors();

    private static final int MAX_TASK_NUM = 100_000_000;

    private static ThreadPoolExecutor commonPoolUtil;

    public static synchronized ThreadPoolExecutor getCommonPoolUtil(){
        if(commonPoolUtil==null){
            commonPoolUtil = ThreadPoolUtil.newCPUPool("common");
        }
        return commonPoolUtil;
    }

    public static ThreadPoolExecutor newCPUPool(String threadName){
        return new ThreadPoolExecutor(THREAD_CPU_NUM * 2,
                THREAD_CPU_NUM * 4,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(MAX_TASK_NUM),
                new DefaultThreadFactory(threadName),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static ThreadPoolExecutor newIOPool(String threadName){
        return new ThreadPoolExecutor(THREAD_CPU_NUM * 4,
                THREAD_CPU_NUM * 8,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(MAX_TASK_NUM),
                new DefaultThreadFactory(threadName),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * The default thread factory
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory(String threadName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + threadName +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()){
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
