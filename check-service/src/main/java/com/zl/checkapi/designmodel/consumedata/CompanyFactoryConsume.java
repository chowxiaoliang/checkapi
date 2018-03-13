package com.zl.checkapi.designmodel.consumedata;

import com.zl.checkapi.designmodel.DataObject;
import com.zl.checkapi.designmodel.DataObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class CompanyFactoryConsume implements DataObjectFactory {
    @Override
    public DataObject getData(String data) {
        if("company".equals(data)){
            return new PeopleConsume();
        }
        return null;
    }
}
