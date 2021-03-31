package algorithm;

import org.apache.commons.lang3.StringUtils;

/**
 * 反转整个字符串里面的单词
 * my name is zhouliang
 * 翻转成：
 * gnailuohz si eman ym
 */
public class ReverseWords {

    public static void main(String[] args) {
        String string = "my name is zhouliang";
        System.out.println(reverse(string));

    }

    private static String reverse(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }

        String reverseStr = new StringBuilder(str).reverse().toString();
//        String[] strings = reverseStr.split(" ");
//        StringBuilder result = new StringBuilder();
//        for (String string : strings){
//            result = result.append(new StringBuilder(string).reverse()).append(" ");
//        }
//        System.out.println(result);
        return reverseStr;
    }
}
