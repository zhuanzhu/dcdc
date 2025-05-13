package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.StandardProductUnitAttNamePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardProductUnitAttNameWriteDAO extends BaseWriteDAO<StandardProductUnitAttNamePO> {
    void saveStandardProductUnitAttName(@Param("poList")List<StandardProductUnitAttNamePO> standardProductUnitAttNamePOList);
}
	