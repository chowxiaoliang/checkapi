package check;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestTwo {

    public static boolean emailFormat(String email)
    {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    public static void main(String args[]) {
        System.out.println("请输入邮箱：");
        Scanner sc = new Scanner(System.in);
        String email = sc.next();
        if (TestTwo.emailFormat(email)) {
            System.out.println("√");
            return;
        } else {
            System.out.println("您的邮箱格式不正确，请重新输入！");
            main(args);
        }
    }
}
