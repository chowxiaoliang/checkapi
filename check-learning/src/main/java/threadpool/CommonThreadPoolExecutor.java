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
    
    private Runnable wrapRunnable(final Runnable task, final Exception clientStack){
        return () -> {
            try{
                LOGGER.info("thread starts to run!");
                task.run();
            }catch (Exception e){
                LOGGER.error("error occurs in thread!");
                clientStack.printStackTrace();
                throw e;
            }
        };
    }

    private  <T> Callable<T> wrapCallable(final Callable<T> task, final Exception clientStack){
        return () -> {
            try{
                LOGGER.info("thread starts to run!");
                return task.call();
            }catch (Exception e){
                LOGGER.error("error occurs in thread!");
                clientStack.printStackTrace();
                throw e;
            }
        };
    }

    @Override
    public void execute(Runnable task){
        super.execute(wrapRunnable(task, clientStack()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrapRunnable(task, clientStack()));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return super.submit(wrapRunnable(task, clientStack()), result);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task){
        return super.submit(wrapCallable(task, clientStack()));
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
