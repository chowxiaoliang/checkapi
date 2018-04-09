package thread.threadwithreentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
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
            //需要先获得锁(先输出1,2,3)
            reentrantLock.lock();
            try {
                while (numberWraper.value<4){
                    //A线程先输出前三个
                    logger.info("thread A is increasing...value=>{}", numberWraper.value);
                    numberWraper.value ++ ;
                    TimeUnit.SECONDS.sleep(1);
                }
                //要输出4,5,6时通知B线程可以开始了
                reachThree.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
            //再次获得锁(7,8,9)
            reentrantLock.lock();
            try{
                //等待输出4,5,6
                reachSix.await();
                logger.info("thread A is going to write value=>{}", numberWraper.value);
                //收到通知输出7,8,9
                while(numberWraper.value<10){
                    logger.info("thread A is increasing...value=>{}", numberWraper.value);
                    numberWraper.value++;
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }

        }).start();
        new Thread(()->{
            reentrantLock.lock();
            try{
                while(numberWraper.value<4){
                    logger.info("thread B is waiting...value=>{}" ,numberWraper.value);
                    TimeUnit.SECONDS.sleep(1);
                    reachThree.await();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
            //收到输出4,5,6的信号
            reentrantLock.lock();
            try {
                logger.info("thread B is going to write");
                while (numberWraper.value<7){
                    logger.info("thread B is increasing...value=>{}", numberWraper.value);
                    numberWraper.value++;
                    TimeUnit.SECONDS.sleep(1);
                }
                //通知线程A可以开始了
                reachSix.signal();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }).start();
    }
}
