package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ExchangeActivityReadService {

	public ExchangeActivityDTO findExchangeActivityById(ExchangeActivityDTO dto);

	public PageResult<ExchangeActivityDTO> findExchangeActivityOfPage(ExchangeActivityDTO dto,Pagination page);

	public List<ExchangeActivityDTO> findExchangeActivityAll(ExchangeActivityDTO dto);

	/**
	 * 模糊查询以旧换新活动列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<ExchangeActivityDTO> fuzzQueryExchangeActivityOfPage(ExchangeActivityDTO dto, Pagination page);


    List<ExchangeActivityDTO> findExchangeActivityByIds(List<Long> exchangeList);
}
	