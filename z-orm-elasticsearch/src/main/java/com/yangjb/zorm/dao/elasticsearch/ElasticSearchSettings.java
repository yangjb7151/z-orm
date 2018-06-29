package com.yangjb.zorm.dao.elasticsearch;

import com.yangjb.zorm.dao.DaoSettings;
import lombok.Data;

/**
 * es 通用client级别设置对象
 *
 * @Author zhoutao
 * @Date 2017/5/16
 */
@Data
public final class ElasticSearchSettings implements DaoSettings {
    /**
     * 服务端node节点地址列表
     * 1.1.1.1:9300,1.1.1.9:9300
     */
    private String serverAddressList;
    /**
     * 集群名称
     */
    private String clusterName = "elasticsearch";
}
