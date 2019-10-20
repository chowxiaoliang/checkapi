package thread;

import threadpool.ThreadPoolUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouliang
 * @desc Semaphore信号量
 * Semaphore(信号量)，用于做限流处理，内部是基于AQS的共享模式
 * Semaphore主要方法：
    Semaphore(int permits):构造方法，创建具有给定许可数的计数信号量并设置为非公平信号量。
    Semaphore(int permits,boolean fair):构造方法，当fair等于true时，创建具有给定许可数的计数信号量并设置为公平信号量。
    void acquire():从此信号量获取一个许可前线程将一直阻塞。相当于一辆车占了一个车位。
    void acquire(int n):从此信号量获取给定数目许可，在提供这些许可前一直将线程阻塞。比如n=2，就相当于一辆车占了两个车位。
    void release():释放一个许可，将其返回给信号量。就如同车开走返回一个车位。
    void release(int n):释放n个许可。
    int availablePermits()：当前可用的许可数

 */
public class TestWithSemaphore {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getCommonPoolUtil();

        Semaphore semaphore = new Semaphore(5, true);

        threadPoolExecutor.execute(() -> {
            try {
                semaphore.acquire();
                System.out.println("当前执行线程1！");
                semaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        threadPoolExecutor.execute(() -> {
            try {
                semaphore.acquire();
                System.out.println("当前执行线程2！");
                semaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        threadPoolExecutor.execute(() -> {
            try {
                semaphore.acquire();
                System.out.println("当前执行线程3！");
                semaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        threadPoolExecutor.execute(() -> {
            try {
                semaphore.acquire();
                System.out.println("当前执行线程4！");
                semaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        threadPoolExecutor.execute(() -> {
            try {
                semaphore.acquire();
                System.out.println("当前执行线程5！");
                semaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        threadPoolExecutor.shutdown();
    }
}
