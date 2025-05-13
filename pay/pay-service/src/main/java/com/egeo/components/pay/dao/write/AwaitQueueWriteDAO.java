package com.egeo.components.pay.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.pay.po.AwaitQueuePO;
import com.egeo.orm.BaseWriteDAO;

public interface AwaitQueueWriteDAO extends BaseWriteDAO<AwaitQueuePO> {

	int updateAwaitQueueBySoIdWithTx(@Param("po")AwaitQueuePO po);
}
	