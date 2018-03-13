package com.zl.checkapi.designmodel.consumedata;

import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.designmodel.DataObject;
import com.zl.checkapi.pojo.People;
import com.zl.checkapi.util.SpringContext;
import org.springframework.stereotype.Component;

@Component
public class PeopleConsume extends DataObject{
    @Override
    public void processData() {
        People people = SpringContext.getBean("people", People.class);
        System.out.println("输出个人相关的信息是=>"+JSONObject.toJSONString(people));
    }
}
