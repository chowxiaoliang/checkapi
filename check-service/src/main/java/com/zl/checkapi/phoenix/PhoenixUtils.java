package com.zl.checkapi.phoenix;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.dubbo.container.spring.SpringContainer;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.MD5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Phoenix的操作类
 */
public class PhoenixUtils implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(PhoenixUtils.class);
    public static final String PK = "PK";
    private static String DRIVER = "org.apache.phoenix.jdbc.PhoenixDriver";
    private static PhoenixUtils phoenixUtils;
    private static String jdbcUrl;
    private static DruidDataSource dataSource;
    static {
        ConfigMap.INSTANCE.init();
        jdbcUrl = ConfigMap.INSTANCE.getValue("phoenix.jdbcUrl");
    }

    /**
     * jdbc形如 : jdbc:phoenix:hadoop1,hadoop2,hadoop3:2181
     */
    public static PhoenixUtils instance() {
        if (phoenixUtils == null) {
            synchronized (PhoenixUtils.class) {
                if (phoenixUtils == null) {
                    phoenixUtils = new PhoenixUtils();
                }
            }
        }

        return phoenixUtils;
    }

    private PhoenixUtils( ) {

    }

    public DataSource getDataSource() {
        if(dataSource == null){
            dataSource = SpringContainer.getContext().getBean("phoenixDataSource", DruidDataSource.class);
        }
        return dataSource;
    }
//    public Connection getConnection() throws SQLException, ClassNotFoundException {
//        long begin = System.currentTimeMillis();
//        DataSource dataSource = getDataSource();
//        Connection conn = dataSource.getConnection();
//        long end = System.currentTimeMillis();
//        LOG.debug(String.format("获取phoenix连接花费:%s ms", end - begin));
//        return conn;
//    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        long begin = System.currentTimeMillis();

        Connection conn = null;

        Class.forName(DRIVER);
        if(StringUtils.isBlank(jdbcUrl)){
            ConfigMap.INSTANCE.init();
            jdbcUrl = ConfigMap.INSTANCE.getValue("phoenix.jdbcUrl");
        }
        conn = DriverManager.getConnection(jdbcUrl);

        conn.setAutoCommit(true);

        long end = System.currentTimeMillis();
        LOG.debug(String.format("获取phoenix连接花费:%s ms", end - begin));

        return conn;
    }

    /**
     * 查询列
     * @param tableName      表名
     * @param searchColumns  select的列(逗号分隔)
     * @param partnerId      PRMI_PARTNERID
     * @param appId          PRMI_APPID
     * @param beginTime      PRMI_OCCURTIME的区间开始时间
     * @param endTime        PRMI_OCCURTIME的区间结束时间
     * @param key PRMI_MOBILE或者PRMI_CERTNO(11位是PRMI_MOBILE，15或者18位是PRMI_CERTNO)
     * @param orderField     排序的列
     * @param orderType      排序类型(ASC, DESC)
     * @param limit          分页开始位置
     * @param offset         偏移量
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Map<String, String>> select(String tableName, List<String> searchColumns, String partnerId, String appId,
                                            long beginTime, long endTime, String key,String field, String orderField, OrderType orderType, int limit, int offset) throws SQLException, ClassNotFoundException {
        //参数校验
        if (StringUtils.isBlank(tableName)) {
            LOG.warn("无法执行select, tableName为空.");
            return null;
        }

        if (searchColumns == null || searchColumns.isEmpty()) {
            LOG.warn("无法执行select, searchColumns为空.");
            return null;
        }

        if (StringUtils.isBlank(partnerId)) {
            LOG.warn("无法执行select, partnerId为空.");
            return null;
        }

        if (StringUtils.isBlank(appId)) {
            LOG.warn("无法执行select, appId为空.");
            return null;
        }

        if (beginTime > endTime) {
            LOG.warn("无法执行select, beginTime大于endTime.");
            return null;
        }

        if (StringUtils.isNotBlank(orderField) && orderType == null) {
            LOG.warn("无法执行select, orderType为空.");
            return null;
        }
        String betweenAnd = " PRMI_OCCURTIME between ? and ?";
        String order = "";
        if (StringUtils.isNotBlank(orderField)) {
            order = " order by " + orderField;
            if (orderType.equals(OrderType.ASC)) {
                order += " asc";
            }
            if (orderType.equals(OrderType.DESC)) {
                order += " desc";
            }
        }
        String limitSql = "";
        if (limit > 0) {
            limitSql = " limit ?";
            if (offset > 0) {
                limitSql += " offset ?";
            }
        }

        StringBuffer sql = new StringBuffer();

        String tempColum = "";
        for (String column : searchColumns) {
            tempColum = tempColum + "," + column;
        }
        tempColum = tempColum.substring(1);

        sql.append(String.format("select %s from %s where PRMI_PARTNERID = ? and PRMI_APPID = ? and", tempColum, tableName, partnerId, appId));
        sql.append(betweenAnd);
        if(StringUtils.isNotBlank(key)){
            if ("PRMI_MOBILE".equals(field)) {
                sql.append(" and PRMI_MOBILE = ? ");
            } else if ("PRMI_CERTNO".equals(field)) {
                sql.append(" and PRMI_CERTNO = ? ");
            }
        }
        sql.append(order);
        sql.append(limitSql);

        Connection conn = null;
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            if (conn == null) {
                LOG.warn("获取到的phoenix连接为null. 无法执行sql操作.");
                return null;
            }
            stmt = conn.prepareStatement(sql.toString());
            stmt.setString(1,partnerId);
            stmt.setString(2,appId);
            stmt.setLong(3,beginTime);
            stmt.setLong(4,endTime);
            if(StringUtils.isNotBlank(key)){
                stmt.setString(5,key);
                if (limit > 0) {
                    stmt.setInt(6,limit);
                    if (offset > 0) {
                        stmt.setInt(7,offset);
                    }
                }
            }else{
                if (limit > 0) {
                    stmt.setInt(5,limit);
                    if (offset > 0) {
                        stmt.setInt(6,offset);
                    }
                }
            }
            LOG.debug("begin to execut sql. sql = " + sql.toString());
            rs = stmt.executeQuery();
            LOG.debug("finish to execut sql. sql = " + sql.toString());

            return resultSet2List(rs, searchColumns);

        } finally {
            close(rs, stmt, conn);
        }
    }



    /**
     * 查询条数
     * @param tableName      表名
     * @param partnerId      PRMI_PARTNERID
     * @param appId          PRMI_APPID
     * @param beginTime      PRMI_OCCURTIME的区间开始时间
     * @param endTime        PRMI_OCCURTIME的区间结束时间
     * @param key PRMI_MOBILE或者PRMI_CERTNO(11位是PRMI_MOBILE，15或者18位是PRMI_CERTNO)
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public long selectCount(String tableName, String partnerId, String appId,
                            long beginTime, long endTime, String key,String field) throws SQLException, ClassNotFoundException {
        //参数校验
        if (StringUtils.isBlank(tableName)) {
            LOG.warn("无法执行select, tableName为空.");
            return -1;
        }

        if (StringUtils.isBlank(partnerId)) {
            LOG.warn("无法执行select, partnerId为空.");
            return -1;
        }

        if (StringUtils.isBlank(appId)) {
            LOG.warn("无法执行select, appId为空.");
            return -1;
        }

        if (beginTime > endTime) {
            LOG.warn("无法执行select, beginTime大于endTime.");
            return -1;
        }

        String betweenAnd = " PRMI_OCCURTIME between ? and ?";

        StringBuffer sql = new StringBuffer("");
        sql.append(String.format("select count(1) count from %s where PRMI_PARTNERID = ? and PRMI_APPID = ? and", tableName, partnerId, appId));
        sql.append(betweenAnd);
        if(StringUtils.isNotBlank(key)){
            if ("PRMI_MOBILE".equals(field)) {
                sql.append(" and PRMI_MOBILE = ? ");
            } else if ("PRMI_CERTNO".equals(field)) {
                sql.append(" and PRMI_CERTNO = ? ");
            }
        }
        LOG.info("sql = " + sql.toString());
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            if (conn == null) {
                LOG.warn("获取到的phoenix连接为null. 无法执行sql操作.");
                return -1;
            }
            stmt = conn.prepareStatement(sql.toString());
            stmt.setString(1,partnerId);
            stmt.setString(2,appId);
            stmt.setLong(3,beginTime);
            stmt.setLong(4,endTime);
            if(StringUtils.isNotBlank(key)){
                stmt.setString(5,key);
            }
            LOG.debug("begin to execut sql. sql = " + sql);
            rs = stmt.executeQuery(sql.toString());
            LOG.debug("finish to execut sql. sql = " + sql);
            long count = 0;
            if (rs.next()) {
                count = rs.getLong(1);
            }
            return count;

        } finally {
            close(rs, stmt, conn);
        }
    }

    /**
     * 插入更新
     * @param tableName
     * @param rowkey
     * @param data
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void inserOrUpdate(String tableName, String rowkey, Map<String, Object> data) throws SQLException, ClassNotFoundException {
        //参数校验
        if (StringUtils.isBlank(tableName)) {
            LOG.warn("无法执行inserOrUpdate, tableName为空.");
            return;
        }

        if (StringUtils.isBlank(rowkey)) {
            LOG.warn("无法执行inserOrUpdate, rowkey为空.");
            return;
        }

        if (data == null || data.isEmpty()) {
            LOG.warn("无法执行inserOrUpdate, data为空.");
            return;
        }

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = getConnection();
            if (conn == null) {
                LOG.warn("获取到的phoenix连接为null. 无法执行sql操作.");
                return;
            }

            stmt = conn.createStatement();
            StringBuffer tempColumn = new StringBuffer();
            StringBuffer tempValue = new StringBuffer();
            mapToString(data, tempColumn, tempValue);

            String sql = String.format("upsert into %s (PK, %s) values ('%s', %s)", tableName, tempColumn, rowkey, tempValue);
            LOG.debug("begin to execut sql. sql = " + sql);
            int result = stmt.executeUpdate(sql);

        } finally {
            close(null, stmt, conn);
        }
    }

    /**
     * 批量插入更新
     * @param tableName
     * @param datas
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void inserOrUpdateBatch(String tableName, List<Map<String, Object>> datas) throws SQLException, ClassNotFoundException {
        //参数校验
        if (StringUtils.isBlank(tableName)) {
            LOG.warn("无法执行inserOrUpdate, tableName为空.");
            return;
        }

        if (datas == null || datas.size() == 0) {
            LOG.warn("无法执行inserOrUpdateBatch, datas为空.");
            return;
        }

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            if (conn == null) {
                LOG.warn("获取到的phoenix连接为null. 无法执行sql操作.");
                return;
            }

            stmt = conn.createStatement();
            int i = 0;
            for (Map<String, Object> data : datas) {

                StringBuffer tempColumn = new StringBuffer();
                StringBuffer tempValue = new StringBuffer();
                mapToString(data, tempColumn, tempValue);

                String rowkey = data.get(PK).toString();
                if (StringUtils.isBlank(rowkey)) {
                    LOG.warn("无法执行inserOrUpdate, rowkey为空.");
                    return;
                }

                String sql = String.format("upsert into %s (%s) values (%s)", tableName, tempColumn.toString(), tempValue.toString());
                LOG.debug("begin to execut sql. sql = " + sql);

                //批量提交
                i++;
                stmt.addBatch(sql);
                if (i % 100 == 0) {
                    stmt.executeBatch();
                    conn.commit();
                }

                LOG.debug(String.format("finish to execut sql. table=%s, rowkey=%s", tableName, rowkey));
            }

            //统一提交（确保没有遗漏）
            stmt.executeBatch();
            conn.commit();

        } finally {
            close(null, stmt, conn);
        }
    }

    /**
     * 删除记录
     *
     * @param tableName
     * @param cause
     */
    public void delete(String tableName, Map<String, String> cause) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            if (conn == null) {
                LOG.debug("获取到的phoenix连接为null. 无法执行sql操作.");
                return;
            }

            stmt = conn.createStatement();
            String tempCause = "";
            for (Map.Entry<String, String> entry : cause.entrySet()) {
                String key = entry.getKey();
                String value = StringUtils.trimToEmpty(entry.getValue());
                tempCause = tempCause + String.format(" and %s='%s' ", key, value);
            }
            tempCause = tempCause.substring(4);

            String sql = String.format("delete from %s where %s", tableName, tempCause);
            LOG.debug("begin to execut sql. sql = " + sql);
            int result = stmt.executeUpdate(sql);
//            boolean result = stmt.execute(sql);
            LOG.debug(String.format("finish to execut sql. table=%s, result=%s", tableName, result));

        } finally {
            close(null, stmt, conn);
        }

    }

    private List<Map<String, String>> resultSet2List(ResultSet rs, List<String> searchColumns) throws SQLException {
        List<Map<String, String>> result = new ArrayList<>();
        while (rs.next()) {
            Map<String, String> row = new HashMap<>();
            for (String column : searchColumns) {
                String value = rs.getString(column);
                row.put(column, value);
            }
            result.add(row);
        }
        return result;
    }

    private void close(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
        }

        if (stmt != null) {
            stmt.close();
        }

        if (conn != null) {
            conn.close();
        }
    }

    public static void mapToString(Map<String, Object> data, StringBuffer sbColumn, StringBuffer sbValue) {

        String tempColumn = "";
        String tempValue = "";
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            tempColumn = tempColumn + "," + key;
            if(value instanceof Long || value instanceof Double || value instanceof Integer){
                tempValue = tempValue + "," + value + "";
            }else{
                tempValue = tempValue + ",'" + value + "'";
            }

            //tempValue = tempValue + ",'" + value + "'";
        }
        sbColumn.append(tempColumn.substring(1));
        sbValue.append(tempValue.substring(1));

    }

    public static String md5HashRowKey(String key){
        return MD5Hash.getMD5AsHex(key.getBytes()).substring(0, 8) + key;
    }

    public static void main(String[] args) {
        PhoenixUtils phoenix = PhoenixUtils.instance();
        List<String> column = new ArrayList<>();
        column.add("PK");
        column.add("PRMI_OCCURTIME");
        long startTime = 1523030400000L;
        long endTime = 1523635199999L;
        try {
            List<Map<String,String>> list = phoenix.select("PHN_T_RISK_EVENT_NEW",column,"demo","credit",startTime,endTime,null,null,"PRMI_OCCURTIME",OrderType.DESC,100,500);
            System.out.println("-----------------------------"+list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
