package com.egeo.components.promotion.service.read.impl;

import com.egeo.components.promotion.converter.CardUseRecordConverter;
import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.manage.read.CardUseRecordReadManage;
import com.egeo.components.promotion.po.CardUseRecordPO;
import com.egeo.components.promotion.service.read.CardUseRecordReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cardUseRecordReadService")
public class CardUseRecordReadServiceImpl implements CardUseRecordReadService {
	@Autowired
	private CardUseRecordReadManage cardUseRecordReadManage;

	@Override
	public CardUseRecordDTO findCardUseRecordById(CardUseRecordDTO dto) {
		CardUseRecordPO po = CardUseRecordConverter.toPO(dto);
		CardUseRecordPO list = cardUseRecordReadManage.findCardUseRecordById(po);
		return CardUseRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<CardUseRecordDTO> findCardUseRecordOfPage(CardUseRecordDTO dto, Pagination page) {
		CardUseRecordPO po = CardUseRecordConverter.toPO(dto);
		PageResult<CardUseRecordPO> pageResult = cardUseRecordReadManage.findCardUseRecordOfPage(po, page);

		List<CardUseRecordDTO> list = CardUseRecordConverter.toDTO(pageResult.getList());
		PageResult<CardUseRecordDTO> result = new PageResult<>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<CardUseRecordDTO> findCardUseRecordAll(CardUseRecordDTO dto) {
		CardUseRecordPO po = CardUseRecordConverter.toPO(dto);
		List<CardUseRecordPO> list = cardUseRecordReadManage.findCardUseRecordAll(po);
		return CardUseRecordConverter.toDTO(list);
	}
}
