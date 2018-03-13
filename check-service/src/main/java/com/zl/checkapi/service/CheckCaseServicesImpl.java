package com.zl.checkapi.service;

import com.zl.checkapi.designmodel.DataObject;
import com.zl.checkapi.designmodel.DataObjectFactory;
import com.zl.checkapi.designmodel.consumedata.CompanyFactoryConsume;
import com.zl.checkapi.designmodel.consumedata.PeopleFactoryConsume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by zhouliang on 2017/9/11.
 */
@Path("case")
@Service(value="checkCaseService")
@Produces({MediaType.APPLICATION_JSON})
public class CheckCaseServicesImpl implements CheckCaseService{
    @Autowired
    private PeopleFactoryConsume peopletFactoryConsume;
    @Autowired
    private CompanyFactoryConsume companytFactoryConsume;

    private DataObjectFactory dataObjectFactory;

    private static Logger LOG = LoggerFactory.getLogger(CheckCaseServicesImpl.class);

    @Path("getCase")
    @POST
    @Override
    public String checkCase(String req) {
        LOG.info("start to execute getCase....");
        LOG.info("request param is :"+req);
        DataObject dataObject = peopletFactoryConsume.getData("people");
        dataObject.processData();
        return "success";
    }
}
