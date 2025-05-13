package com.egeo.components.promotion.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CardBatchManage {

	public CardBatchDTO findCardBatchById(CardBatchDTO dto);	

	public PageResult<Map<String, Object>> findCardBatchOfPage(CardBatchDTO dto,Pagination page);

	public List<CardBatchDTO> findCardBatchAll(CardBatchDTO dto);

	Long insertCardBatchWithTx(CardBatchDTO dto);

	int updateCardBatchWithTx(CardBatchDTO dto);

	int deleteCardBatchWithTx(CardBatchDTO dto);
}
	