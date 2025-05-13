package com.egeo.components.third.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
    protected Object determineCurrentLookupKey() {
        if (DynamicDataSourceHolder.getDataSouce() != null) {
            return DynamicDataSourceHolder.getDataSouce();
        }
        return DataSourceType.write.getType();
    }
}
