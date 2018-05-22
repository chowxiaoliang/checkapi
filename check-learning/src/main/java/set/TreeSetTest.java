package set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouliang
 * @desc
 * TreeSet特点:
 * TreeSet是SortedSet接口的唯一实现类，TreeSet可以确保集合元素处于排序状态。TreeSet支持两种排序方式，自然排序和定制排序，
    其中自然排序为默认的排序方式。向TreeSet中加入的应该是同一个类的对象。
    TreeSet判断两个对象不相等的方式是两个对象通过equals方法返回false，或者通过CompareTo方法比较没有返回0
    自然排序使用要排序元素的CompareTo（Object obj）方法来比较元素之间大小关系，然后将元素按照升序排列。
    Java提供了一个Comparable接口，该接口里定义了一个compareTo(Object obj)方法，该方法返回一个整数值，实现了该接口的对象就可以比较大小。
    obj1.compareTo(obj2)方法如果返回0，则说明被比较的两个对象相等，如果返回一个正数，则表明obj1大于obj2，如果是负数，则表明obj1小于obj2。
    如果我们将两个对象的equals方法总是返回true，则这两个对象的compareTo方法返回应该返回0定制排序
    自然排序是根据集合元素的大小，以升序排列，如果要定制排序，应该使用Comparator接口，实现 int compare(T o1,T o2)方法。

    最重要：

    1、TreeSet 是二叉树实现的,Treeset中的数据是自动排好序的，不允许放入null值。

    2、HashSet 是哈希表实现的,HashSet中的数据是无序的，可以放入null，但只能放入一个null，两者中的值都不能重复，就如数据库中唯一约束。

    3、HashSet要求放入的对象必须实现HashCode()方法，放入的对象，是以hashcode码作为标识的，而具有相同内容的 String对象，hashcode是一样，所以放入的内容不能重复。但是同一个类的对象可以放入不同的实例 。

 * @since 2018-05-17 16:28
 **/
public class TreeSetTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TreeSetTest.class);

    private final static Set<Integer> SET = new TreeSet<>(16);

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getCommonPoolUtil();

        for(int i=0;i<20000;i++){
            int finalI = i;
            threadPoolExecutor.submit(()->{
                SET.add(finalI);
            });
        }
        threadPoolExecutor.shutdown();
        long taskCount ;
        long completedTaskCount ;
        do{
            taskCount = threadPoolExecutor.getTaskCount();
            completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
            LOGGER.info("task count is => {}", taskCount);
            LOGGER.info("completed task count is => {}", completedTaskCount);
        }while (taskCount!=completedTaskCount);
        LOGGER.info("task is finished!");

        LOGGER.info("size of set is => {}", SET.size());

        for(Integer integer : SET){
            LOGGER.info("current data is => {}", integer);
        }
    }
}
