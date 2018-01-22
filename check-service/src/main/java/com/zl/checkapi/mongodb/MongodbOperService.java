package com.zl.checkapi.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service(value = "mongodbOperService")
public class MongodbOperService {

    @Autowired
    private MongodbConfig mongodbConfig;

    private MongodbOper mongodbOper;

    private final static String FEEDBACK_TABLE_NAME = "RISK_BQS_BATCH_FEEDBACK";

    @PostConstruct
    public void init() {
        mongodbOper = MongodbOperImpl.instance(mongodbConfig);
    }

    public List<DBObject> getMongodbData(){
        DBObject dbObject = new BasicDBObject();
        List<DBObject> resultList = mongodbOper.selectAll(FEEDBACK_TABLE_NAME, dbObject);
        return resultList;
    }

}
