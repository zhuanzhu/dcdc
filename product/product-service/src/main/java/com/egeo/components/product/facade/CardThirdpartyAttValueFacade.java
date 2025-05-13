package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CardThirdpartyAttValueReadService;
import com.egeo.components.product.service.write.CardThirdpartyAttValueWriteService;
import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CardThirdpartyAttValueFacade {
	
	@Resource
	private CardThirdpartyAttValueReadService cardThirdpartyAttValueReadService;
	
	@Resource
	private CardThirdpartyAttValueWriteService cardThirdpartyAttValueWriteService;
	
	
	public CardThirdpartyAttValueDTO findCardThirdpartyAttValueById(CardThirdpartyAttValueDTO dto){
		
		return cardThirdpartyAttValueReadService.findCardThirdpartyAttValueById(dto);
	}

	public PageResult<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueOfPage(CardThirdpartyAttValueDTO dto,Pagination page){
		
		return cardThirdpartyAttValueReadService.findCardThirdpartyAttValueOfPage(dto, page);
		
	}

	public List<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueAll(CardThirdpartyAttValueDTO dto){
		
		return cardThirdpartyAttValueReadService.findCardThirdpartyAttValueAll(dto);
		
	}

	public Long insertCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto){
		
		return cardThirdpartyAttValueWriteService.insertCardThirdpartyAttValueWithTx(dto);
	}

	public int updateCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto){
		
		return cardThirdpartyAttValueWriteService.updateCardThirdpartyAttValueWithTx(dto);
	}

	public int deleteCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto){
		
		return cardThirdpartyAttValueWriteService.deleteCardThirdpartyAttValueWithTx(dto);
		
	}

}
	