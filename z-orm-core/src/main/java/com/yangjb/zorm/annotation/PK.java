package com.yangjb.zorm.annotation;

import java.lang.annotation.*;

/**
 * 标注entity的字段是主键
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PK {
}
