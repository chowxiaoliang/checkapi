package com.zl.checkapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOG = LoggerFactory.getLogger(CheckCaseServicesImpl.class);
    @Path("getCase")
    @POST
    @Override
    public String checkCase(String req) {
        LOG.info("start to execute getCase....");
        LOG.info("request param is :"+req);
        return "success";
    }
}
