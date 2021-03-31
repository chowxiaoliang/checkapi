package algorithm;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀
 */
public class MaxComPre {

    public static void main(String[] args) {

//        String[] strings = {"flower","flow","flight"};
        String[] strings = {"zhou", "zhouliang" ,"zh"};
        System.out.println(getMaxCommonPre(strings));
    }

    private static int getMaxCommonPre(String[] strings){
        if (strings.length == 0){
            System.out.println(" ");
            return 0;
        }
        boolean flag = true;
        String commonPre = "";
        for (int i=1;i<strings[0].length();i++){
            String subString = strings[0].substring(0, i);
            for (int j=1;j<strings.length;j++){
                if (strings[j].startsWith(subString)){
                    if ((j == strings.length - 1)){
                        commonPre = subString;
                    }
                }else {
                    flag = false;
                    break;
                }
            }
            if (! flag){
                break;
            }
        }
        System.out.println(commonPre);
        return commonPre.length();

    }
}
