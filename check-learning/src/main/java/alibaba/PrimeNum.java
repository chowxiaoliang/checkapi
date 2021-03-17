package alibaba;

import java.util.Scanner;

/**
 * 在控制台输入任意一个数数n，打印出小于等于n的所有素数，请确保算法是高效的，尽量最大可能减少计算量
 */
public class PrimeNum {

    public static void main(String[] args) {
        int num;
        boolean flag = true;
        Scanner ip = new Scanner(System.in);
        System.out.print("Enter a number: ");
        num = ip.nextInt();
        for (int j=1;j<num;j++){
            for (int i = 2; i <= j / 2; i++) { // you can also use i <= Math.sqrt(num)
                if (j % i == 0){
                    flag = false;
//                    System.out.println("不是素数:"+j);
                    break;
                }
            }
            if (flag){
                System.out.println("素数是："+j);
            }
            flag = true;
        }

        ip.close();
    }
}
