package collections.queue;

import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {
    @Test
    public void setData() throws InterruptedException {

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(16);
        queue.add("ZHOULIANG");
        queue.add("YANGXIAOXIAO");
        queue.add("WANGWENQI");

        for(int i=0;i<queue.size();i++){
            queue.offer(String.valueOf(i));
        }
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
