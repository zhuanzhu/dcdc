package com.egeo.components.promotion.service.read;


import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface CardUseRecordReadService {

	public CardUseRecordDTO findCardUseRecordById(CardUseRecordDTO dto);

	public PageResult<CardUseRecordDTO> findCardUseRecordOfPage(CardUseRecordDTO dto,Pagination page);

	public List<CardUseRecordDTO> findCardUseRecordAll(CardUseRecordDTO dto);
}
