package com.egeo.components.pay.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.egeo.utils.log.XLogger;

@Aspect
@Component
@Order(-1)
public class DataSourcePartAspect {

	private static final XLogger logger = XLogger.getLogger(DataSourcePartAspect.class);

 	@Before("execution(* com.egeo.components..manage.read..*.*(..)) ")
    public void setReadDataSourceType() {
 		DynamicDataSourceHolder.remove();
 		logger.debug("当前数据源：{}",DynamicDataSourceHolder.getDataSouce());
        DynamicDataSourceHolder.putDataSource(DataSourceType.read);
        logger.debug("数据库执行读操作，dataSource 切换到：{}",DataSourceType.read.getName());
    }
 
    /**
     * mapper 修改删除操作默认使用主库库
     */
    @Before("execution(* com.egeo.components..manage.write..*.*(..)) ")
    public void setWriteDataSourceType() {
    	DynamicDataSourceHolder.remove();
    	logger.debug("当前数据源：{}",DynamicDataSourceHolder.getDataSouce());
        DynamicDataSourceHolder.putDataSource(DataSourceType.write);
        logger.debug("数据库执行写操作，dataSource 切换到：{}",DataSourceType.write.getName());
    }
 
    /**
     * 完成后释放
     * @param point
     */
    @After("execution(* com.egeo.components..manage.read..*.*(..)) ")
    public void afterSwitchDSCheck(JoinPoint point){
        DynamicDataSourceHolder.remove();
    }
 
    @After("execution(* com.egeo.components..manage.write..*.*(..)) ")
    public void afterSwitchDS(JoinPoint point){
        DynamicDataSourceHolder.remove();
    }
	
	
}
