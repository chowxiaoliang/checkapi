//package com.zl.checkapi.thread;
//
//import com.zl.checkapi.entity.User;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
///**
// * @author zhouliang
// * @since 2017/11/26
// * @desc 终止一个线程 stop()方法(不合理的方法)
// */
//public class StopTest {
//
//    private static User user = new User();
//
//    private static Thread thread = null;
//
//    public static void threadOfChangeObject(){
//        thread = new Thread(() -> {
//            while (true){
//                synchronized (user){
//                    int v = (int)(System.currentTimeMillis()/1000);
//                    user.setId(v);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    user.setName(String.valueOf(v));
//                    System.out.println("change the value of object:"+ user.toString());
//                }
//                Thread.yield();
//            }
//        });
//    }
//
//    public static void threadOfReadObject(){
//        new Thread(() -> {
//            while (true){
//                synchronized(user){
//                    if(Integer.valueOf(user.getName()) != user.getId()){
//                        System.out.println(user.toString());
//                    }
//                }
//                Thread.yield();
//            }
//        }).start();
//    }
//
//    public static void main(String[] args) throws InterruptedException{
//
//        StopTest.threadOfReadObject();
//        while (true){
//            StopTest.threadOfChangeObject();
//            thread.start();
//            Thread.sleep(1500);
//            System.out.println("going to change the value of object:"+user.toString());
//            thread.stop();
//        }
//    }
//}
