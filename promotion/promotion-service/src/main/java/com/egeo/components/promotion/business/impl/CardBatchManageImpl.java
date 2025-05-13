package com.egeo.components.promotion.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.CardBatchManage;
import com.egeo.components.promotion.facade.CardBatchFacade;
import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cardBatch")
public class CardBatchManageImpl implements CardBatchManage{

	
	@Resource(name="cardBatchFacade")
	private CardBatchFacade cardBatchFacade;

	@Override
	public CardBatchDTO findCardBatchById(CardBatchDTO dto) {
		return cardBatchFacade.findCardBatchById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findCardBatchOfPage(CardBatchDTO dto, Pagination page) {
		PageResult<CardBatchDTO> pageResult = cardBatchFacade.findCardBatchOfPage(dto, page);
		List<Map<String, Object>> maps = new ArrayList<>();
		List<CardBatchDTO> cardBatchList = pageResult.getList();
		for (CardBatchDTO cardBatchDTO : cardBatchList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", cardBatchDTO.getId());
			map.put("batchCode", cardBatchDTO.getBatchCode());
			map.put("createTime", cardBatchDTO.getCreateTime());
			map.put("importSum", cardBatchDTO.getImportSum());
			map.put("source", cardBatchDTO.getSource());
			map.put("remark", cardBatchDTO.getRemark());
			map.put("createUserid", cardBatchDTO.getCreateUserid());
			map.put("createUsername", cardBatchDTO.getCreateUsername());
			maps.add(map);
		}
        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(maps);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<CardBatchDTO> findCardBatchAll(CardBatchDTO dto) {
		return cardBatchFacade.findCardBatchAll(dto);
	}

	@Override
	public Long insertCardBatchWithTx(CardBatchDTO dto) {
		return cardBatchFacade.insertCardBatchWithTx(dto);
	}

	@Override
	public int updateCardBatchWithTx(CardBatchDTO dto) {
		return cardBatchFacade.updateCardBatchWithTx(dto);
	}

	@Override
	public int deleteCardBatchWithTx(CardBatchDTO dto) {
		return cardBatchFacade.deleteCardBatchWithTx(dto);
	}


}
	