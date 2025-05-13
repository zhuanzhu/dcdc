package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoDeliveryItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoDeliveryItemReadManage {

	public SoDeliveryItemPO findSoDeliveryItemById(SoDeliveryItemPO po);

	public PageResult<SoDeliveryItemPO> findSoDeliveryItemOfPage(SoDeliveryItemPO po,Pagination page);

	public List<SoDeliveryItemPO> findSoDeliveryItemAll(SoDeliveryItemPO po);
}
	