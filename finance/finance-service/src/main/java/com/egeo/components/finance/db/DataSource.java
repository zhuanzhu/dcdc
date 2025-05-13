package com.egeo.components.finance.db;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface DataSource {
 
    /**
     * 数据库路由
     */
    DataSourceType value() default DataSourceType.write;
}