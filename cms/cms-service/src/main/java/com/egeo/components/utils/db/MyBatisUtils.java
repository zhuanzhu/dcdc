package com.egeo.components.utils.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.egeo.utils.log.XLogger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by guofeng.qin on 2017/4/5 0005.
 * <p>
 * MyBatis 辅助类
 */
public class MyBatisUtils extends DruidDataSourceFactory implements org.apache.ibatis.datasource.DataSourceFactory {
    private static final XLogger logger = XLogger.getLogger(MyBatisUtils.class);

    private Properties props;

    public static SqlSessionFactory initSqlSessionFactory(String configFile) throws IOException {
        try (InputStream is = Resources.getResourceAsStream(configFile)) {
            return new SqlSessionFactoryBuilder().build(is);
        }
    }

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        try {
            return createDataSource(props);
        } catch (Exception e) {
            logger.error("create DataSource error !!!", e);
        }

        return null;
    }
}
