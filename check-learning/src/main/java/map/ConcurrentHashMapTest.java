package map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @desc
 * put 和 putIfAbsent的区别：put会覆盖旧的值，putIfAbsent则会判断，有则不会put
 *
 * key: ConcurrentHashMap实现方式中的put使用了synchronize锁住代码块
 * @since 2018-05-17 15:36
 **/
public class ConcurrentHashMapTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConcurrentHashMapTest.class);

    private final static ConcurrentMap<Integer, Integer> CONCURRENT_MAP  = new ConcurrentHashMap<>(16);

    private final static Map<Integer, Integer> MAP = new HashMap<>(16);

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getCommonPoolUtil();

        for(int i=0;i<20000;i++){
            int finalI = i;
            int finalJ = i;
            threadPoolExecutor.submit(()->{
                CONCURRENT_MAP.put(finalI, finalJ);
                MAP.put(finalI, finalJ);
            });
        }

        long taskCount ;
        long completedTaskCount ;
        do{
            taskCount = threadPoolExecutor.getTaskCount();
            completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            LOGGER.info("task count is => {}", taskCount);
            LOGGER.info("completed task count is => {}", completedTaskCount);
            TimeUnit.MILLISECONDS.sleep(5);
        }while (taskCount!=completedTaskCount);

        LOGGER.info("task is finished!");

        LOGGER.info("size of concurrentMap is => {}", CONCURRENT_MAP.size());

        LOGGER.info("size of map is => {}", MAP.size());
    }

    private void test(){
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap(16);
        concurrentMap.put("name", "zhouliang");
        System.out.println(concurrentMap.get("name"));

        concurrentMap.put("name", "yangxiaoxiao");
        System.out.println(concurrentMap.get("name"));

        concurrentMap.put("name", "wangwenqi");
        System.out.println(concurrentMap.get("name"));

        concurrentMap.putIfAbsent("name", "male");
        System.out.println(concurrentMap.get("name"));

        System.out.println("=========================");

        Map<String, String> map = new HashMap(16);
        map.put("name", "yangxiaoxiao");
        System.out.println(map.get("name"));

        map.put("name", "zhouliang");
        System.out.println(map.get("name"));

        map.putIfAbsent("sex", "male");
        System.out.println(map.get("sex"));

        map.putIfAbsent("name", "wuke");
        System.out.println(map.get("name"));
    }
}
