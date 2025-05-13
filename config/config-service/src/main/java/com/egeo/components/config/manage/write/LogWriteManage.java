package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.LogPO;


public interface LogWriteManage {

	Long insertLogWithTx(LogPO po);

	int updateLogWithTx(LogPO po);

	int deleteLogWithTx(LogPO po);
}
	