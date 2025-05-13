package com.egeo.components.third.db;

import com.egeo.utils.log.XLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class DataSourceAspect {


	private static final XLogger logger = XLogger.getLogger(DataSourcePartAspect.class);

    /**
     * 注解方式
     * @param joinPoint
     * @param dataSource
     */
    @Before(value = "@annotation(dataSource)")
    public void dataSourcePoint(JoinPoint joinPoint, DataSource dataSource) {
    	logger.debug("当前数据源：{}",DynamicDataSourceHolder.getDataSouce());
        DynamicDataSourceHolder.putDataSource(dataSource.value());
        logger.debug("使用注解数据源，dataSource 切换到：{}",dataSource.value().getName());


    }

    @After("@annotation(dataSource)")
    public void afterSwitchDS(JoinPoint point, DataSource dataSource){
        DynamicDataSourceHolder.remove();
    }
}
