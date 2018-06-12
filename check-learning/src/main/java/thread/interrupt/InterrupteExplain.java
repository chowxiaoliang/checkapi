package thread.interrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @since 2018-05-31 11:09
 **/
public class InterrupteExplain {

    private final static Logger LOGGER = LoggerFactory.getLogger(InterrupteExplain.class);

    private void stopThread() throws InterruptedException{
        if(Thread.interrupted()){
            throw new InterruptedException("gasdgsdfg");
        }
    }

    public static void main(String[] args) {
        //step1
//        InnerClass innerClass = new InnerClass();
//        Thread thread = new Thread(innerClass);
//        thread.start();
//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.interrupt();

        //step2
        CallAbleClass callAbleClass = new CallAbleClass();
        FutureTask<String> futureTask = new FutureTask<>(callAbleClass);
        Thread thread = new Thread(futureTask);
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
        LOGGER.info("start stop thread...");
        thread.interrupt();
        try {
            String result = futureTask.get();
            LOGGER.info("result is => {}", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        LOGGER.info("main thread is finished!");

        for(int i=0;i<10;i++){
            LOGGER.info("current main thread is going on! i => {}", i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class CallAbleClass implements Callable<String>{
        InterrupteExplain interrupteExplain = new InterrupteExplain();

        @Override
        public String call() throws Exception {
            for(int i=0;i<1000;i++){

                interrupteExplain.stopThread();
                LOGGER.info("current num is => {}", i);
            }
            return "success";
        }
    }

    static class InnerClass implements Runnable{

        InterrupteExplain interrupteExplain = new InterrupteExplain();

        @Override
        public void run() {
            for(int i=0;i<10;i++){
                LOGGER.info("current num is => {}", i);
                if(i==5){
//                    interrupteExplain.stopThread();
//                    Thread.currentThread().interrupt();

                }
                LOGGER.info("previous sleep order is going on! i=>{}", i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    LOGGER.info("睡眠被中断了！ i => {}", i);
                }

                LOGGER.info("next sleep order is going on! i=>{}", i);
            }
        }
    }
}
