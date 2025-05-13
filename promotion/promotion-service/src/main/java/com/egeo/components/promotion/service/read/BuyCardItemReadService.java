package com.egeo.components.promotion.service.read;


import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface BuyCardItemReadService {

	public BuyCardItemDTO findBuyCardItemById(BuyCardItemDTO dto);

	public PageResult<BuyCardItemDTO> findBuyCardItemOfPage(BuyCardItemDTO dto,Pagination page);

	public List<BuyCardItemDTO> findBuyCardItemAll(BuyCardItemDTO dto);
}
