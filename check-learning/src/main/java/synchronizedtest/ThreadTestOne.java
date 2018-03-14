package synchronizedtest;

public class ThreadTestOne implements Runnable {
    private String name;
    public ThreadTestOne(String name){
        System.out.println(name);
        this.name = name;
    }
    @Override
    public void run() {
//        synchronized (ThreadTest.class){
//
//        }
        for(int i=0;i<5;i++){
            try{
                System.out.println("姓名=>"+name+"第"+i+"次调用");
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
