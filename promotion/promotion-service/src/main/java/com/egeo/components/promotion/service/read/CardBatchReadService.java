package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CardBatchReadService {

	public CardBatchDTO findCardBatchById(CardBatchDTO dto);

	public PageResult<CardBatchDTO> findCardBatchOfPage(CardBatchDTO dto,Pagination page);

	public List<CardBatchDTO> findCardBatchAll(CardBatchDTO dto);
}
	