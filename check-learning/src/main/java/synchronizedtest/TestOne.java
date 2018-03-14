package synchronizedtest;

public class TestOne {
    public static void main(String[] args){
        ThreadTestOne threadTest = new ThreadTestOne("zhouliang");
        ThreadTestOne threadTest1 = new ThreadTestOne("yangxiaoxiao");
        Thread thread = new Thread(threadTest);
        Thread thread1 = new Thread(threadTest1);
        thread.start();
        thread1.start();
    }
}
