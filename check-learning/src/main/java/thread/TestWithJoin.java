package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @desc join()方法使当前线程停下来等待，直至另一个调用join方法的线程终止
 */
public class TestWithJoin {
    private final static Logger logger = LoggerFactory.getLogger(TestWithJoin.class);
    public static void main(String[] args){
        Yield yield = new Yield("json");
        Thread thread = new Thread(yield);
        thread.start();
        try {
            //开辟新的线程需要时间
            thread.join();
            logger.info("new thread execute complete");
            //休眠5s
            for(int i=0;i<5;i++){
                logger.info("execute 第 {} 次操作", i);
                TimeUnit.SECONDS.sleep(1);
            }
            logger.info("end main execute");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class Yield implements Runnable{
        public String name;
        public Yield(String name){
            this.name = name;
        }
        @Override
        public void run() {
            logger.info("execute new thread");
            for (int i=0;i<10;i++){
                //1s执行一次
                logger.info("execute 第 {} 次操作", i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info("end execute");
        }
    }
}
