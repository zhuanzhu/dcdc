package com.egeo.components.user.db;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by guofeng.qin on 2017/9/3 0003.
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.egeo.components.user.dao")
public class DatabaseConfig {
	@Value("${datasource.type}")
    private Class<? extends DataSource> type;
	@Value("${datasource.driver}")
    private String driver;
	@Autowired
	private Environment env;


	private int toInt(String v) {
		return Integer.parseInt(v.trim());
	}

	private boolean toBoolean(String v) {
		return Boolean.valueOf(v.trim());
	}

	private long toLong(String v) {
		return Long.valueOf(v.trim());
	}

	@Bean(name = "dataSourceWrite")
    @ConfigurationProperties(prefix = "datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().type(type).driverClassName(driver).build();
    }
 
 
    @Bean(name = "dataSourceRead")
    @ConfigurationProperties(prefix = "datasource.read")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().type(type).driverClassName(driver).build();
    }

    
    //动态数据源
    @Bean(name = "dynamicDataSource")
    //解决互相依赖关系
    @DependsOn({ "dataSourceWrite", "dataSourceRead"})
    @Primary
    public DataSource getDataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources());
        return dataSource;
    }
 
    private Map<Object, Object> targetDataSources() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.write.getType(), writeDataSource());
        targetDataSources.put(DataSourceType.read.getType(), readDataSource());
        return targetDataSources;
    }

 /*   // 注入动态数据源 DataSourceTransactionManager 用于事务管理(事务回滚只针对同一个数据源)
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("dataSourceWrite") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/
	/*
	
	@Bean
	@Primary
	public DataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();

		DataBase db = Binder.get(env).bind("app.acme", DataBase.class).orElse(null);
		dataSource.setDriverClassName(db.getDriver());
		dataSource.setUrl(db.getUrl());
		dataSource.setUsername(db.getUsername());
		dataSource.setPassword(db.getPassword());

		dataSource.setInitialSize(db.getInitialSize());
		dataSource.setMinIdle(db.getMinIdle());
		dataSource.setMaxActive(db.getMaxActive());
		dataSource.setMaxWait(db.getMaxWait());
		dataSource.setTimeBetweenEvictionRunsMillis(db.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(db.getMinEvictableIdleTimeMillis());
		dataSource.setValidationQuery(db.getValidationQuery());
		dataSource.setTestWhileIdle(db.isTestWhileIdle());
		dataSource.setTestOnBorrow(db.isTestOnBorrow());
		dataSource.setTestOnReturn(db.isTestOnReturn());
		dataSource.setPoolPreparedStatements(db.isPoolPreparedStatements());
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(db.getMaxPoolPreparedStatementPerConnectionSize());
		dataSource.setFilters(db.getFilters());
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		//PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Interceptor[] plugins = new Interceptor[] { new PageHelper() };
		sqlSessionFactoryBean.setPlugins(plugins);
		return sqlSessionFactoryBean.getObject();
	}*/
}
