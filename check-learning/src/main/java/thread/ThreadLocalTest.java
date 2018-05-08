package thread;

import threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @desc ThreadLocal应用 必须先set然后get 最后要remove
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
