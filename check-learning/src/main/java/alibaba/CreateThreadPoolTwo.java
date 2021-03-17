package alibaba;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThreadPoolTwo {

    private CreateThreadPoolTwo(){}

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ExecutorService getExecutorService(){
        return executorService;
    }
}
