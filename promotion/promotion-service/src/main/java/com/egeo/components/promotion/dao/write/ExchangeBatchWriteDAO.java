package com.egeo.components.promotion.dao.write;

import com.egeo.components.promotion.po.ExchangeBatchPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExchangeBatchWriteDAO extends BaseWriteDAO<ExchangeBatchPO> {
    int insertBatchList(@Param("batchPOList")List<ExchangeBatchPO> batchPOList);
}
	