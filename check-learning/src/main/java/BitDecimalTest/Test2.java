package BitDecimalTest;

import java.math.BigDecimal;

public class Test2 {

    public static void main(String[] args){

        BigDecimal bigDecimal1 = new BigDecimal("1.22");
        BigDecimal bigDecimal2 = new BigDecimal("2.21");

        BigDecimal bigDecimal = bigDecimal1.subtract(bigDecimal2);
        System.out.println(bigDecimal);

        Double result = bigDecimal.doubleValue();
        System.out.println(result);
        if(result<0){
            System.out.println("得到的结果小于0");
        }

    }
}
