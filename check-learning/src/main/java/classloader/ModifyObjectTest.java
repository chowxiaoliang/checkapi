package classloader;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ModifyObjectTest {

    public static void main(String[] args) throws Exception{
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list, "zhouliang");
        System.out.println(JSONObject.toJSONString(list));
    }

}
