package com.yangjb.zorm.dao.elasticsearch;

import com.google.common.collect.Maps;
import com.yangjb.zorm.exception.DaoException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.HashMap;

import static com.yangjb.zorm.constant.SymbolConstant.COLON;
import static com.yangjb.zorm.constant.SymbolConstant.COMMA;

/**
 * @Author zhoutao
 * @Date 2017/5/17
 */
public final class ElasticSearchClientFactory {
    static final ElasticSearchClientFactory INSTANCE = new ElasticSearchClientFactory();
    private HashMap<ElasticSearchSettings, TransportClient> transportClientMap = Maps.newHashMap();

    /**
     * 客户端的获取发生在项目运行中
     *
     * @param elasticSearchSettings
     */
    TransportClient getClient(ElasticSearchSettings elasticSearchSettings) {
        return transportClientMap.get(elasticSearchSettings);
    }

    /**
     * 客户端的创建工作发生在项目启动过程
     *
     * @param elasticSearchSettings
     */
    synchronized void setClient(ElasticSearchSettings elasticSearchSettings) {
        if (this.getClient(elasticSearchSettings) != null) {
            return;
        }

        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", elasticSearchSettings.getClusterName())
                    .put("client.transport.sniff", true)
                    .build();

            System.setProperty("es.set.netty.runtime.available.processors", "false");
            TransportClient transportClient = new PreBuiltTransportClient(settings);

            String[] serverAddrArr = elasticSearchSettings.getServerAddressList().split(COMMA);
            for (String serverAddr : serverAddrArr) {
                String[] ipAndPort = serverAddr.split(COLON);
                transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAndPort[0]), Integer.parseInt(ipAndPort[1])));
            }
            transportClientMap.put(elasticSearchSettings, transportClient);
        } catch (Exception e) {
            throw new DaoException("无法生产Client[" + elasticSearchSettings + "]", e);
        }
    }
}
