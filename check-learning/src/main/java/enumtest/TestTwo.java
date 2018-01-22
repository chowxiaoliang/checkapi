package enumtest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTwo {

    public static void main(String[] args) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datestr1 = "2018-01-18";
        String datestr2 = "1516263405000";
        Date resultStr1 = simpleDateFormat.parse(datestr1);
//        Date resultStr2 = simpleDateFormat.parse(datestr2);
        System.out.println(resultStr1);
//        System.out.println(resultStr2);
        if(datestr1.contains("-")){
            System.out.println("包含数据-");
        }
    }
}
