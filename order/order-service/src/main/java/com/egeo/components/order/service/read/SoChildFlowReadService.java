package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoChildFlowReadService {

	public SoChildFlowDTO findSoChildFlowById(SoChildFlowDTO dto);

	public PageResult<SoChildFlowDTO> findSoChildFlowOfPage(SoChildFlowDTO dto,Pagination page);

	public List<SoChildFlowDTO> findSoChildFlowAll(SoChildFlowDTO dto);

	/**
	 * 根据订单id查询操作流水
	 * @param orderId
	 * @return
	 */
	public List<SoChildFlowDTO> queryFlowListBySoId(Long orderId);

	/**
	 * 根据子订单id查询操作流水
	 * @param soChildId
	 * @return
	 */
	public List<SoChildFlowDTO> queryFlowListBySoChildId(Long soChildId);
}
	