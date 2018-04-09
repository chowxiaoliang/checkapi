package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouliang on 2018/4/8 0008.
 * @desc CyclicBarrier可以让n个线程异步执行后等待，然后在合适时间一起同时异步执行接下来的任务
 * example：三个运动员各自准备，等到三个人都准备好后，再一起跑
 */
public class CyclicBarrierTest {
    private final static Logger logger = LoggerFactory.getLogger(CyclicBarrierTest.class);
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        CyclicBarrierRunnable cyclicBarrierRunnable = new CyclicBarrierRunnable("zhouliang", cyclicBarrier);
        CyclicBarrierRunnable cyclicBarrierRunnable1 = new CyclicBarrierRunnable("wangwenqi", cyclicBarrier);
        CyclicBarrierRunnable cyclicBarrierRunnable2 = new CyclicBarrierRunnable("yangxiaoxiao", cyclicBarrier);
        Thread thread = new Thread(cyclicBarrierRunnable);
        Thread thread1 = new Thread(cyclicBarrierRunnable1);
        Thread thread2 = new Thread(cyclicBarrierRunnable2);
        thread.start();
        thread1.start();
        thread2.start();
    }
    static class CyclicBarrierRunnable implements Runnable{
        private String name;
        private CyclicBarrier cyclicBarrier;
        public CyclicBarrierRunnable(String name, CyclicBarrier cyclicBarrier){
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            for(int i=0;i<5;i++){
                logger.info("{} 执行第 {} 次", name, i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            logger.info("{} end for 前5次执行", name);
        }
    }
}
