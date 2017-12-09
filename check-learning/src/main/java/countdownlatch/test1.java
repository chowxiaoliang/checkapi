package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class test1 {

    public static void main(String[] args) throws InterruptedException{

        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println("thread1 start...");
            threadSleep();
            System.out.println("thread1 end....");
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("thread2 start...");
            threadSleep();
            System.out.println("thread2 end...");
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        System.out.println("main thread end!");
    }

    private static void threadSleep(){
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
