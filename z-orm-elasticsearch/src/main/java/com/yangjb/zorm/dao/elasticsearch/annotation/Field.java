package com.yangjb.zorm.dao.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * 标注对应的es字段
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Field {

    //字段的数据类型
    FieldType type() default FieldType.Auto;

    //字段是否应当被当成全文来搜索analyzed，或被当成一个准确的值not_analyzed，还是完全不可被搜索no
    FieldIndex index() default FieldIndex.analyzed;

    //确定在索引和或搜索时全文字段使用的分析器
    String analyzer() default "";

    //是否持久化
    boolean isTransient() default true;
}
