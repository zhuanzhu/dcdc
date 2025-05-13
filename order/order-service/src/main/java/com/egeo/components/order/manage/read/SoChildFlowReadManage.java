package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoChildFlowPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoChildFlowReadManage {

	public SoChildFlowPO findSoChildFlowById(SoChildFlowPO po);

	public PageResult<SoChildFlowPO> findSoChildFlowOfPage(SoChildFlowPO po,Pagination page);

	public List<SoChildFlowPO> findSoChildFlowAll(SoChildFlowPO po);

	/**
	 * 根据订单id查询操作流水
	 * @param orderId
	 * @return
	 */
	public List<SoChildFlowPO> queryFlowListBySoId(Long orderId);

	/**
	 * 根据子订单id查询操作流水
	 * @param soChildId
	 * @return
	 */
	public List<SoChildFlowPO> queryFlowListBySoChildId(Long soChildId);
}
	