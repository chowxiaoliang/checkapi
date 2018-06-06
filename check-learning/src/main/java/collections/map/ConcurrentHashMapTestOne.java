package collections.map;

import threadpool.ThreadPoolUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 模拟调用次数
 */
public class ConcurrentHashMapTestOne {
    private Map<String, Long> map = new ConcurrentHashMap<>(16);
    private final static ConcurrentHashMapTestOne concurrentHashMapTestOne = new ConcurrentHashMapTestOne();
    private ConcurrentHashMapTestOne(){}
    public ConcurrentHashMapTestOne getInstanct(){
        synchronized (ConcurrentHashMapTestOne.class){
            return concurrentHashMapTestOne;
        }
    }

    synchronized Long increase(String url){
        Long time = map.get(url);
        Long increTime = 0L;
        increTime = time==null? 1L : time+1;
        map.put(url, increTime);
        return increTime;
    }
    private Long getCount(String url){
        return map.get(url);
    }
    public static void main(String[] args) throws InterruptedException {
        String url = "www.baiqishi.com";
        ExecutorService executorService = ThreadPoolUtil.getCommonPoolUtil();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        ConcurrentHashMapTestOne concurrentHashMapTestOne = new ConcurrentHashMapTestOne();
        for(int i=0;i<10000;i++){
            executorService.execute(()->{
                concurrentHashMapTestOne.increase(url);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(5, TimeUnit.MINUTES);
        System.out.println("finished num is => "+concurrentHashMapTestOne.map.get(url));
    }
}
