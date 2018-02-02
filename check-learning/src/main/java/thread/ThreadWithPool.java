package thread;

import com.alibaba.fastjson.JSONObject;
import commonbeans.People;
import threadpool.ThreadPoolUtil;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouliang
 */
public class ThreadWithPool {

    private static Integer cal(int a, int b){
        int c = 0;
        try{
            c = a+b;
            return c;
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    public static void main(String[] args){

        People people = new People();
        people.setName("zhouliang");
        people.setAge(21);
        people.setSex("male");

        People people1 = new People();
        people1.setSex("female");
        people1.setName("yangxiaoxiao");
        people1.setAge(33);

        Integer result = null;

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.newCPUPool("testPool");

        threadPoolExecutor.submit(()-> System.out.println(JSONObject.toJSONString(people)));
        threadPoolExecutor.submit(()-> System.out.println(JSONObject.toJSONString(people1)));
        //用future获取值
        Future<Integer> future = threadPoolExecutor.submit(() -> cal(12, 34));
        //用futureTask获取值
        FutureTask<Integer> futureTask = new FutureTask<>(() -> cal(1, 34));
        threadPoolExecutor.submit(futureTask);

        try{
            int finalResult = future.get();
            int anotherResult = futureTask.get();
            System.out.println(finalResult);
            System.out.println(anotherResult);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(threadPoolExecutor.getPoolSize());

    }
}
