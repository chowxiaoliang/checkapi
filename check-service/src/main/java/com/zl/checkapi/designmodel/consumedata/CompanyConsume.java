package com.zl.checkapi.designmodel.consumedata;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.designmodel.DataObject;
import com.zl.checkapi.pojo.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyConsume extends DataObject {
    @Override
    public void processData() {
        Company company = SpringContainer.getContext().getBean("company", Company.class);
        System.out.println("数据公司相关的信息是=>"+JSONObject.toJSONString(company));
    }
}
