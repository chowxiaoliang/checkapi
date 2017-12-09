package string;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringTestFormate {

    public static void main(String[] args){

        StringTestFormate stringTestFormate = new StringTestFormate();
        System.out.println(stringTestFormate.getStrDate()+stringTestFormate.operateUUID());
    }

    public String getStrDate(){
        int maxint = 99999999;
        int minint = 0;
        if(maxint<minint){
            minint = 0;
        }
        minint++;
        String result = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+ String.format("%1$013d", minint);

        System.out.println(result);
        return result;
    }

    public String generateUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    public String operateUUID(){
        String uuid = this.generateUUID();
        return uuid.substring(0,12);
    }
}
