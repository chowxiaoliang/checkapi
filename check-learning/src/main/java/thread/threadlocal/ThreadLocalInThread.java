package thread.threadlocal;

import threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalInThread {

    public static void main(String[] args) throws InterruptedException {

        for(int i=0; i< 10; i ++){
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                threadLocal.set("[current thread num is "+ finalI + "]");
                System.out.println(threadLocal.get());
                threadLocal.remove();
            });

            TimeUnit.SECONDS.sleep(1);
        }

        threadPoolExecutor.shutdown();

    }

    private static ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getCommonPoolUtil();

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
}
