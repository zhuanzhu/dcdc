package com.egeo.components.promotion.manage.read;

import java.util.List;

import com.egeo.components.promotion.condition.ExchangeActivityCondition;
import com.egeo.components.promotion.po.ExchangeActivityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ExchangeActivityReadManage {

	public ExchangeActivityPO findExchangeActivityById(ExchangeActivityPO po);

	public PageResult<ExchangeActivityPO> findExchangeActivityOfPage(ExchangeActivityPO po,Pagination page);

	public List<ExchangeActivityPO> findExchangeActivityAll(ExchangeActivityPO po);

	/**
	 * 模糊查询以旧换新活动列表
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<ExchangeActivityPO> fuzzQueryExchangeActivityOfPage(ExchangeActivityPO po, Pagination page);

	List<ExchangeActivityPO> findExchangeActivityByIds(List<Long> exchangeList);
}
	