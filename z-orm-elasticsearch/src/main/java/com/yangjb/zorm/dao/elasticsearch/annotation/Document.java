package com.yangjb.zorm.dao.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * 标注entity对应的es文档描述
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Document {
    //对应的es的索引名称
    String indexName();

    //对应的es索引的类型名称
    String typeName();
}
