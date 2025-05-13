package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CardThirdpartyAttValueReadService;
import com.egeo.components.product.manage.read.CardThirdpartyAttValueReadManage;
import com.egeo.components.product.converter.CardThirdpartyAttValueConverter;
import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;
import com.egeo.components.product.po.CardThirdpartyAttValuePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cardThirdpartyAttValueReadService")
public class CardThirdpartyAttValueReadServiceImpl  implements CardThirdpartyAttValueReadService {
	@Autowired
	private CardThirdpartyAttValueReadManage cardThirdpartyAttValueReadManage;

	@Override
	public CardThirdpartyAttValueDTO findCardThirdpartyAttValueById(CardThirdpartyAttValueDTO dto) {
		CardThirdpartyAttValuePO po = CardThirdpartyAttValueConverter.toPO(dto);
		CardThirdpartyAttValuePO list = cardThirdpartyAttValueReadManage.findCardThirdpartyAttValueById(po);		
		return CardThirdpartyAttValueConverter.toDTO(list);
	}

	@Override
	public PageResult<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueOfPage(CardThirdpartyAttValueDTO dto, Pagination page) {
		CardThirdpartyAttValuePO po = CardThirdpartyAttValueConverter.toPO(dto);
        PageResult<CardThirdpartyAttValuePO> pageResult = cardThirdpartyAttValueReadManage.findCardThirdpartyAttValueOfPage(po, page);
        
        List<CardThirdpartyAttValueDTO> list = CardThirdpartyAttValueConverter.toDTO(pageResult.getList());
        PageResult<CardThirdpartyAttValueDTO> result = new PageResult<CardThirdpartyAttValueDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueAll(CardThirdpartyAttValueDTO dto) {
		CardThirdpartyAttValuePO po = CardThirdpartyAttValueConverter.toPO(dto);
		List<CardThirdpartyAttValuePO> list = cardThirdpartyAttValueReadManage.findCardThirdpartyAttValueAll(po);		
		return CardThirdpartyAttValueConverter.toDTO(list);
	}

	@Override
	public Integer findCardTypeByStandardProductUnitId(Long standardProductUnitId) {
		return cardThirdpartyAttValueReadManage.findCardTypeByStandardProductUnitId(standardProductUnitId);
	}
}
	