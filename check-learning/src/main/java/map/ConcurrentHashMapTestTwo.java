package map;

import org.springframework.util.StringUtils;
import threadpool.ThreadPoolUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentHashMapTestTwo {
    private Map<String, AtomicLong> map = new ConcurrentHashMap<>(16);
    private Long increase(String url){
        AtomicLong number = map.get(url);
        if(number==null){
            //该url在map中不存在
            AtomicLong newNumber = new AtomicLong(0);
            number = map.putIfAbsent(url, newNumber);
            //如果该url对应的value不存在则返回0 否则返回null
            if(number==null){
                number = newNumber;
            }
        }
        return number.incrementAndGet();
    }
    private AtomicLong getTime(String url){
        AtomicLong time = map.get(url);
        if(StringUtils.isEmpty(time)){
            return new AtomicLong();
        }
        return time;
    }
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMapTestTwo concurrentHashMapTestTwo = new ConcurrentHashMapTestTwo();
        ExecutorService executorService = ThreadPoolUtil.getCommonPoolUtil();
        final String url = "www.baiqishi.com";
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for(int i=0;i<10000;i++){
            executorService.execute(()->{
                concurrentHashMapTestTwo.increase(url);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        System.out.println(concurrentHashMapTestTwo.getTime(url));
        System.exit(0);
    }
}
