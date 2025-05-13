package com.egeo.components.order.dao.read;

import com.egeo.components.order.po.SoThirdpartyPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SoThirdpartyReadDAO extends BaseReadDAO<SoThirdpartyPO>{
    List<Long> getThirdpartyIdListByStatus();

    Long findSoChildIdByThirdpartyId(@Param("jdOrderId")Long jdOrderId);
}
	