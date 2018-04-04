package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @desc
 *  Thread.yield()方法作用是：暂停当前正在执行的线程对象，并执行其他线程。
    yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
    因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。
    但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
    结论：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。
 */
public class TestWithYield {
    private final static Logger logger = LoggerFactory.getLogger(TestWithYield.class);
    public static void main(String[] args){
        ThreadOne threadOne = new ThreadOne("json", 10);
        ThreadOne threadOne1 = new ThreadOne("wangwenqi", 5);
        Thread thread = new Thread(threadOne);
        Thread thread1 = new Thread(threadOne1);
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
            logger.info("after 3s start to execute main");
            TimeUnit.SECONDS.sleep(3);
            logger.info("end main");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class ThreadOne implements Runnable{
        private String name;
        private int time;
        public ThreadOne(String name, int time){
            this.name = name;
            this.time = time;
        }
        @Override
        public void run() {
            logger.info("{} starts execute", name);
            for(int i=0;i<time;i++){
                logger.info("{} is execute 第{}次", name, i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Thread.yield();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info("{} ends execute", name);
        }
    }

}
