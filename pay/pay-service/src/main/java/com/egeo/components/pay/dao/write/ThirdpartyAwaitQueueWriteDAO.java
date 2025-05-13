package com.egeo.components.pay.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.pay.po.ThirdpartyAwaitQueuePO;
import com.egeo.orm.BaseWriteDAO;

public interface ThirdpartyAwaitQueueWriteDAO extends BaseWriteDAO<ThirdpartyAwaitQueuePO> {
    int deleteByCode(@Param("po")ThirdpartyAwaitQueuePO po);
}
	