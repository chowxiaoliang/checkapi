package com.zl.checkapi.mongodb;

import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * desc
 *
 * @author shijiankai
 */
public class MongodbOperImpl implements MongodbOper {
    private static final Logger LOG = LoggerFactory.getLogger(MongodbOperImpl.class);
    private static final String PK = "_id";
    private static MongodbOperImpl mongodbOper;
    private MongoClient mongoClient;
    private String dbName;

    public static MongodbOperImpl instance(MongodbConfig mongodbConfig) {
        if (mongodbOper == null) {
            synchronized (MongodbOperImpl.class) {
                if (mongodbOper == null) {
                    mongodbOper = new MongodbOperImpl(mongodbConfig);
                }
            }
        }
        return mongodbOper;
    }

    private MongodbOperImpl(MongodbConfig mongodbConfig) {
        try {
            MongoClientOptions.Builder buide = new MongoClientOptions.Builder();
            buide.connectionsPerHost(mongodbConfig.getConnectionsPerHost());// 与目标数据库可以建立的最大链接数
            buide.connectTimeout(mongodbConfig.getConnectTimeout());// 与数据库建立链接的超时时间
            buide.maxWaitTime(mongodbConfig.getMaxWaitTime());// 一个线程成功获取到一个可用数据库之前的最大等待时间
            buide.threadsAllowedToBlockForConnectionMultiplier(mongodbConfig.getThreadsAllowedToBlockForConnectionMultiplier());
            buide.maxConnectionIdleTime(0);
            buide.maxConnectionLifeTime(0);
            buide.socketTimeout(0);
            buide.socketKeepAlive(true);
            MongoClientOptions myOptions = buide.build();

            List<ServerAddress> serverAddressList = new ArrayList<>();
            for (MongodbConfig.MongodbHost host : mongodbConfig.getHosts()) {
                ServerAddress serverAddress = new ServerAddress(host.getHost(), host.getPort());
                serverAddressList.add(serverAddress);
            }

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential(mongodbConfig.getUserName(), mongodbConfig.getDbName(), mongodbConfig.getPassword().toCharArray());
            List<MongoCredential> credentials = new ArrayList<>();
            credentials.add(credential);

            mongoClient = new MongoClient(serverAddressList, credentials, myOptions);
            dbName = mongodbConfig.getDbName();
        } catch (Exception e) {
            LOG.error("获取mongoClient失败.", e);
        }
    }


    private DB getDB() {
        return mongoClient.getDB(dbName);
    }

    @Override
    public void insert(String tableName, DBObject dbObject, String pk) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("insert, tableName is null.");
            return;
        }

        if (StringUtils.isBlank(pk)) {
            LOG.error("无法执行insert, pk is null.");
            return;
        }

        if (dbObject == null) {
            LOG.error("dbObject is null. 无法执行insert.");
            return;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            if (StringUtils.isNotBlank(pk)) {
                dbObject.put(PK, pk);
            }

            collection.insert(dbObject);
        } catch (DuplicateKeyException e) {
            LOG.warn(String.format("重复主键。table=%s, pk=%s", tableName, pk));
        } catch (Exception e) {
            LOG.error("执行insert失败.", e);
        }
    }

    @Override
    public void insertWithException(String tableName, DBObject dbObject, String pk) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("insert, tableName is null.");
            return;
        }

        if (StringUtils.isBlank(pk)) {
            LOG.error("无法执行insert, pk is null.");
            return;
        }

        if (dbObject == null) {
            LOG.error("dbObject is null. 无法执行insert.");
            return;
        }

        DB db = getDB();
        DBCollection collection = db.getCollection(tableName);

        if (StringUtils.isNotBlank(pk)) {
            dbObject.put(PK, pk);
        }

        collection.insert(dbObject);
    }


    /**
     * 批量插入
     *
     * @param tableName
     * @param dbObjectMap
     */
    @Override
    public void insertBatch(String tableName, Map<String, DBObject> dbObjectMap) {
        if (dbObjectMap == null || dbObjectMap.size() == 0) {
            LOG.error("dbObjectMap is null. 无法执行insertBatch.");
            return;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            List<DBObject> dbObjects = new ArrayList<>();
            for (String pk : dbObjectMap.keySet()) {
                DBObject dbObject = dbObjectMap.get(pk);
                dbObject.put(PK, pk);
                dbObjects.add(dbObject);
            }

            collection.insert(dbObjects);
        } catch (Exception e) {
            LOG.error("执行insertBatch失败.", e);
        }
    }

    @Override
    public DBObject selectByPk(String tableName, String pk) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectOneByPk, tableName is null.");
            return null;
        }

        if (StringUtils.isBlank(pk)) {
            LOG.error("无法执行selectOneByPk, pk is null.");
            return null;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);
            DBObject cause = new BasicDBObject();
            cause.put(PK, pk);

            return collection.findOne(cause);
        } catch (Exception e) {
            LOG.error("执行selectByPk失败.", e);
        }

        return null;
    }

    @Override
    public DBObject selectOne(String tableName, DBObject cause) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectOne, tableName is null.");
            return null;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            if (cause == null || cause.keySet() == null || cause.keySet().size() == 0) {
                return collection.findOne();
            } else {
                return collection.findOne(cause);
            }
        } catch (Exception e) {
            LOG.error("执行selectOne失败.", e);
        }

        return null;
    }

    @Override
    public DBObject selectOne(String tableName, DBObject cause, DBObject param) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectOne, tableName is null.");
            return null;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            if (cause == null || cause.keySet() == null || cause.keySet().size() == 0) {
                return collection.findOne();
            } else {
                return collection.findOne(cause, param);
            }
        } catch (Exception e) {
            LOG.error("执行selectOne失败.", e);
        }

        return null;
    }

    @Override
    public DBObject selectOneByStoreTime(String tableName, DBObject cause) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectOneByStoreTime, tableName is null.");
            return null;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);
            DBCursor cursor;
            if (cause == null || cause.keySet() == null || cause.keySet().size() == 0) {
                cursor = collection.find().limit(1).sort(new BasicDBObject("storeTime", Integer.valueOf(-1)));
            } else {
                cursor = collection.find(cause).limit(1).sort(new BasicDBObject("storeTime", Integer.valueOf(-1)));
            }
            DBObject obj = null;
            while (cursor.hasNext()) {
                obj = cursor.next();
            }
            cursor.close();
            return obj;
        } catch (Exception e) {
            LOG.error("执行selectByBasic失败.", e);
        }

        return null;
    }

    @Override
    public List<DBObject> selectAllBySort(String tableName, DBObject cause, DBObject sortCause) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectAllByParam, tableName is null.");
            return null;
        }

        if (sortCause == null) {
            sortCause = new BasicDBObject();
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            if (cause == null || cause.keySet() == null || cause.keySet().size() == 0) {
                DBCursor cursor = collection.find().sort(sortCause);
                List<DBObject> objects = new ArrayList<>();
                while (cursor.hasNext()) {
                    DBObject tempObj = cursor.next();
                    objects.add(tempObj);
                }
                return objects;
            } else {
                DBCursor cursor = collection.find(cause).sort(sortCause);
                List<DBObject> objects = new ArrayList<>();
                while (cursor.hasNext()) {
                    DBObject tempObj = cursor.next();
                    objects.add(tempObj);
                }
                return objects;
            }

        } catch (Exception e) {
            LOG.error("执行selectAll失败.", e);
        }

        return null;
    }


    @Override
    public List<DBObject> selectAll(String tableName, DBObject cause) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectAll, tableName is null.");
            return null;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            if (cause == null || cause.keySet() == null || cause.keySet().size() == 0) {
                DBCursor cursor = collection.find();
                List<DBObject> objects = new ArrayList<>();
                while (cursor.hasNext()) {
                    DBObject tempObj = cursor.next();
                    objects.add(tempObj);
                }
                return objects;
            } else {
                DBCursor cursor = collection.find(cause);
                List<DBObject> objects = new ArrayList<>();
                while (cursor.hasNext()) {
                    DBObject tempObj = cursor.next();
                    objects.add(tempObj);
                }
                return objects;
            }

        } catch (Exception e) {
            LOG.error("执行selectAll失败.", e);
        }

        return null;
    }

    @Override
    public Integer getResultCount(String tableName, DBObject cause) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行getResultCount, tableName is null.");
            return 0;
        }
        DB db = getDB();
        DBCollection collection = db.getCollection(tableName);
        int count = 0;
        //case
        if (cause != null && cause.keySet() != null && cause.keySet().size() != 0) {
            count = collection.find(cause).count();
        } else {
            count = collection.find().count();
        }
        return count;
    }


    private void update(String tableName, DBObject cause, DBObject allColumns) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行update, tableName is null.");
            return;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            //case
            if (cause != null && cause.keySet() != null && cause.keySet().size() != 0) {
                //changeColumns
                if (allColumns != null && allColumns.keySet() != null && allColumns.keySet().size() != 0) {
                    //false: 只更新,若没有找到记录不插入
                    //true: 若有多条都执行更新操作
                    collection.update(cause, allColumns, false, true);
                } else {
                    LOG.error("无法执行update, changeColumns is null.");
                }
            } else {
                //changeColumns
                if (allColumns != null && allColumns.keySet() != null && allColumns.keySet().size() != 0) {
                    //false: 只更新,若没有找到记录不插入
                    //true: 若有多条都执行更新操作
                    collection.update(new BasicDBObject(), allColumns, false, true);
                } else {
                    LOG.error("无法执行update, changeColumns is null and cause is null.");
                }
            }
        } catch (Exception e) {
            LOG.error("执行update失败.", e);
        }
    }

    @Override
    public void updateByPk(String tableName, String pk, DBObject allColumns) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行updateByPk, tableName is null.");
            return;
        }

        if (StringUtils.isBlank(pk)) {
            LOG.error("无法执行updateByPk, pk is null.");
            return;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);
            DBObject cause = new BasicDBObject();
            cause.put(PK, pk);

            if (allColumns != null && allColumns.keySet() != null && allColumns.keySet().size() != 0) {
                //false: 只更新,若没有找到记录不插入
                //true: 若有多条都执行更新操作
                collection.update(cause, allColumns, false, true);
            } else {
                LOG.error("无法执行updateByPk, changeColumns is null.");
            }
        } catch (Exception e) {
            LOG.error("执行updateByPk失败.", e);
        }
    }

    @Override
    public void deleteByPk(String tableName, String pk) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行deleteByPk, tableName is null.");
            return;
        }

        if (StringUtils.isBlank(pk)) {
            LOG.error("无法执行deleteByPk, pk is null.");
            return;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);
            DBObject cause = new BasicDBObject();
            cause.put(PK, pk);

            collection.remove(cause);
        } catch (Exception e) {
            LOG.error("执行updateByPk失败.", e);
        }
    }

    @Override
    public void delete(String tableName, DBObject cause) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行delete, tableName is null.");
            return;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);
            if (cause == null || cause.keySet() == null || cause.keySet().size() == 0) {
                LOG.error("删除条件不能为空！执行删除失败");
                return;
            }
            collection.remove(cause);
        } catch (Exception e) {
            LOG.error("执行delete失败.", e);
        }
    }

    @Override
    public DBObject selectOneBySort(String tableName, DBObject cause, DBObject sortCause) {

        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectOneBySort, tableName is null.");
            return null;
        }
        if (sortCause == null) {

        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);
            DBCursor cursor;
            if (cause == null || cause.keySet() == null || cause.keySet().size() == 0) {
                cursor = collection.find().limit(1).sort(sortCause);
            } else {
                cursor = collection.find(cause).limit(1).sort(sortCause);
            }
            DBObject obj = null;
            while (cursor.hasNext()) {
                obj = cursor.next();
            }
            cursor.close();
            return obj;
        } catch (Exception e) {
            LOG.error("执行selectOneBySort失败.", e);
        }

        return null;

    }

    @Override
    public List selectAllByDistinct(String tableName, DBObject cause, String param) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectAllByDistinct, tableName is null.");
            return null;
        }
        if (StringUtils.isBlank(param)) {
            LOG.error("无法执行selectAllByDistinct, distinct对象 is null.");
            return null;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            if (cause == null || cause.keySet() == null || cause.keySet().size() == 0)  {
                return collection.distinct(param);
            } else {
                return collection.distinct(param, cause);
            }

        } catch (Exception e) {
            LOG.error("执行selectAllByDistinct失败.", e);
        }

        return null;
    }

    /**
     * 执行聚合
     *
     * @param tableName
     * @param pipeline
     * @return
     */
    @Override
    public List<DBObject> executeAggregate(String tableName, List<DBObject> pipeline) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行executeAggregate, tableName is null.");
            return null;
        }

        if (pipeline == null || pipeline.size() == 0) {
            LOG.error("无法执行executeAggregate, pipeline is null.");
            return null;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);
            AggregationOutput aggregationOutput = collection.aggregate(pipeline);

            Iterable<DBObject> iterable = aggregationOutput.results();
            if (iterable == null) {
                LOG.warn("从mongodb中获取到的AggregationOutput为空。");
                return null;
            }

            List<DBObject> result = new ArrayList<>();
            for (DBObject dbObject : iterable) {
                result.add(dbObject);
            }

            return result;
        } catch (Exception e) {
            LOG.error("执行executeAggregate失败.", e);
            return null;
        }
    }

    @Override
    public List<DBObject> selectByLimitAndsort(String tableName, DBObject cause, int limitSize, DBObject sortCause) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("tableName is null");
            return null;
        }
        if (limitSize == 0) {
            LOG.error("limitSize is null");
            return null;
        }
        try {
            DB e = this.getDB();
            DBCollection collection = e.getCollection(tableName);
            DBCursor cursor;
            if (cause != null && cause.keySet() != null && cause.keySet().size() != 0) {
                cursor = collection.find(cause).limit(limitSize).sort(sortCause);
            } else {
                cursor = collection.find().limit(limitSize).sort(sortCause);
            }

            List<DBObject> objects = new ArrayList<>();

            while (cursor.hasNext()) {
                DBObject tempObj = cursor.next();
                objects.add(tempObj);
            }

            cursor.close();
            return objects;
        } catch (Exception var11) {
            LOG.error("执行selectByLimitAndsort失败.", var11);
        }
        return null;
    }

    @Override
    public Set<String> getAllTables() {
        try {
            DB db = getDB();
            return db.getCollectionNames();
        } catch (Exception e) {
            LOG.error("执行getAllTables失败.", e);
            return null;
        }
    }

    @Override
    public List<DBObject> selectAll(String tableName, DBObject cause, DBObject param) {
        if (StringUtils.isBlank(tableName)) {
            LOG.error("无法执行selectAll, tableName is null.");
            return null;
        }

        try {
            DB db = getDB();
            DBCollection collection = db.getCollection(tableName);

            if (cause == null || cause.keySet() ==null || cause.keySet().size() == 0) {
                DBCursor cursor = collection.find(null, param);
                List<DBObject> objects = new ArrayList<>();
                while (cursor.hasNext()) {
                    DBObject tempObj = cursor.next();
                    objects.add(tempObj);
                }
                return objects;
            } else {
                DBCursor cursor = collection.find(cause, param);
                List<DBObject> objects = new ArrayList<>();
                while (cursor.hasNext()) {
                    DBObject tempObj = cursor.next();
                    objects.add(tempObj);
                }
                return objects;
            }

        } catch (Exception e) {
            LOG.error("执行selectAll失败.", e);
        }

        return null;


    }
}
