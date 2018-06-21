package dailytask;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaochenxi
 * @Title: ${file_name}
 * @Description: ${todo}
 * @date 2018/5/30 22:02
 */
public class Test {

    private static BlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>();
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1,2,1, TimeUnit.HOURS,blockingDeque);
    private static int i = 0;

    public static void test(){
        System.out.println("------start-----------");
        executor.execute(()->{
            while (true){
                i++;
                System.out.println(i);
                System.out.println(Thread.interrupted());
            }

        });

        while (true){
            System.out.println("------------------------------"+i);
            if(i==10){
                executor.shutdownNow();
                break;
            }
        }
        System.out.println("------end-----------");
    }

    public static void main(String [] args){
        test();
    }
}