package com.zl.checkapi.elasticsearch;

import org.elasticsearch.client.Client;

/**
 * Created by zhouliang on 2017/9/19.
 */
public class EStest {

    public static void main(String[] args){

        Client client = ElasticSearchClient.client();
        System.out.println(client.prepareIndex());
    }
}
