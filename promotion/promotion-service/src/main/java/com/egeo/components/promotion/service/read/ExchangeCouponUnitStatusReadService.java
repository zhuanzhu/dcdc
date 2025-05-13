package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ExchangeCouponUnitStatusReadService {

	public ExchangeCouponUnitStatusDTO findExchangeCouponUnitStatusById(ExchangeCouponUnitStatusDTO dto);

	public PageResult<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusOfPage(ExchangeCouponUnitStatusDTO dto,Pagination page);

	public List<ExchangeCouponUnitStatusDTO> findExchangeCouponUnitStatusAll(ExchangeCouponUnitStatusDTO dto);

	public List<Integer> findUnitStatusAll(ExchangeCouponUnitStatusDTO dto);
}
	