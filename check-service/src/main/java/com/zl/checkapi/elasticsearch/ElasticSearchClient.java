package com.zl.checkapi.elasticsearch;

import com.google.common.net.HostAndPort;
import com.zl.checkapi.util.ConfigUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhouliang on 2017/9/19.
 */
public class ElasticSearchClient {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchClient.class);

    private static RestHighLevelClient client = null;

    private static RestHighLevelClient getTransportClient(){
        RestHighLevelClient client = null;
        try{
            String nodeInfos = ConfigUtil.getValueByKey("new.es.cluster.nodes");
            LOG.info("elasticsearch 配置参数信息 =>>  " + nodeInfos);
            if (nodeInfos == null || nodeInfos.indexOf(":") < 0) {
                throw new IllegalArgumentException("elasticsearch connection config  [cluster.nodes] error");
            } else {
                String[] nodeInfosSplit = nodeInfos.split(",");
                HttpHost[] httpHosts = new HttpHost[nodeInfosSplit.length];
                for (int i = 0 ; i < nodeInfosSplit.length ; i ++ ){
                    String nodeInfo = nodeInfosSplit[i];
                    HostAndPort hap = HostAndPort.fromString(nodeInfo);
                    HttpHost httpHost = new HttpHost(hap.getHostText(), hap.getPort());
                    httpHosts[i] = httpHost;
                }
                RestClientBuilder builder = RestClient.builder(httpHosts);
                //1分钟
                builder.setMaxRetryTimeoutMillis(60000);
                client = new RestHighLevelClient(builder);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return client;
    }

    public static RestHighLevelClient client() {
        if (client == null) {
            synchronized (ElasticSearchClient.class) {
                if(client == null) {
                    client = getTransportClient();
                }
            }
        }
        return client;
    }
}
