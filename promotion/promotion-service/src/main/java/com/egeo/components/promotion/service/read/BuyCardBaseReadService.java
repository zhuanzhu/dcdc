package com.egeo.components.promotion.service.read;


import com.egeo.components.promotion.dto.BuyCardBaseDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface BuyCardBaseReadService {

	public BuyCardBaseDTO findBuyCardBaseById(BuyCardBaseDTO dto);

	public PageResult<BuyCardBaseDTO> findBuyCardBaseOfPage(BuyCardBaseDTO dto,Pagination page);

	public List<BuyCardBaseDTO> findBuyCardBaseAll(BuyCardBaseDTO dto);

	public int findMaxSortValue();
}
