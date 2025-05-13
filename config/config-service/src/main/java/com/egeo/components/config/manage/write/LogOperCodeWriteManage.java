package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.LogOperCodePO;


public interface LogOperCodeWriteManage {

	Long insertLogOperCodeWithTx(LogOperCodePO po);

	int updateLogOperCodeWithTx(LogOperCodePO po);

	int deleteLogOperCodeWithTx(LogOperCodePO po);
}
	