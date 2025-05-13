package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.ExchangeCouponUnitStatusReadService;
import com.egeo.components.promotion.service.write.ExchangeCouponUnitStatusWriteService;
import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ExchangeCouponUnitStatusFacade {
	
	@Autowired
	private ExchangeCouponUnitStatusReadService exchangeCouponUnitStatusReadService;
	
	@Autowired
	private ExchangeCouponUnitStatusWriteService exchangeCouponUnitStatusWriteService;
	
	
	public ExchangeCouponUnitStatusDTO findExchangeCouponUnitStatusById(ExchangeCouponUnitStatusDTO dto){
		
		return exchangeCouponUnitStatusReadService.findExchangeCouponUnitStatusById(dto);
	}

	public PageResult<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusOfPage(ExchangeCouponUnitStatusDTO dto,Pagination page){
		
		return exchangeCouponUnitStatusReadService.findExchangeCouponUnitStatusOfPage(dto, page);
		
	}

	public List<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusAll(ExchangeCouponUnitStatusDTO dto){
		
		return exchangeCouponUnitStatusReadService.findExchangeCouponUnitStatusAll(dto);
		
	}

	public Long insertExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto){
		
		return exchangeCouponUnitStatusWriteService.insertExchangeCouponUnitStatusWithTx(dto);
	}

	public int updateExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto){
		
		return exchangeCouponUnitStatusWriteService.updateExchangeCouponUnitStatusWithTx(dto);
	}

	public int deleteExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto){
		
		return exchangeCouponUnitStatusWriteService.deleteExchangeCouponUnitStatusWithTx(dto);
		
	}

}
	