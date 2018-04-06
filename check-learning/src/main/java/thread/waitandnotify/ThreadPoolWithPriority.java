package thread.waitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouliang on 2018/4/5 0005.
 * @desc 设置线程的优先级
 * 线程的优先级于代码执行顺序无关
 * 线程的优先级仍然无法保障线程的执行次序。只不过，优先级高的线程获取CPU资源的概率较大，优先级低的并非没机会执行。
 */
public class ThreadPoolWithPriority {
    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolWithPriority.class);
    public static void main(String[] args){

        ThreadTest threadTest1 = new ThreadTest("json", 5);
        ThreadTest threadTest2 = new ThreadTest("wangwenqi", 10);
        Thread thread1 = new Thread(threadTest1);
        Thread thread2 = new Thread(threadTest2);
        thread1.setPriority(1);
        thread2.setPriority(10);
        thread1.start();
        thread2.start();


    }
    static class ThreadTest implements Runnable{
        private String name;
        private int time;
        public ThreadTest(String name, int time){
            this.name = name;
            this.time = time;
        }
        @Override
        public void run() {
            for(int i=0;i<time;i++){
                logger.info("{} 执行第 {} 次", name, i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
