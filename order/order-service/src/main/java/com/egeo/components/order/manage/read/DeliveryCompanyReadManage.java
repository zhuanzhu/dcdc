package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.DeliveryCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DeliveryCompanyReadManage {

	public DeliveryCompanyPO findDeliveryCompanyById(DeliveryCompanyPO po);

	public PageResult<DeliveryCompanyPO> findDeliveryCompanyOfPage(DeliveryCompanyPO po,Pagination page);

	public List<DeliveryCompanyPO> findDeliveryCompanyAll(DeliveryCompanyPO po);

	/**
	 * 根据名称查询物流公司
	 * @param name
	 * @return
	 */
	public DeliveryCompanyPO queryDeliveryCompanyByName(String name);
}
	