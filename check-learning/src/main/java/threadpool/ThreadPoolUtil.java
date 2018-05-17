package threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouliang
 * @desc 创建两种不同类型的线程池（https://www.cnblogs.com/kuoAT/p/6714762.html）
 * 参数解释：
 * 每个变量都有其各自的作用，这里要重点解释一下corePoolSize、maximumPoolSize、largestPoolSize三个变量。
　　corePoolSize在很多地方被翻译成核心池大小，其实我的理解这个就是线程池的大小。举个简单的例子：
　　假如有一个工厂，工厂里面有10个工人，每个工人同时只能做一件任务。
　　因此只要当10个工人中有工人是空闲的，来了任务就分配给空闲的工人做；
　　当10个工人都有任务在做时，如果还来了任务，就把任务进行排队等待；
　　如果说新任务数目增长的速度远远大于工人做任务的速度，那么此时工厂主管可能会想补救措施，比如重新招4个临时工人进来；
　　然后就将任务也分配给这4个临时工人做；
　　如果说着14个工人做任务的速度还是不够，此时工厂主管可能就要考虑不再接收新的任务或者抛弃前面的一些任务了。
　　当这14个工人当中有人空闲时，而新任务增长的速度又比较缓慢，工厂主管可能就考虑辞掉4个临时工了，只保持原来的10个工人，毕竟请额外的工人是要花钱的。
　　这个例子中的corePoolSize就是10，而maximumPoolSize就是14（10+4）。
　　也就是说corePoolSize就是线程池大小，maximumPoolSize在我看来是线程池的一种补救措施，即任务量突然过大时的一种补救措施。
　　不过为了方便理解，在本文后面还是将corePoolSize翻译成核心池大小。
　　largestPoolSize只是一个用来起记录作用的变量，用来记录线程池中曾经有过的最大线程数目，跟线程池的容量没有任何关系。

    任务提交给线程池之后的处理策略，这里总结一下主要有4点：
    如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
    如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
    如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
    如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。

    参数allowCoreThreadTimeOut（返回值为布尔）如果设置为true后，当核心线程池里面的线程没有任务提交时，当时间超过keepAliveTime时，里面的线程也会终止

    阻塞队列选用
    建议使用有界队列，有界队列能增加系统的稳定性和预警能力，可以根据需要设大一点，比如几千。有一次我们组使用的后台任务线程池的队列和线程池全满了，
    不断的抛出抛弃任务的异常，通过排查发现是数据库出现了问题，导致执行SQL变得非常缓慢，因为后台任务线程池里的任务全是需要向数据库查询和插入数据的，
    所以导致线程池里的工作线程全部阻塞住，任务积压在线程池里。如果当时我们设置成无界队列，线程池的队列就会越来越多，有可能会撑满内存，导致整个系统不可用，
    而不只是后台任务出现问题。当然我们的系统所有的任务是用的单独的服务器部署的，而我们使用不同规模的线程池跑不同类型的任务，但是出现这样问题时也会影响到其他任务。


    ThreadPoolExecutor线程池执行流程

    根据ThreadPoolExecutor源码前面大段的注释，我们可以看出，当试图通过execute方法将一个Runnable任务添加到线程池中时，按照如下顺序来处理：

    （1）如果线程池中的线程数量少于corePoolSize，就创建新的线程来执行新添加的任务；

    （2）如果线程池中的线程数量大于等于corePoolSize，但队列workQueue未满，则将新添加的任务放到workQueue中，按照FIFO的原则依次等待执行（线程池中有线程空闲出来后依次将队列中的任务交付给空闲的线程执行）；

    （3）如果线程池中的线程数量大于等于corePoolSize，且队列workQueue已满，但线程池中的线程数量小于maximumPoolSize，则会创建新的线程来处理被添加的任务；

    （4）如果线程池中的线程数量等于了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理

    总结，当有新的任务要处理时，先看线程池中的线程数量是否大于corePoolSize，再看缓冲队列workQueue是否满，最后看线程池中的线程数量是否大于maximumPoolSize

    另外，当线程池中的线程数量大于corePoolSize时，如果里面有线程的空闲时间超过了keepAliveTime，就将其移除线程池

 */
public class ThreadPoolUtil {

    /**
     * 计算密集型  最佳线程数 cpu数量*2 （一般使用此种）
     * IO密集型 最佳线程数 cpu数量*(5~10) IO阻塞会浪费cpu
     */
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
                new ArrayBlockingQueue<>(MAX_TASK_NUM),
                new DefaultThreadFactory(threadName),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static ThreadPoolExecutor newIOPool(String threadName){
        return new ThreadPoolExecutor(THREAD_CPU_NUM * 4,
                THREAD_CPU_NUM * 8,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(MAX_TASK_NUM),
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
