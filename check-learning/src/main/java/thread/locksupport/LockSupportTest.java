package thread.locksupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhouliang
 * @since 2018-05-21 17:39
 * @desc park()挂起 unpark()恢复
 * 与wait和notify的比较：
 * 其实现机制和wait/notify有所不同，面向的是线程。wait/notify/nofityAll()必须在synchronize代码块里面使用
    不需要依赖监视器
    与wait/notify没有交集
    使用起来更加灵活方便
    unpark()可以先于park()使用
 **/
public class LockSupportTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(LockSupportTest.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("start to execute");
        InnerThread innerThread = new InnerThread("zhouliang", 5);
        Thread thread = new Thread(innerThread);
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        LOGGER.info("start to execute main thread!");
        LockSupport.unpark(thread);
        LOGGER.info("start to execute the next main thread!");
    }

    private static class InnerThread implements Runnable{
        private String name;

        private int num;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public InnerThread(String name, int num){
            this.name = name;
            this.num = num;
        }


        @Override
        public void run() {
            LOGGER.info("thread => {} starts to execute!", name);
            synchronized (this){
                LOGGER.info("start num is => {}", num);
                LockSupport.park();
                num++;
                LOGGER.info("next to start num is => {}", num);
            }
        }
    }
}
