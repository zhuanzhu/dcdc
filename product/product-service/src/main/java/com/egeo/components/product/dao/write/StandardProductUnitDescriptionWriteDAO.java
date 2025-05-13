package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.StandardProductUnitDescriptionPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardProductUnitDescriptionWriteDAO extends BaseWriteDAO<StandardProductUnitDescriptionPO> {
    void saveStandardProductUnitDescription(@Param("poList")List<StandardProductUnitDescriptionPO> standardProductUnitDescriptionPOList);

    void updateStandardProductUnitDescriptionSWithTx(@Param("po")StandardProductUnitDescriptionPO standardProductUnitDescriptionPO);
}
	