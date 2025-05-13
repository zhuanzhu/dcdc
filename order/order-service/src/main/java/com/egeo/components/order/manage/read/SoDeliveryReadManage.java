package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoDeliveryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoDeliveryReadManage {

	public SoDeliveryPO findSoDeliveryById(SoDeliveryPO po);

	public PageResult<SoDeliveryPO> findSoDeliveryOfPage(SoDeliveryPO po,Pagination page);

	public List<SoDeliveryPO> findSoDeliveryAll(SoDeliveryPO po);
}
	