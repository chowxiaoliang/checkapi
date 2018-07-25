package dailytask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author zhouliang
 * @since 2018-06-27 9:39
 **/
public class TaskTwo {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaskTwo.class);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("请输入n值");
        String inputStr = scanner.nextLine();
        scanner.close();
        int n = Integer.valueOf(inputStr);
        int result = 1 ;
        while(n > 0){
            result = result * n;
            n --;
        }
        LOGGER.info("得到的阶乘值是=>{}", result);
    }
}
