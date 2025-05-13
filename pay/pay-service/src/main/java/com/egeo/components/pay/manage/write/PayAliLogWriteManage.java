package com.egeo.components.pay.manage.write;

import com.egeo.components.pay.po.PayAliLogPO;


public interface PayAliLogWriteManage {

	Long insertPayAliLogWithTx(PayAliLogPO po);

	int updatePayAliLogWithTx(PayAliLogPO po);

	int deletePayAliLogWithTx(PayAliLogPO po);
}
	