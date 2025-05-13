package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.InfoSendWayPO;


public interface InfoSendWayWriteManage {

	Long insertInfoSendWayWithTx(InfoSendWayPO po);

	int updateInfoSendWayWithTx(InfoSendWayPO po);

	int deleteInfoSendWayWithTx(InfoSendWayPO po);
}
	