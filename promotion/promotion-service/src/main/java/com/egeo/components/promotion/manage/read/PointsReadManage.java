package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.PointsPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PointsReadManage {

	public PointsPO findPointsById(PointsPO po);

	public PageResult<PointsPO> findPointsOfPage(PointsPO po,Pagination page);

	public List<PointsPO> findPointsAll(PointsPO po);
}
	