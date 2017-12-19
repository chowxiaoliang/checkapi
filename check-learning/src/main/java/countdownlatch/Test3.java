package countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test3 {

    public static void main(String[] args){

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Callable<Integer> callable1 = (() -> {
            System.out.println("thread1 start...");
            int a = 0;
            a ++;
//            countDownLatch.countDown();
            System.out.println("thread1 end...");
            return a;
        });
        FutureTask futureTask1 = new FutureTask(callable1);
        new Thread(futureTask1).start();

        Callable callable2 = ()->{
            System.out.println("thread2 start...");
            int b = 0;
            b++;
//            countDownLatch.countDown();
            System.out.println("thread2 end...");
            return b;
        };
        FutureTask futureTask2 = new FutureTask(callable2);
        new Thread(futureTask2).start();

        try{
//            countDownLatch.await();
            System.out.println(futureTask1.get());
            System.out.println(futureTask2.get());
            System.out.println("main thread end!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

}
