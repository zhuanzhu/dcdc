package com.egeo.components.order.dao.write;

import com.egeo.components.order.po.SoItemPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

public interface SoItemWriteDAO extends BaseWriteDAO<SoItemPO> {

    int updateSoItemRefundWithTx(@Param("po")SoItemPO po);
}
