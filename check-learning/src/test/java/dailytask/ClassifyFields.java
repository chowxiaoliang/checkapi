package dailytask;

import com.alibaba.fastjson.JSONObject;

public class ClassifyFields {

    public static void main(String[] args){

        //用户基本信息
        JSONObject baseInfo = new JSONObject();
        ClassifyFields classifyFields = new ClassifyFields();
        int result =  classifyFields.cal();
        System.out.println("结果是:"+result);
    }

    public int cal(){
        int c = 0;
        try{
            int a = 0;
            int b = 1;
            c = b/a;
            return c;
        }catch (Exception e){
            System.out.println("产生异常了");
            e.printStackTrace();
            return c;
        }finally {
            System.out.println("结束了");
            c -- ;
        }
    }
}
