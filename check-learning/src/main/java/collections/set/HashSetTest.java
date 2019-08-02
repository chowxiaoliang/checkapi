package collections.set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouliang
 * @desc HashSet有以下特点
 * 不能保证元素的排列顺序，顺序有可能发生变化
 * 不是同步的
 * 集合元素可以是null,但只能放入一个null
 * 当向HashSet集合中存入一个元素时，HashSet会调用该对象的hashCode()方法来得到该对象的hashCode值，然后根据 hashCode值来决定该对象在HashSet中存储位置。
 * 简单的说，HashSet集合判断两个元素相等的标准是两个对象通过equals方法比较相等，并且两个对象的hashCode()方法返回值相等
 * 注意，如果要把一个对象放入HashSet中，重写该对象对应类的equals方法，也应该重写其hashCode()方法。其规则是如果两个对象通过equals方法比较返回true时，
 * 其hashCode也应该相同。另外，对象中用作equals比较标准的属性，都应该用来计算 hashCode的值。
 * @since 2018-05-17 17:54
 * <p>
 * 需要考虑的点：
 * hashSet 和 hashMap
 * linkedHashSet 和 linkedHashMap
 * treeSet 和 treeMap
 * 这三者之间的关系，底层存储结构是否相同，存和取的方式异同点
 * <p>
 * HashSet是基于HashMap来实现的，操作很简单，更像是对HashMap做了一次“封装”，而且只使用了HashMap的key来实现各种特性，我们先来感性的认识一下这个结构：
 * HashSet<String> collections.set = new HashSet<String>();
 * collections.set.add("语文");
 * collections.set.add("数学");
 * collections.set.add("英语");
 * collections.set.add("历史");
 * collections.set.add("政治");
 * collections.set.add("地理");
 * collections.set.add("生物");
 * collections.set.add("化学");
 * 其大致的结构是这样的：
 * <p>
 * private transient HashMap<E,Object> collections.map;
 * // Dummy value to associate with an Object in the backing Map
 * private static final Object PRESENT = new Object();
 * map是整个HashSet的核心，而PRESENT则是用来造一个假的value来用的。
 * <p>
 * 2. 基本操作
 * public boolean add(E e) {
 * return collections.map.put(e, PRESENT)==null;
 * }
 * <p>
 * public boolean remove(Object o) {
 * return collections.map.remove(o)==PRESENT;
 * }
 * <p>
 * public boolean contains(Object o) {
 * return collections.map.containsKey(o);
 * }
 * <p>
 * public int size() {
 * return collections.map.size();
 * }
 * 基本操作也非常简单，就是调用HashMap的相关方法，其中value就是之前那个dummy的Object。所以，只要了解#7 HashMap的实现就可以了。
 **/
public class HashSetTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(HashSetTest.class);

    private final static Set<Integer> SET = new HashSet<>(16);

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getCommonPoolUtil();

        for (int i = 0; i < 20000; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                SET.add(finalI);
            });
        }

        threadPoolExecutor.shutdown();
        long taskCount;
        long completedTaskCount;
        do {
            taskCount = threadPoolExecutor.getTaskCount();
            completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            LOGGER.info("task count is => {}", taskCount);
            LOGGER.info("completed task count is => {}", completedTaskCount);
        } while (taskCount != completedTaskCount);

        LOGGER.info("task is finished!");

        LOGGER.info("size of collections.set is => {}", SET.size());

        for (Integer integer : SET) {
            LOGGER.info("current data is => {}", integer);
        }
    }
}
