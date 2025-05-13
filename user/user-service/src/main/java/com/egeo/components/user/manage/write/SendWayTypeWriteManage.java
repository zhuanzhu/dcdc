package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.SendWayTypePO;


public interface SendWayTypeWriteManage {

	Long insertSendWayTypeWithTx(SendWayTypePO po);

	int updateSendWayTypeWithTx(SendWayTypePO po);

	int deleteSendWayTypeWithTx(SendWayTypePO po);
}
	