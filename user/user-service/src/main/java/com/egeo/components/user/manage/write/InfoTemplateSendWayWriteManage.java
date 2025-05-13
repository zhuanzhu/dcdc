package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.InfoTemplateSendWayPO;


public interface InfoTemplateSendWayWriteManage {

	Long insertInfoTemplateSendWayWithTx(InfoTemplateSendWayPO po);

	int updateInfoTemplateSendWayWithTx(InfoTemplateSendWayPO po);

	int deleteInfoTemplateSendWayWithTx(InfoTemplateSendWayPO po);
}
	