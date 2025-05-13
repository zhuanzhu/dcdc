package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.ECardTempPO;


public interface ECardTempWriteManage {

	Long insertECardTempWithTx(ECardTempPO po);

	int updateECardTempWithTx(ECardTempPO po);

	int deleteECardTempWithTx(ECardTempPO po);
}
	