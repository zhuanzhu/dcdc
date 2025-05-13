package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.CardSaltConverter;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.manage.read.CardSaltReadManage;
import com.egeo.components.config.po.CardSaltPO;
import com.egeo.components.config.service.read.CardSaltReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cardSaltReadService")
public class CardSaltReadServiceImpl   implements CardSaltReadService{
	@Autowired
	private CardSaltReadManage cardSaltReadManage;

	@Override
	public CardSaltDTO findCardSaltById(CardSaltDTO dto) {
		CardSaltPO po = CardSaltConverter.toPO(dto);
		CardSaltPO list = cardSaltReadManage.findCardSaltById(po);		
		return CardSaltConverter.toDTO(list);
	}

	@Override
	public PageResult<CardSaltDTO> findCardSaltOfPage(CardSaltDTO dto, Pagination page) {
		CardSaltPO po = CardSaltConverter.toPO(dto);
        PageResult<CardSaltPO> pageResult = cardSaltReadManage.findCardSaltOfPage(po, page);
        
        List<CardSaltDTO> list = CardSaltConverter.toDTO(pageResult.getList());
        PageResult<CardSaltDTO> result = new PageResult<CardSaltDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CardSaltDTO> findCardSaltAll(CardSaltDTO dto) {
		CardSaltPO po = CardSaltConverter.toPO(dto);
		List<CardSaltPO> list = cardSaltReadManage.findCardSaltAll(po);		
		return CardSaltConverter.toDTO(list);
	}

	@Override
	public CardSaltDTO queryCardSaltByUUID(String uuid) {
		return CardSaltConverter.toDTO(cardSaltReadManage.queryCardSaltByUUID(uuid));
	}
}
	