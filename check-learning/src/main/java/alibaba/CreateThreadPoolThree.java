package alibaba;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThreadPoolThree {

    private CreateThreadPoolThree(){}

    public ExecutorService getExecutorService(){
        return InnerThreadPool.executorService;
    }

    static class InnerThreadPool{
        private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    }
}
