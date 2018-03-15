package synchronizedtest;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTestTwo implements Runnable{
    private String name;
    private ReentrantLock reentrantLock;
    public ThreadTestTwo(String name, ReentrantLock reentrantLock){
        System.out.println(name);
        this.name = name;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        reentrantLock.lock() ;
        for(int i=0;i<5;i++){
            try{
                System.out.println("姓名=>"+name+"第"+i+"次调用");
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        reentrantLock.unlock();
    }
}
