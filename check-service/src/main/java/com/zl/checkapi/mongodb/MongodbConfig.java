package com.zl.checkapi.mongodb;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * desc
 *
 * @author shijiankai
 */
public class MongodbConfig {
    private String dbName;
    private String userName;
    private String password;

    private String hosts;
    //与目标数据库可以建立的最大链接数
    private String connectionsPerHost;
    //等待连接线程
    private String threadsAllowedToBlockForConnectionMultiplier;
    //与数据库建立链接的超时时间
    private String connectTimeout;
    //一个线程成功获取到一个可用数据库之前的最大等待时间
    private String maxWaitTime;

    public Integer getThreadsAllowedToBlockForConnectionMultiplier() {
        return Integer.parseInt(threadsAllowedToBlockForConnectionMultiplier);
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(String threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MongodbHost> getHosts() {
        String temps[] = hosts.split(",");
        List<MongodbHost> hostList = new ArrayList<>();
        for (String temp : temps) {
            temp = temp.trim();
            String htemp[] = temp.split(":");
            MongodbHost mongodbHost = new MongodbHost();
            mongodbHost.setHost(htemp[0]);
            mongodbHost.setPort(Integer.parseInt(htemp[1]));
            hostList.add(mongodbHost);
        }

        return hostList;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public int getConnectionsPerHost() {
        return Integer.parseInt(connectionsPerHost);
    }

    public void setConnectionsPerHost(String connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public int getConnectTimeout() {
        return Integer.parseInt(connectTimeout);
    }

    public void setConnectTimeout(String connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getMaxWaitTime() {
        return Integer.parseInt(maxWaitTime);
    }

    public void setMaxWaitTime(String maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public static class MongodbHost {
        private String host;
        private int port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

    }

    public static void main(String[] args) {
        List<MongodbHost> lists = new ArrayList<>();
        MongodbHost host = new MongodbHost();
        host.setHost("192.168.56.106");
        host.setPort(27017);
        lists.add(host);
        System.out.println(JSON.toJSONString(lists));

    }

}
