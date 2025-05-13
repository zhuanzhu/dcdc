package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.PointsHistoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PointsHistoryReadManage {

	public PointsHistoryPO findPointsHistoryById(PointsHistoryPO po);

	public PageResult<PointsHistoryPO> findPointsHistoryOfPage(PointsHistoryPO po,Pagination page);

	public List<PointsHistoryPO> findPointsHistoryAll(PointsHistoryPO po);
}
	