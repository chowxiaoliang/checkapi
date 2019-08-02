package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.CommonThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * @author zhouliang
 * @desc execute()方法提交任务异常不会被吃掉, 但是只能知道异常是在哪里抛出来的，并不知道任务的具体提交位置是在哪里
 * 使用submit()方法提交任务异常会被setException()吃掉
 * 如果想抛出异常则需要使用futureTask.get()方法去获取他的执行结果
 * 如果不想要结果则可以对submit()方法进行封装可以得到相应的异常堆栈信息
 * <p>
 * execute()只能用来提交Runnable任务，但是submit()可以用来提交Runnable和Callable任务(有异常会被吃掉，除非获取其结果)
 * @since 2018-06-14 16:35
 **/
public class ThreadPoolException {
    private final static Logger LOGGER = LoggerFactory.getLogger(ThreadPoolException.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CommonThreadPoolExecutor threadPoolExecutor = CommonThreadPoolExecutor.newIOPool("exception-pool");
//        threadPoolExecutor.submit(new InnerCallable());

        Future<?> future = threadPoolExecutor.submit(new InnerRunable());
        System.out.println(future.get());

//        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        Future<String> future = executorService.submit(new InnerCallable());
//        future.get();

//        Future<?> future = executorService.submit(new InnerRunable());
//        future.get();
//
//        executorService.execute(new InnerRunable());
//        executorService.shutdown();

//        threadPoolExecutor.shutdown();

    }

    static class InnerCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            LOGGER.info("starts to execute callable thread!");
            int a = 3;
            int b = 0;
            int c = a / b;
            System.out.println(c);
            return "success";
        }
    }

    static class InnerRunable implements Runnable {

        @Override
        public void run() {
            LOGGER.info("starts to execute runnable thread!");
            int a = 2;
            int b = 0;
//            int c = a/b;
//            System.out.println(c);
        }
    }

}
