package com.egeo.components.promotion.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.vo.ExchangeActivityVO;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface ExchangeActivityManage {

	public ExchangeActivityDTO findExchangeActivityById(ExchangeActivityDTO dto);	

	public PageResult<ExchangeActivityDTO> findExchangeActivityOfPage(ExchangeActivityDTO dto,Pagination page);

	public List<ExchangeActivityDTO> findExchangeActivityAll(ExchangeActivityDTO dto);

	Long insertExchangeActivityWithTx(ExchangeActivityDTO dto);

	int updateExchangeActivityWithTx(ExchangeActivityDTO dto);

	int deleteExchangeActivityWithTx(ExchangeActivityDTO dto);

	/**
	 * 模糊查询以旧换新活动列表
	 */
	public PageResult<ExchangeActivityDTO> fuzzQueryExchangeActivityOfPage(ExchangeActivityDTO dto,Pagination page);

    int insertOrUpdateExchangeActivityWithTx(ExchangeActivityDTO exchangeActivityDTO);

    void updateExchangeActivityStatus(Integer status, Long exchangeId);

	JsonResult<Map<String, Object>> findExchangeActivity(ExchangeActivityDTO dto);
}
	