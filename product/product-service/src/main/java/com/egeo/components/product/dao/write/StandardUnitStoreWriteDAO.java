package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.StandardUnitStorePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardUnitStoreWriteDAO extends BaseWriteDAO<StandardUnitStorePO> {
    void saveStandardUnitStore(@Param("poList") List<StandardUnitStorePO> standardUnitStorePOList);
}
	