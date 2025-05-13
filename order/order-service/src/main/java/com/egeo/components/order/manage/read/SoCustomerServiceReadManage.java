package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoCustomerServicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoCustomerServiceReadManage {

	public SoCustomerServicePO findSoCustomerServiceById(SoCustomerServicePO po);

	public PageResult<SoCustomerServicePO> findSoCustomerServiceOfPage(SoCustomerServicePO po,Pagination page);

	public List<SoCustomerServicePO> findSoCustomerServiceAll(SoCustomerServicePO po);

	/**
	 * 根据子订单id查询售后信息
	 * @param scId
	 * @return
	 */
	public SoCustomerServicePO queryCustomerServiceBySoChildId(Long scId);
}
	