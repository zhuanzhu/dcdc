package com.egeo.components.promotion.service.read;


import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface BuyCardItemRefundReadService {

	public BuyCardItemRefundDTO findBuyCardItemRefundById(BuyCardItemRefundDTO dto);

	public PageResult<BuyCardItemRefundDTO> findBuyCardItemRefundOfPage(BuyCardItemRefundDTO dto,Pagination page);

	public List<BuyCardItemRefundDTO> findBuyCardItemRefundAll(BuyCardItemRefundDTO dto);
}
