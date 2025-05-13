package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.PayTypePO;


public interface PayTypeWriteManage {

	Long insertPayTypeWithTx(PayTypePO po);

	int updatePayTypeWithTx(PayTypePO po);

	int deletePayTypeWithTx(PayTypePO po);
}
	