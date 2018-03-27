package Test.timeunit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TimeUnitTestOne {
    private static final Logger logger = LoggerFactory.getLogger(TimeUnitTestOne.class);
    public static void main(String[] args){
        Long seconds = TimeUnit.SECONDS.toMillis(50);
        logger.info("result=>{}", seconds);
    }
}
