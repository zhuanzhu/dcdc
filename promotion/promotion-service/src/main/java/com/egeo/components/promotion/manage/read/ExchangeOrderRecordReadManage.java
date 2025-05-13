package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.ExchangeOrderRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ExchangeOrderRecordReadManage {

	public ExchangeOrderRecordPO findExchangeOrderRecordById(ExchangeOrderRecordPO po);

	public PageResult<ExchangeOrderRecordPO> findExchangeOrderRecordOfPage(ExchangeOrderRecordPO po,Pagination page);

	public List<ExchangeOrderRecordPO> findExchangeOrderRecordAll(ExchangeOrderRecordPO po);

	public int getCountOfCompletedOrderByExchangeId(Long exchangeId);

	/**
	 * 模糊查询以旧换新活动兑换记录
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<ExchangeOrderRecordPO> fuzzyQueryExchangeOrderRecordOfPage(ExchangeOrderRecordPO dto,String startTime,String endTime, Pagination page);

    List<ExchangeOrderRecordPO> findExchangeOrderRecordLived(ExchangeOrderRecordPO po);
}
	