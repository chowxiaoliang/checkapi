package algorithm;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class RepetMaxStr {

    public static void main(String[] args) {

        String str = "abcabcabb";
        System.out.println(getMaxRepetMaxStr(str));
    }

    private static int getMaxRepetMaxStr(String str){

        if (StringUtils.isEmpty(str)){
            return 0;
        }
        int start = 0;
        int end = 0;
        int result = 0;
        Set<Character> hashSet = new HashSet<>();

        while (start < str.length() && end < str.length()){
            if (hashSet.contains(str.charAt(end))){
                hashSet.remove(str.charAt(start ++));
            }else {
                hashSet.add(str.charAt(end ++));
                result = Math.max(result,end - start);
            }
        }
        return result;
    }
}
