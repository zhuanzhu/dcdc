package com.egeo.components.pay.manage.write;

import com.egeo.components.pay.po.PayWeixinLogPO;


public interface PayWeixinLogWriteManage {

	Long insertPayWeixinLogWithTx(PayWeixinLogPO po);

	int updatePayWeixinLogWithTx(PayWeixinLogPO po);

	int deletePayWeixinLogWithTx(PayWeixinLogPO po);
}
	