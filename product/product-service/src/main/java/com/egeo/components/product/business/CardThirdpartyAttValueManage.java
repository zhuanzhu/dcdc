package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CardThirdpartyAttValueManage {

	public CardThirdpartyAttValueDTO findCardThirdpartyAttValueById(CardThirdpartyAttValueDTO dto);	

	public PageResult<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueOfPage(CardThirdpartyAttValueDTO dto,Pagination page);

	public List<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueAll(CardThirdpartyAttValueDTO dto);

	Long insertCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto);

	int updateCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto);

	int deleteCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto);
}
	