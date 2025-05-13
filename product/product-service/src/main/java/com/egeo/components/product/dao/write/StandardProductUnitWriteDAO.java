package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardProductUnitWriteDAO extends BaseWriteDAO<StandardProductUnitPO> {
    void saveSPU(@Param("poList")List<StandardProductUnitPO> standardProductUnitPOList);

    void updateStandardProductUnitList(@Param("poList")List<StandardProductUnitPO> standardProductUnitPOList);
}
	