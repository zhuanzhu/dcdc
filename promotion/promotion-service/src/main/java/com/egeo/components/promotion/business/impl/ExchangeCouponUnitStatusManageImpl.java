package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.ExchangeCouponUnitStatusManage;
import com.egeo.components.promotion.facade.ExchangeCouponUnitStatusFacade;
import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("exchangeCouponUnitStatus")
public class ExchangeCouponUnitStatusManageImpl implements ExchangeCouponUnitStatusManage{

	
	@Resource(name="exchangeCouponUnitStatusFacade")
	private ExchangeCouponUnitStatusFacade exchangeCouponUnitStatusFacade;

	@Override
	public ExchangeCouponUnitStatusDTO findExchangeCouponUnitStatusById(ExchangeCouponUnitStatusDTO dto) {
		return exchangeCouponUnitStatusFacade.findExchangeCouponUnitStatusById(dto);
	}

	@Override
	public PageResult<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusOfPage(ExchangeCouponUnitStatusDTO dto, Pagination page) {
		return exchangeCouponUnitStatusFacade.findExchangeCouponUnitStatusOfPage(dto, page);
	}

	@Override
	public List<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusAll(ExchangeCouponUnitStatusDTO dto) {
		return exchangeCouponUnitStatusFacade.findExchangeCouponUnitStatusAll(dto);
	}

	@Override
	public Long insertExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto) {
		return exchangeCouponUnitStatusFacade.insertExchangeCouponUnitStatusWithTx(dto);
	}

	@Override
	public int updateExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto) {
		return exchangeCouponUnitStatusFacade.updateExchangeCouponUnitStatusWithTx(dto);
	}

	@Override
	public int deleteExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto) {
		return exchangeCouponUnitStatusFacade.deleteExchangeCouponUnitStatusWithTx(dto);
	}


}
	