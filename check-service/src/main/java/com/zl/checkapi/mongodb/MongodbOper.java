package com.zl.checkapi.mongodb;


import com.mongodb.DBObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * desc
 *
 * @author shijiankai
 */
public interface MongodbOper {

    void insert(String tableName, DBObject dbObject, String pk);

    void insertWithException(String tableName, DBObject dbObject, String pk);

    /**
     * 批量插入
     *
     * @param tableName
     * @param dbObjectMap
     */
    void insertBatch(String tableName, Map<String, DBObject> dbObjectMap);

    /**
     * 根据主键查询记录
     *
     * @param tableName
     * @param pk
     * @return
     */
    DBObject selectByPk(String tableName, String pk);

    /**
     * 查询一条记录
     *
     * @param tableName
     * @param cause
     * @return
     */
    DBObject selectOne(String tableName, DBObject cause);

    /**
     * 查询一条记录
     *
     * @param tableName
     * @param cause
     * @param
     * @return
     */
    DBObject selectOne(String tableName, DBObject cause, DBObject param);

    /**
     * 根据storeTime取最新的一条数据
     *
     * @param tableName
     * @param cause
     * @return
     */
    DBObject selectOneByStoreTime(String tableName, DBObject cause);


    /**
     * 根据排序条件返回所有数据
     *
     * @param tableName
     * @param cause
     * @param sortCause
     * @return
     */
    List<DBObject> selectAllBySort(String tableName, DBObject cause, DBObject sortCause);

    /**
     * 查询所有数据
     *
     * @param tableName
     * @param cause
     * @return
     */
    List<DBObject> selectAll(String tableName, DBObject cause);


    /**
     * 根据条件查询结果条数
     *
     * @param tableName
     * @param cause
     * @return
     */
    Integer getResultCount(String tableName, DBObject cause);

//    void update(String tableName, DBObject cause, DBObject allColumns);

    /**
     * 根据主键更新
     *
     * @param tableName
     * @param pk
     * @param allColumns
     */
    void updateByPk(String tableName, String pk, DBObject allColumns);

    /**
     * 根据主键删除
     *
     * @param tableName
     * @param pk
     */
    void deleteByPk(String tableName, String pk);

    /**
     * 删除所有符合查询条件的数据
     *
     * @param tableName
     * @param cause
     */
    void delete(String tableName, DBObject cause);


    /**
     * 根据排序结果获取一条数据
     *
     * @param tableName
     * @param cause
     * @param sortCause
     * @return
     */
    DBObject selectOneBySort(String tableName, DBObject cause, DBObject sortCause);


    /**
     * 去重查询
     *
     * @param tableName
     * @param cause
     * @param param
     * @return
     */
    List selectAllByDistinct(String tableName, DBObject cause, String param);

    /**
     * 执行聚合
     * @param tableName
     * @param pipeline
     * @return
     */
    List<DBObject> executeAggregate(String tableName, List<DBObject> pipeline);


    /**
     * 执行排序并限制返回条数
     * @param tableName
     * @param cause
     * @param limitSize
     * @param sortCause
     * @return
     */
    List<DBObject> selectByLimitAndsort(String tableName, DBObject cause, int limitSize, DBObject sortCause);

    /**
     * 获取库中的所有表名
     * @return
     */
    Set<String> getAllTables();

    /**
     * 返回指定字段
     * @param tableName
     * @param cause
     * @param param
     * @return
     */
    List<DBObject> selectAll(String tableName, DBObject cause, DBObject param);



}
