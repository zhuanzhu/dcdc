package com.egeo.components.product.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MybatisConfig {
    @Resource(name = "dynamicDataSource")
    private DataSource dynamicDataSource;
    @Bean
    public SqlSessionFactory sqlSessionFactory(org.apache.ibatis.session.Configuration config) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setConfiguration(config);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappings/**/*SqlMap.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    @Bean(name="config")
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatiesConfig(){ 
       return new org.apache.ibatis.session.Configuration();
    }
}
