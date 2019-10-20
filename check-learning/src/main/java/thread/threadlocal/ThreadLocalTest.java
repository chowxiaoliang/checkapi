package thread.threadlocal;

import threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @desc ThreadLocal应用 必须先set然后get 最后要remove
 * ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量
 *首先，ThreadLocal 不是用来解决共享对象的多线程访问问题的，一般情况下，通过
 *ThreadLocal.collections.set() 到线程中的对象是该线程自己使用的对象，其他线程是不需要访问的，
 *也访问不到的。各个线程中访问的是不同的对象。
 *另外，说ThreadLocal使得各线程能够保持各自独立的一个对象，并不是通过
 *ThreadLocal.collections.set()来实现的，而是通过每个线程中的new 对象 的操作来创建的对象，每个
 *线程创建一个，不是什么对象的拷贝或副本。通过ThreadLocal.collections.set()将这个新创建的对象的
 *引用保存到各线程的自己的一个map中，每个线程都有这样一个map，执行
 *ThreadLocal.get()时，各线程从自己的map中取出放进去的对象，因此取出来的是各自自己
 *线程中的对象，ThreadLocal实例是作为map的key来使用的。
 *
 *如果ThreadLocal.collections.set()进去的东西本来就是多个线程共享的同一个对象，那么多个线程的ThreadLocal.get()取得的还是这个共享对象本身，还是有并发访问问题。
 *
 * ThreadLocal不是用来解决对象共享访问问题的，而主要是提供了保持对象的方法和避免参数传递的方便的对象访问方式。归纳了两点：
 * 总结：
 * 每个线程中都有一个自己的ThreadLocalMap类对象，可以将线程自己的对象保持到其中，各管各的，线程可以正确的访问到自己的对象。
 * 将一个共用的ThreadLocal静态实例作为key，将不同对象的引用保存到不同线程的ThreadLocalMap中，然后在线程执行的各处通过这个静态ThreadLocal实例的get()方法取得自己线程保存的那个对象，避免了将这个对象作为参数传递的麻烦。
 *
 *
 * ThreadLocal的应用场合，我觉得最适合的是按线程多实例（每个线程对应一个实例）的对象的访问，并且这个对象很多地方都要用到
 *
 * 最常见的ThreadLocal使用场景为 用来解决 数据库连接、Session管理等。
 *
 *  ThreadLocalMap 中使用的 key 为ThreadLocal对象的弱引用，在垃圾回收器对其进行回收时，这个key是会被回收掉的，但是value是强引用，不会被回收，这样一来就会出现key为null的value，
 *  这样的话就造成了内存泄漏，不过threadLocal已经为我们考虑了，手动调用remove（）则可以清除key为null的记录。

 * @since 2018-05-08 17:23
 **/
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private void setName(String name){
        threadLocal.set(name);
    }

    private String getName(){
        return threadLocal.get();
    }

    private void remove(){
        threadLocal.remove();
    }
    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        //主线程
        threadLocalTest.setName(Thread.currentThread().getName());
        System.out.println(threadLocalTest.getName());
        //新线程
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getCommonPoolUtil();
        for(int i=0;i<5;i++){
            threadPoolExecutor.submit(()->{
                threadLocal.set(Thread.currentThread().getName());
                System.out.println(threadLocal.get());
            });
        }
        do{
            try{
                TimeUnit.MILLISECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }while (threadPoolExecutor.getQueue().size()>0);
        threadPoolExecutor.shutdown();
        threadLocal.remove();
        System.out.println(threadLocalTest.getName());
    }
}
