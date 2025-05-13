package com.egeo.components.order.service.read;


import com.egeo.components.order.dto.SoRefundItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface SoRefundItemReadService {

	SoRefundItemDTO findSoRefundItemById(SoRefundItemDTO dto);

	PageResult<SoRefundItemDTO> findSoRefundItemOfPage(SoRefundItemDTO dto, Pagination page);

	List<SoRefundItemDTO> findSoRefundItemAll(SoRefundItemDTO dto);

}
	