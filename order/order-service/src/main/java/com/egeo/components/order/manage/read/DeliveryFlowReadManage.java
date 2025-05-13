package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.DeliveryFlowPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DeliveryFlowReadManage {

	public DeliveryFlowPO findDeliveryFlowById(DeliveryFlowPO po);

	public PageResult<DeliveryFlowPO> findDeliveryFlowOfPage(DeliveryFlowPO po,Pagination page);

	public List<DeliveryFlowPO> findDeliveryFlowAll(DeliveryFlowPO po);
}
	