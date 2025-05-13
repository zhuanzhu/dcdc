package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.LogDictPO;


public interface LogDictWriteManage {

	Long insertLogDictWithTx(LogDictPO po);

	int updateLogDictWithTx(LogDictPO po);

	int deleteLogDictWithTx(LogDictPO po);
}
	