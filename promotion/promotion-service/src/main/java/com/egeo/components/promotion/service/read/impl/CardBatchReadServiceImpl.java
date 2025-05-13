package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CardBatchReadService;
import com.egeo.components.promotion.manage.read.CardBatchReadManage;
import com.egeo.components.promotion.converter.CardBatchConverter;
import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.components.promotion.po.CardBatchPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cardBatchReadService")
public class CardBatchReadServiceImpl implements CardBatchReadService {
	@Autowired
	private CardBatchReadManage cardBatchReadManage;

	@Override
	public CardBatchDTO findCardBatchById(CardBatchDTO dto) {
		CardBatchPO po = CardBatchConverter.toPO(dto);
		CardBatchPO list = cardBatchReadManage.findCardBatchById(po);		
		return CardBatchConverter.toDTO(list);
	}

	@Override
	public PageResult<CardBatchDTO> findCardBatchOfPage(CardBatchDTO dto, Pagination page) {
		CardBatchPO po = CardBatchConverter.toPO(dto);
        PageResult<CardBatchPO> pageResult = cardBatchReadManage.findCardBatchOfPage(po, page);
        
        List<CardBatchDTO> list = CardBatchConverter.toDTO(pageResult.getList());
        PageResult<CardBatchDTO> result = new PageResult<CardBatchDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CardBatchDTO> findCardBatchAll(CardBatchDTO dto) {
		CardBatchPO po = CardBatchConverter.toPO(dto);
		List<CardBatchPO> list = cardBatchReadManage.findCardBatchAll(po);		
		return CardBatchConverter.toDTO(list);
	}
}
	