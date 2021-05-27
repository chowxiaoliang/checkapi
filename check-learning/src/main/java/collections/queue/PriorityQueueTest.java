package collections.queue;

import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {

        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(10);
        priorityQueue.add(5);
        priorityQueue.add(6);
        priorityQueue.add(20);
        priorityQueue.add(77);

        priorityQueue.hashCode();

//        System.out.println(JSONObject.toJSONString(priorityQueue.toArray()));

        while (priorityQueue.iterator().hasNext()){
            int result = priorityQueue.poll();

        }

        priorityQueue.poll();

        System.out.println("=================");

        for (Integer integer : priorityQueue){
            System.out.println(integer);
        }
//        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//
//        priorityQueue1.add(1);
//        priorityQueue1.add(10);
//        priorityQueue1.add(5);
//        priorityQueue1.add(6);
//        priorityQueue1.add(2);
//        priorityQueue1.add(7);
//
//        System.out.println(JSONObject.toJSONString(priorityQueue1.toArray()));
    }
}
