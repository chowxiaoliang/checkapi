package synchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhouliang
 * @since 2018-05-14 16:47
 **/
public class Mutex implements Lock , java.io.Serializable {

    private class Sync extends AbstractQueuedSynchronizer{
        //判断是否锁定状态
        @Override
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }

        //尝试获取资源,立即返回，成功则返回true 失败则返回false
        @Override
        public boolean tryAcquire(int acquires){
            //这里限定为一个
            assert acquires == 1;
            if(compareAndSetState(0, 1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        public boolean tryRelease(int acquires){
            //这里限定为一个
            assert acquires == 1;
            //验证状态
            if(getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

    /**
     * 所有真正同步类的实现都依赖继承于AQS的自定义同步器
     */
    private final Sync sync = new Sync();

    //lock<-->acquire。两者语义一样：获取资源，即便等待，直到成功才返回。
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    //tryLock<-->tryAcquire。两者语义一样：尝试获取资源，要求立即返回。成功则为true，失败则为false。
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    //unlock<-->release。两者语文一样：释放资源
    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     *
     * @desc 判断锁是否占用
     * @return
     */
    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
}
