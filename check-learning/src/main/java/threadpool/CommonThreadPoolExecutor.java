package threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouliang
 * @since 2018-05-11 11:40
 **/
public class CommonThreadPoolExecutor extends ThreadPoolExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonThreadPoolExecutor.class);

    private static final int THREAD_CPU_NUM = Runtime.getRuntime().availableProcessors();

    private static final int MAX_TASK_NUM = 100_000_000;

    private static CommonThreadPoolExecutor commonPoolUtil;

    public static synchronized CommonThreadPoolExecutor getCommonPoolUtil(){
        if(commonPoolUtil==null){
            commonPoolUtil = newCPUPool("common");
        }
        return commonPoolUtil;
    }

    private CommonThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public static CommonThreadPoolExecutor newCPUPool(String threadName){
        return new CommonThreadPoolExecutor(THREAD_CPU_NUM * 2,
                THREAD_CPU_NUM * 4,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(MAX_TASK_NUM),
                new ThreadPoolUtil.DefaultThreadFactory(threadName),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static CommonThreadPoolExecutor newIOPool(String threadName){
        return new CommonThreadPoolExecutor(THREAD_CPU_NUM * 4,
                THREAD_CPU_NUM * 8,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(MAX_TASK_NUM),
                new ThreadPoolUtil.DefaultThreadFactory(threadName),
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
    
    private Exception clientStack(){
        return new Exception("Client stack trace!");
    }
    
    private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName){
        return () -> {
            try{
                LOGGER.info("thread {} start to run!", clientThreadName);
                task.run();
            }catch (Exception e){
                LOGGER.error("error occur in thread {}", clientThreadName);
                clientStack.printStackTrace();
                throw e;
            }
        };
    }

    @Override
    public void execute(Runnable task){
        super.execute(wrap(task, clientStack(), Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientStack(), Thread.currentThread().getName()));
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r){

    }

    @Override
    protected void afterExecute(Runnable r, Throwable t){

    }

    @Override
    protected void terminated(){

    }
}
