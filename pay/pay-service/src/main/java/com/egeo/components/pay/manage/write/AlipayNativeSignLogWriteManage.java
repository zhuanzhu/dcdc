package com.egeo.components.pay.manage.write;

import com.egeo.components.pay.po.AlipayNativeSignLogPO;


public interface AlipayNativeSignLogWriteManage {

	Long insertAlipayNativeSignLogWithTx(AlipayNativeSignLogPO po);

	int updateAlipayNativeSignLogWithTx(AlipayNativeSignLogPO po);

	int deleteAlipayNativeSignLogWithTx(AlipayNativeSignLogPO po);
}
	