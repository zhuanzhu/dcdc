package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoFlowPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoFlowReadManage {

	public SoFlowPO findSoFlowById(SoFlowPO po);

	public PageResult<SoFlowPO> findSoFlowOfPage(SoFlowPO po,Pagination page);

	public List<SoFlowPO> findSoFlowAll(SoFlowPO po);

	/**
	 * 根据订单id查询订单操作记录列表
	 * @param orderId
	 * @return
	 */
	public List<SoFlowPO> queryFlowListBySoId(Long orderId);
}
	