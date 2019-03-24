package check;

public class FinalizeTest {
    public static void main(String[] args) {
        System.out.println("返回的结果是=>" + FinalizeTest.sayMessage());

    }

    static String sayMessage(){
        try {
            System.out.println("执行try里面的语句！");
            int a = 1/0;
            return "try";
        }catch (Exception e){
            System.out.println("遇到异常了！" + e);
            return "exception";
        }finally {
            System.out.println("执行finally里面的语句！");
            return "finally";
        }
    }
}
