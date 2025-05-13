package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CardThirdpartyAttValueManage;
import com.egeo.components.product.facade.CardThirdpartyAttValueFacade;
import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cardThirdpartyAttValue")
public class CardThirdpartyAttValueManageImpl implements CardThirdpartyAttValueManage{

	
	@Resource(name="cardThirdpartyAttValueFacade")
	private CardThirdpartyAttValueFacade cardThirdpartyAttValueFacade;

	@Override
	public CardThirdpartyAttValueDTO findCardThirdpartyAttValueById(CardThirdpartyAttValueDTO dto) {
		return cardThirdpartyAttValueFacade.findCardThirdpartyAttValueById(dto);
	}

	@Override
	public PageResult<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueOfPage(CardThirdpartyAttValueDTO dto, Pagination page) {
		return cardThirdpartyAttValueFacade.findCardThirdpartyAttValueOfPage(dto, page);
	}

	@Override
	public List<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueAll(CardThirdpartyAttValueDTO dto) {
		return cardThirdpartyAttValueFacade.findCardThirdpartyAttValueAll(dto);
	}

	@Override
	public Long insertCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto) {
		return cardThirdpartyAttValueFacade.insertCardThirdpartyAttValueWithTx(dto);
	}

	@Override
	public int updateCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto) {
		return cardThirdpartyAttValueFacade.updateCardThirdpartyAttValueWithTx(dto);
	}

	@Override
	public int deleteCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto) {
		return cardThirdpartyAttValueFacade.deleteCardThirdpartyAttValueWithTx(dto);
	}


}
	