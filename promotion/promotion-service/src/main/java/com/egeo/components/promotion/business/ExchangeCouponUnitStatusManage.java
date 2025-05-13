package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ExchangeCouponUnitStatusManage {

	public ExchangeCouponUnitStatusDTO findExchangeCouponUnitStatusById(ExchangeCouponUnitStatusDTO dto);	

	public PageResult<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusOfPage(ExchangeCouponUnitStatusDTO dto,Pagination page);

	public List<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusAll(ExchangeCouponUnitStatusDTO dto);

	Long insertExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto);

	int updateExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto);

	int deleteExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto);
}
	