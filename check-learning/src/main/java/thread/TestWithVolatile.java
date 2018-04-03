package thread;

import threadpool.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TestWithVolatile {
    private volatile int time = 0;
    public void test() throws InterruptedException {
        ExecutorService executorService = ThreadPoolUtil.getCommonPoolUtil();
        for(int i=0;i<10;i++){
            executorService.execute(()->{
                time++;
                System.out.println("the value of time is => "+time);
            });
            TimeUnit.SECONDS.sleep(2);
        }
        executorService.shutdown();
        if (executorService != null) {

        }
    }
    public static void main(String[] args) throws InterruptedException {
        new TestWithVolatile().test();

    }
}
