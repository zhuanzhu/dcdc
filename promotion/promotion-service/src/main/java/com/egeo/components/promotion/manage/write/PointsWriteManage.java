package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.PointsPO;


public interface PointsWriteManage {

	Long insertPointsWithTx(PointsPO po);

	int updatePointsWithTx(PointsPO po);

	int deletePointsWithTx(PointsPO po);
}
	