package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.PointsHistoryPO;


public interface PointsHistoryWriteManage {

	Long insertPointsHistoryWithTx(PointsHistoryPO po);

	int updatePointsHistoryWithTx(PointsHistoryPO po);

	int deletePointsHistoryWithTx(PointsHistoryPO po);
}
	