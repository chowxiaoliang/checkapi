package com.zl.checkapi.elasticsearch;

import com.zl.checkapi.util.ConfigUtil;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhouliang on 2017/9/19.
 */
public class ElasticSearchClient {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchClient.class);

    private static Client client = null;

    private static Client getTransportClient(){
        TransportClient client = null;
        try{
            org.elasticsearch.common.settings.Settings settings = org.elasticsearch.common.settings.Settings.
                    builder().put("cluster.name", ConfigUtil.getValueByKey("cluster.name"))
                    .put("client.transport.sniff", ConfigUtil.getValueByKey("es.client.transport.sniff"))
                    .put("cluster.nodes", ConfigUtil.getValueByKey("cluster.nodes")).build();

            String nodeInfos = ConfigUtil.getValueByKey("cluster.nodes");
            if(nodeInfos==null || nodeInfos.indexOf(":")<0){
                LOG.error("配置信息读取失败");
            }else {
                client = TransportClient.builder().settings(settings).build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return client;
    }

    public static Client client(){
        if(client == null){
            synchronized (ElasticSearchClient.class){
                client = getTransportClient();
            }
        }
        return client;
    }
}
