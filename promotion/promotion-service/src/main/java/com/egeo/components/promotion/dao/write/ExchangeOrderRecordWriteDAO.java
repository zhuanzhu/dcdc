package com.egeo.components.promotion.dao.write;

import com.egeo.components.promotion.po.ExchangeOrderRecordPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

public interface ExchangeOrderRecordWriteDAO extends BaseWriteDAO<ExchangeOrderRecordPO> {
    int updateByOrderCode(@Param("po")ExchangeOrderRecordPO po);
}
	