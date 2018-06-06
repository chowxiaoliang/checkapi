package collections.map;

import threadpool.ThreadPoolUtil;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * TreeMap基于红黑树实现（非线程安全）
 * treeMap按顺序存储，hashMap按hash值存储
 */
public class TreeMapTest {
    private Map<String, Long> map = new TreeMap();
    synchronized void increase(String url){
        Long increaseTime = map.get(url)==null?1L:map.get(url)+1;
        map.put(url, increaseTime);
    }
    private Long getTime(String url){
        return map.get(url);
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = ThreadPoolUtil.getCommonPoolUtil();
        String url = "www.baiqishi.com";
        TreeMapTest treeMapTest = new TreeMapTest();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for(int i=0;i<10000;i++){
            executorService.execute(()->{
                treeMapTest.increase(url);
                System.out.println("current time is => " + treeMapTest.getTime(url));
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(5, TimeUnit.MINUTES);
        executorService.shutdown();
        System.out.println("all time is => "+ treeMapTest.getTime(url));
    }
}
