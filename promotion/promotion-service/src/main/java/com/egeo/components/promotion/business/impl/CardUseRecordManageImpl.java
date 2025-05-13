package com.egeo.components.promotion.business.impl;


import com.egeo.components.promotion.business.CardUseRecordManage;
import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.facade.CardUseRecordFacade;
import com.egeo.components.promotion.vo.GrantUserBuyCardVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service("cardUseRecord")
public class CardUseRecordManageImpl implements CardUseRecordManage{


	@Resource(name="cardUseRecordFacade")
	private CardUseRecordFacade cardUseRecordFacade;

	@Override
	public CardUseRecordDTO findCardUseRecordById(CardUseRecordDTO dto) {
		return cardUseRecordFacade.findCardUseRecordById(dto);
	}

	@Override
	public PageResult<CardUseRecordDTO> findCardUseRecordOfPage(CardUseRecordDTO dto, Pagination page) {
		return cardUseRecordFacade.findCardUseRecordOfPage(dto, page);
	}

	@Override
	public List<CardUseRecordDTO> findCardUseRecordAll(CardUseRecordDTO dto) {
		return cardUseRecordFacade.findCardUseRecordAll(dto);
	}

	@Override
	public Long insertCardUseRecordWithTx(CardUseRecordDTO dto) {
		return cardUseRecordFacade.insertCardUseRecordWithTx(dto);
	}

	@Override
	public int updateCardUseRecordWithTx(CardUseRecordDTO dto) {
		return cardUseRecordFacade.updateCardUseRecordWithTx(dto);
	}

	@Override
	public int deleteCardUseRecordWithTx(CardUseRecordDTO dto) {
		return cardUseRecordFacade.deleteCardUseRecordWithTx(dto);
	}
}
