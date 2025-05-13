package com.egeo.components.order.dao.write;

import com.egeo.components.order.po.SoThirdpartyPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

public interface SoThirdpartyWriteDAO extends BaseWriteDAO<SoThirdpartyPO> {
    int updateSoThirdpartyByCodeWithTx(@Param("po")SoThirdpartyPO po);

    int updateSoThirdpartyByThirdIdWithTx(@Param("po")SoThirdpartyPO po);
}
	