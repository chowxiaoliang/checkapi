package schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {


    public static void main(String[] args){
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("this is circle task!");
        }, 10, 10, TimeUnit.SECONDS);
    }
}
