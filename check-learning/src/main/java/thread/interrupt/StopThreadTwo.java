package thread.interrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @since 2018-06-13 11:28
 * @desc 通过抛出异常InterruptedException来终止线程 （https://www.cnblogs.com/hapjin/p/5450779.html）
 * interrupt()方法有两个作用，一个是将线程的中断状态置位(中断状态由false变成true)；另一个则是：让被中断的线程抛出InterruptedException异常。
    这是很重要的。这样，对于那些阻塞方法(比如 wait() 和 sleep())而言，当另一个线程调用interrupt()中断该线程时，该线程会从阻塞状态退出并且抛出中断异常。这样，我们就可以捕捉到中断异常，并根据实际情况对该线程从阻塞方法中异常退出而进行一些处理。
    比如说：线程A获得了锁进入了同步代码块中，但由于条件不足调用 wait() 方法阻塞了。这个时候，线程B执行 threadA.interrupt()请求中断线程A，此时线程A就会抛出InterruptedException，我们就可以在catch中捕获到这个异常并进行相应处理(比如进一步往上抛出)
    因此，上面就是一个采用抛出异常的方式来结束线程的示例。尽管该示例的实用性不大。原因在 IBM的这篇博文中：我们 生吞了中断。
    在第14行，我们直接catch了异常，然后打印输出了一下而已，调用栈中的更高层的代码是无法获得关于该异常的信息的。
    第16行的e.printStackTrace()作用就相当于
    “（仅仅记录 InterruptedException 也不是明智的做法，因为等到人来读取日志的时候，再来对它作出处理就为时已晚了。）”---摘自参考博文
    上面我们是在run()方法中抛出异常，符合这里描述的：
    有时候抛出 InterruptedException 并不合适，例如当由 Runnable 定义的任务调用一个
    可中断的方法时，就是如此。在这种情况下，不能重新抛出 InterruptedException，但是
    您也不想什么都不做。当一个阻塞方法检测到中断并抛出 InterruptedException 时，它
    清除中断状态。如果捕捉到 InterruptedException 但是不能重新抛出它，那么应该保留
    中断发生的证据，以便调用栈中更高层的代码能知道中断，并对中断作出响应。该任务可以
    通过调用 interrupt() 以 “重新中断” 当前线程来完成，如清单 3 所示。 -----“摘自参考博文”
    因为，run方法是实现的Runnable接口中的方法。不能像下面这样定义，也即上面所说的：“不能重新抛出InterruptedException”。
    @Override
    public void run() throws InterruptedException{//这是错误的
    //do something...
    因此，一个更好的解决方案是：调用 interrupt() 以 “重新中断” 当前线程。改进MyThread类中catch异常的方式，如下：

    这样，就由 生吞异常 变成了 将 异常事件 进一步扩散了。
 **/
public class StopThreadTwo {
    private final static Logger LOGGER = LoggerFactory.getLogger(StopThreadTwo.class);

    public static void main(String[] args) {
        InnerThread innerThread = new InnerThread();
        Thread thread = new Thread(innerThread);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(90);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        //不清除中断标志
        if(thread.isInterrupted()){
            LOGGER.info("current num is => {}", innerThread.num);
            LOGGER.info("innerThread is stop!");
        }
        LOGGER.info("main thread is going to stop!");
    }
    static class InnerThread implements Runnable{
        volatile int num = 0;
        @Override
        public void run() {
            try{
                while(true){
                    num ++ ;
                    LOGGER.info("current num is => {}", num);
                    if(Thread.currentThread().isInterrupted()){
                        throw new InterruptedException("current num is interrupted!");
                    }
                }
            }catch (InterruptedException e){
                //重新中断(设置中断标志)
                Thread.currentThread().interrupt();
                LOGGER.info(e.getMessage());
            }
        }
    }
}
