package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoFlowReadService {

	public SoFlowDTO findSoFlowById(SoFlowDTO dto);

	public PageResult<SoFlowDTO> findSoFlowOfPage(SoFlowDTO dto,Pagination page);

	public List<SoFlowDTO> findSoFlowAll(SoFlowDTO dto);

	/**
	 * 根据订单id查询订单操作记录列表
	 * @param orderId
	 * @return
	 */
	public List<SoFlowDTO> queryFlowListBySoId(Long orderId);
}
	