package alibaba;

/**
 * 字符串查找(不能使用String类的indexOf方法) 输入: 字符串str1， 字符串str2 输出: 字符串str2在字符串str1中第一次出现的位置。
 * 如果没有返回-1. 举例： str1=“www.mybank.cn” str2="mybank" -> 4
 */
public class FindString {

    public static void main(String[] args) {

        String name1 ="zhouliang";
        String name2 = "uli";
        System.out.println(find(name1, name2));
    }

    public static int find(String stringOne, String stringTwo){
        int L = stringTwo.length();
        int n = stringOne.length();
        for(int start = 0;start<n-L+1;start++){
            if(stringOne.substring(start,start+L).equals(stringTwo)){
                return start + 1;
            }
        }
        return -1;
    }
}
