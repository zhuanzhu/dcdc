package com.egeo.components.promotion.service.read;


import java.util.List;
	
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ExchangeOrderRecordReadService {

	public ExchangeOrderRecordDTO findExchangeOrderRecordById(ExchangeOrderRecordDTO dto);

	public PageResult<ExchangeOrderRecordDTO> findExchangeOrderRecordOfPage(ExchangeOrderRecordDTO dto,Pagination page);

	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAll(ExchangeOrderRecordDTO dto);

	public int getCountOfCompletedOrderByExchangeId(Long exchangeId);

	/**
	 * 模糊查询以旧换新活动兑换记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<ExchangeOrderRecordDTO> fuzzyQueryExchangeOrderRecordOfPage(ExchangeOrderRecordDTO dto,String startTime,String endTime,Pagination page);

    List<ExchangeOrderRecordDTO> findExchangeOrderRecordAllByOrderCode(String orderCode);

    List<ExchangeOrderRecordDTO> findExchangeOrderRecordLived(ExchangeOrderRecordDTO exchangeOrderRecordDTO);
}
	