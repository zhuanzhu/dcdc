package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.ProvCityAreaPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProvCityAreaReadManage {

	public ProvCityAreaPO findProvCityAreaById(ProvCityAreaPO po);

	public PageResult<ProvCityAreaPO> findProvCityAreaOfPage(ProvCityAreaPO po,Pagination page);

	public List<ProvCityAreaPO> findProvCityAreaAll(ProvCityAreaPO po);
}
	