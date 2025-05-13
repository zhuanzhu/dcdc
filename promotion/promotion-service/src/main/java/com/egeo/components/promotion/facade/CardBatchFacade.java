package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.CardBatchReadService;
import com.egeo.components.promotion.service.write.CardBatchWriteService;
import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CardBatchFacade {
	
	@Autowired
	private CardBatchReadService cardBatchReadService;
	
	@Autowired
	private CardBatchWriteService cardBatchWriteService;
	
	
	public CardBatchDTO findCardBatchById(CardBatchDTO dto){
		
		return cardBatchReadService.findCardBatchById(dto);
	}

	public PageResult<CardBatchDTO> findCardBatchOfPage(CardBatchDTO dto,Pagination page){
		
		return cardBatchReadService.findCardBatchOfPage(dto, page);
		
	}

	public List<CardBatchDTO> findCardBatchAll(CardBatchDTO dto){
		
		return cardBatchReadService.findCardBatchAll(dto);
		
	}

	public Long insertCardBatchWithTx(CardBatchDTO dto){
		
		return cardBatchWriteService.insertCardBatchWithTx(dto);
	}

	public int updateCardBatchWithTx(CardBatchDTO dto){
		
		return cardBatchWriteService.updateCardBatchWithTx(dto);
	}

	public int deleteCardBatchWithTx(CardBatchDTO dto){
		
		return cardBatchWriteService.deleteCardBatchWithTx(dto);
		
	}

}
	