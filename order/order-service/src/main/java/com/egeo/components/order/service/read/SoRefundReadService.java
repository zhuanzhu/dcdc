package com.egeo.components.order.service.read;


import java.util.List;

import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.components.order.dto.SoRefundItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoRefundReadService {

	public SoRefundDTO findSoRefundById(SoRefundDTO dto);

	public PageResult<SoRefundDTO> findSoRefundOfPage(SoRefundDTO dto,List<Long> userIdList, Pagination page);

	public List<SoRefundDTO> findSoRefundAll(SoRefundDTO dto);

	/**
	 * 生成唯一退款单编号
	 */
	public List<String> genSoRefundNO();

	List<SoRefundDTO> getByBatchId(Long batchId,Long orderId,Integer type);

	List<SoRefundItemDTO> getRefundItemByBatchId(Long batchId, Long orderId, Integer type);
}
