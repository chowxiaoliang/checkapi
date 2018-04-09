package thread.threadwithreentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc
 * Condition与Object中的wati,notify,notifyAll区别：
1.Condition中的await()方法相当于Object的wait()方法，Condition中的signal()方法相当于Object的notify()方法，Condition中的signalAll()相当于Object的notifyAll()方法。
不同的是，Object中的这些方法是和同步锁捆绑使用的；而Condition是需要与互斥锁/共享锁捆绑使用的。
2.Condition它更强大的地方在于：能够更加精细的控制多线程的休眠与唤醒。对于同一个锁，我们可以创建多个Condition，在不同的情况下使用不同的Condition。
例如，假如多线程读/写同一个缓冲区：当向缓冲区中写入数据之后，唤醒"读线程"；当从缓冲区读出数据之后，唤醒"写线程"；并且当缓冲区满的时候，"写线程"需要等待；当缓冲区为空时，"读线程"需要等待。
如果采用Object类中的wait(),notify(),notifyAll()实现该缓冲区，当向缓冲区写入数据之后需要唤醒"读线程"时，不可能通过notify()或notifyAll()明确的指定唤醒"读线程"，而只能通过notifyAll唤醒所有线程(但是notifyAll无法区分唤醒的线程是读线程，还是写线程)。 但是，通过Condition，就能明确的指定唤醒读线程。
 @author zhouliang
 */
public class ConditionTest {
    private final static Logger logger = LoggerFactory.getLogger(ConditionTest.class);
    static class NumberWraper{
        public int value = 1;
    }
    public static void main(String[] args){
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition reachThree = reentrantLock.newCondition();
        Condition reachSix = reentrantLock.newCondition();

        final NumberWraper numberWraper = new NumberWraper();
        new Thread(()->{
            while (numberWraper.value<4){
                try {
                    logger.info("thread a is waiting...value=>{}", numberWraper.value);
                    numberWraper.value ++ ;
                    reachThree.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })
    }
}
